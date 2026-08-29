#!/usr/bin/env python3
"""Generate static RSS 2.0 feeds configured in rss/sources.json.

The script intentionally uses only Python's standard library so it can run in
GitHub Actions without a dependency-install step.
"""

from __future__ import annotations

import argparse
import json
import re
import sys
import urllib.error
import urllib.request
import xml.etree.ElementTree as ET
from html import unescape
from html.parser import HTMLParser
from pathlib import Path
from urllib.parse import urljoin


REPOSITORY_ROOT = Path(__file__).resolve().parent.parent
CONFIG_PATH = Path(__file__).with_name("sources.json")
MEDIA_NAMESPACE = "http://search.yahoo.com/mrss/"
ATOM_NAMESPACE = "http://www.w3.org/2005/Atom"

ET.register_namespace("media", MEDIA_NAMESPACE)
ET.register_namespace("atom", ATOM_NAMESPACE)


def value_at_path(data: object, path: str, *, required: bool = False) -> object | None:
    """Read a dotted path from nested JSON dictionaries."""
    value = data
    for part in path.split("."):
        if not isinstance(value, dict) or part not in value:
            if required:
                raise ValueError(f"Missing required path: {path}")
            return None
        value = value[part]
    return value


def fetch_json(url: str) -> object:
    request = urllib.request.Request(
        url,
        headers={
            "Accept": "application/json",
            "User-Agent": "amanshuraikwar.github.io RSS generator/1.0",
        },
    )
    try:
        with urllib.request.urlopen(request, timeout=30) as response:
            return json.load(response)
    except (urllib.error.HTTPError, urllib.error.URLError, TimeoutError, json.JSONDecodeError) as error:
        raise RuntimeError(f"Could not fetch valid JSON from {url}: {error}") from error


def fetch_text(url: str) -> str:
    request = urllib.request.Request(
        url,
        headers={
            "Accept": "text/html,application/xhtml+xml",
            "User-Agent": "amanshuraikwar.github.io RSS generator/1.0",
        },
    )
    try:
        with urllib.request.urlopen(request, timeout=30) as response:
            return response.read().decode(response.headers.get_content_charset() or "utf-8")
    except (urllib.error.HTTPError, urllib.error.URLError, TimeoutError, UnicodeDecodeError) as error:
        raise RuntimeError(f"Could not fetch HTML from {url}: {error}") from error


class WixRepeaterParser(HTMLParser):
    """Extract Site of Sites' server-rendered Wix repeater entries."""

    def __init__(self) -> None:
        super().__init__()
        self.items: list[dict[str, object]] = []
        self.item: dict[str, object] | None = None
        self.item_div_depth = 0
        self.current_anchor: dict[str, str] | None = None
        self.current_paragraph: list[str] | None = None
        self.current_label: list[str] | None = None

    def handle_starttag(self, tag: str, attrs: list[tuple[str, str | None]]) -> None:
        attributes = dict(attrs)
        if tag == "div" and self.item is None:
            classes = attributes.get("class", "")
            if attributes.get("role") == "listitem" and "wixui-repeater__item" in classes:
                self.item = {"categories": []}
                self.item_div_depth = 1
                return
        if self.item is None:
            return
        if tag == "div":
            self.item_div_depth += 1
        elif tag == "a":
            self.current_anchor = attributes
            href = attributes.get("href", "")
            if "/websites/" in href:
                self.item["link"] = href
            elif href.startswith(("https://", "http://")) and "siteofsites.co" not in href:
                self.item["external_url"] = href
        elif tag == "p":
            self.current_paragraph = []
        elif tag == "label":
            self.current_label = []
        elif tag == "wow-image":
            image_info = attributes.get("data-image-info")
            if image_info:
                try:
                    image_uri = json.loads(image_info)["imageData"]["uri"]
                    self.item["image"] = f"https://static.wixstatic.com/media/{image_uri}"
                except (KeyError, TypeError, json.JSONDecodeError):
                    pass
        elif tag == "img" and "image" not in self.item:
            self.item["image"] = attributes.get("src", "")

    def handle_data(self, data: str) -> None:
        if self.item is None:
            return
        if self.current_anchor is not None:
            self.current_anchor["text"] = self.current_anchor.get("text", "") + data
        if self.current_paragraph is not None:
            self.current_paragraph.append(data)
        if self.current_label is not None:
            self.current_label.append(data)

    def handle_endtag(self, tag: str) -> None:
        if self.item is None:
            return
        if tag == "a" and self.current_anchor is not None:
            if self.current_anchor.get("dataquery") and self.current_anchor.get("text", "").strip():
                self.item.setdefault("title", self.current_anchor["text"].strip())
            self.current_anchor = None
        elif tag == "p" and self.current_paragraph is not None:
            text = " ".join("".join(self.current_paragraph).split())
            if re.fullmatch(r"(?:0[1-9]|1[0-2])/\d{4}|[A-Z][a-z]{2} \d{4}", text):
                self.item["date"] = text
            self.current_paragraph = None
        elif tag == "label" and self.current_label is not None:
            category = " ".join("".join(self.current_label).split())
            if category:
                categories = self.item["categories"]
                assert isinstance(categories, list)
                categories.append({"name": category})
            self.current_label = None
        elif tag == "div":
            self.item_div_depth -= 1
            if self.item_div_depth == 0:
                if self.item.get("title") and self.item.get("link"):
                    date = self.item.get("date", "")
                    external_url = self.item.get("external_url")
                    description = f"Featured by Site of Sites in {date}."
                    if external_url:
                        description += f" Original website: {external_url}"
                    self.item["description"] = description
                    self.items.append(self.item)
                self.item = None


