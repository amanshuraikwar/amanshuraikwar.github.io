"""Offline unit tests for every active RSS source adapter."""

from __future__ import annotations

import json
import sys
import unittest
import xml.etree.ElementTree as ET
from pathlib import Path
from unittest.mock import patch


RSS_DIRECTORY = Path(__file__).resolve().parents[1]
sys.path.insert(0, str(RSS_DIRECTORY))
import generate  # noqa: E402


FIXTURES_DIRECTORY = Path(__file__).with_name("fixtures")
MEDIA_CONTENT = f"{{{generate.MEDIA_NAMESPACE}}}content"


def inspiration_grid_payload(title: str, slug: str) -> dict[str, object]:
    return {
        "result": {
            "pageContext": {
                "postsAndEditorials": {
                    "nodes": [
                        {
                            "title": title,
                            "link": f"/{slug}/",
                            "excerpt": f"A featured {slug} project.",
                            "categories": {"nodes": [{"name": "Creative"}]},
                            "featuredImage": {
                                "sourceUrl": f"https://images.example/{slug}.jpg"
                            },
                        }
                    ]
                }
            }
        }
    }


class RSSSourceParserTests(unittest.TestCase):
    @classmethod
    def setUpClass(cls) -> None:
        cls.config = json.loads((RSS_DIRECTORY / "sources.json").read_text(encoding="utf-8"))
        cls.feeds = {feed["id"]: feed for feed in cls.config["feeds"]}

    def assert_inspiration_grid_feed(self, feed_id: str, slug: str) -> None:
        feed = self.feeds[feed_id]
        with patch.object(generate, "fetch_json", return_value=inspiration_grid_payload("Test &amp; Title", slug)):
            root = generate.make_feed(feed).getroot()

        item = root.find("channel/item")
        self.assertIsNotNone(item)
        assert item is not None
        self.assertEqual(item.findtext("title"), "Test & Title")
        self.assertEqual(item.findtext("link"), f"https://theinspirationgrid.com/{slug}/")
        self.assertEqual(item.findtext("description"), f"A featured {slug} project.")
        self.assertEqual(item.findtext("category"), "Creative")
        self.assertEqual(
            item.find(MEDIA_CONTENT).attrib["url"],
            f"https://images.example/{slug}.jpg",
        )

    def test_inspiration_grid_photography_parser(self) -> None:
        self.assert_inspiration_grid_feed("inspiration-grid-photography", "photography-test")

    def test_inspiration_grid_illustration_parser(self) -> None:
        self.assert_inspiration_grid_feed("inspiration-grid-illustration", "illustration-test")

    def test_inspiration_grid_industrial_design_parser(self) -> None:
        self.assert_inspiration_grid_feed("inspiration-grid-industrial-design", "industrial-design-test")

    def test_site_of_sites_html_parser(self) -> None:
        feed = self.feeds["site-of-sites-websites"]
        html = (FIXTURES_DIRECTORY / "site-of-sites.html").read_text(encoding="utf-8")
        with patch.object(generate, "fetch_text", return_value=html):
            root = generate.make_feed(feed).getroot()

        item = root.find("channel/item")
        self.assertIsNotNone(item)
        assert item is not None
        self.assertEqual(item.findtext("title"), "Sample Studio")
        self.assertEqual(item.findtext("link"), "https://www.siteofsites.co/websites/sample-studio")
        self.assertEqual(
            item.findtext("description"),
            "Featured by Site of Sites in 08/2026. Original website: https://sample.studio/",
        )
        self.assertEqual([category.text for category in item.findall("category")], ["Portfolio", "Agency"])
        self.assertEqual(
            item.find(MEDIA_CONTENT).attrib["url"],
            "https://static.wixstatic.com/media/abc123~mv2.jpg",
        )

    def test_source_catalog_records_every_requested_website(self) -> None:
        catalog = self.config["source_catalog"]
        self.assertEqual(len(catalog), 5)
        self.assertEqual(
            {entry["source_url"] for entry in catalog},
            {
                "https://theinspirationgrid.com/category/photography/",
                "https://theinspirationgrid.com/category/illustration/",
                "https://www.instagram.com/stevemccurryofficial/",
                "https://www.siteofsites.co/?p=1",
                "https://theinspirationgrid.com/category/industrial-design/",
            },
        )


if __name__ == "__main__":
    unittest.main()