def wix_repeater_items(url: str) -> list[dict[str, object]]:
    parser = WixRepeaterParser()
    parser.feed(fetch_text(url))
    if not parser.items:
        raise ValueError("No Wix catalogue entries found")
    return parser.items


def source_items(feed: dict[str, object]) -> list[object]:
    parser = feed.get("parser", "json")
    if parser == "json":
        item_path = feed.get("item_path")
        if not isinstance(item_path, str):
            raise ValueError(f"Feed {feed['id']} has an invalid item_path")
        source_url = feed.get("source_url")
        if not isinstance(source_url, str):
            raise ValueError(f"Feed {feed['id']} needs source_url")
        items = value_at_path(fetch_json(source_url), item_path, required=True)
        if not isinstance(items, list):
            raise ValueError(f"Feed {feed['id']} did not contain an item list at {item_path}")
        return items
    if parser == "wix_repeater_html":
        source_url = feed.get("source_url")
        if not isinstance(source_url, str):
            raise ValueError(f"Feed {feed['id']} needs source_url")
        return wix_repeater_items(source_url)
    raise ValueError(f"Feed {feed['id']} uses unsupported parser: {parser}")


def add_text(parent: ET.Element, tag: str, value: object | None) -> ET.Element:
    element = ET.SubElement(parent, tag)
    element.text = "" if value is None else unescape(str(value).strip())
    return element


def make_feed(feed: dict[str, object]) -> ET.ElementTree:
    fields = feed["fields"]
    if not isinstance(fields, dict):
        raise ValueError(f"Feed {feed['id']} has invalid fields")

    items = source_items(feed)
    if not items:
        raise ValueError(f"Feed {feed['id']} did not contain any items")

    rss = ET.Element("rss", {"version": "2.0"})
    channel = ET.SubElement(rss, "channel")
    add_text(channel, "title", feed["title"])
    add_text(channel, "link", feed["site_url"])
    add_text(channel, "description", feed["description"])
    add_text(channel, "generator", "amanshuraikwar.github.io RSS generator")
    ET.SubElement(
        channel,
        f"{{{ATOM_NAMESPACE}}}link",
        {"href": str(feed["feed_url"]), "rel": "self", "type": "application/rss+xml"},
    )

    link_base_url = str(feed["link_base_url"])
    for source_item in items:
        if not isinstance(source_item, dict):
            continue
        title = value_at_path(source_item, str(fields["title"]), required=True)
        article_path = value_at_path(source_item, str(fields["link"]), required=True)
        article_url = urljoin(link_base_url, str(article_path))

        item = ET.SubElement(channel, "item")
        add_text(item, "title", title)
        add_text(item, "link", article_url)
        guid = add_text(item, "guid", article_url)
        guid.set("isPermaLink", "true")
        add_text(item, "description", value_at_path(source_item, str(fields["description"])))

        categories = value_at_path(source_item, str(fields["categories"]))
        if isinstance(categories, list):
            for category in categories:
                category_name = value_at_path(category, str(fields["category_name"]))
                if category_name:
                    add_text(item, "category", category_name)

        image = value_at_path(source_item, str(fields["image"]))
        if image:
            image_url = str(image)
            ET.SubElement(
                item,
                f"{{{MEDIA_NAMESPACE}}}content",
                {"url": image_url, "medium": "image"},
            )
            ET.SubElement(item, f"{{{MEDIA_NAMESPACE}}}thumbnail", {"url": image_url})

    ET.indent(rss, space="  ")
    return ET.ElementTree(rss)


def write_feed(feed: dict[str, object], *, dry_run: bool) -> bool:
    feed_id = feed.get("id", "<unknown>")
    source_url = feed.get("source_url")
    output_path = feed.get("output_path")
    if not isinstance(source_url, str) or not isinstance(output_path, str):
        raise ValueError(f"Feed {feed_id} needs source_url and output_path")

    output = (REPOSITORY_ROOT / output_path).resolve()
    if REPOSITORY_ROOT not in output.parents:
        raise ValueError(f"Feed {feed_id} output_path must stay inside this repository")

    output.parent.mkdir(parents=True, exist_ok=True)
    tree = make_feed(feed)
    xml = ET.tostring(tree.getroot(), encoding="utf-8", xml_declaration=True) + b"\n"
    changed = not output.exists() or output.read_bytes() != xml
    status = "would update" if dry_run and changed else "updated" if changed else "unchanged"
    print(f"{feed_id}: {status}")
    if changed and not dry_run:
        output.write_bytes(xml)
    return changed


def main() -> int:
    parser = argparse.ArgumentParser(description="Generate configured RSS feeds.")
    parser.add_argument("--feed", help="Generate only this feed id")
    parser.add_argument("--dry-run", action="store_true", help="Do not write generated files")
    args = parser.parse_args()

    config = json.loads(CONFIG_PATH.read_text(encoding="utf-8"))
    feeds = config.get("feeds")
    if not isinstance(feeds, list):
        raise ValueError("rss/sources.json must contain a feeds array")
    selected = [feed for feed in feeds if isinstance(feed, dict) and (not args.feed or feed.get("id") == args.feed)]
    if args.feed and not selected:
        raise ValueError(f"No feed configured with id: {args.feed}")

    any_changed = False
    for feed in selected:
        any_changed = write_feed(feed, dry_run=args.dry_run) or any_changed
    return 0 if selected else 1


if __name__ == "__main__":
    try:
        sys.exit(main())
    except (OSError, RuntimeError, ValueError, KeyError) as error:
        print(f"RSS generation failed: {error}", file=sys.stderr)
        sys.exit(1)
