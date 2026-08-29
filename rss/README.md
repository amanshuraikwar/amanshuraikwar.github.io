# RSS feed generator

`generate.py` converts configured JSON sources into static RSS 2.0 files. The
generated files live in `project-files/Portfolio/web/src/jsMain/resources/rss/`,
which means the website build publishes them at `/rss/`.

Generate all feeds locally:

```sh
python3 rss/generate.py
```

The first feed is available at:

`https://amanshuraikwar.github.io/rss/inspiration-grid-photography.xml`

## Add another source

Add a new entry to `sources.json`. It currently supports sources that expose a
JSON document: `item_path` locates the list of entries, and each value in
`fields` is a dotted path inside an entry. Set `output_path` beneath the web
resources `rss` folder, then run the generator and commit the resulting XML.

For an HTML-only source, add a small source adapter to `generate.py` rather
than relying on page-specific selectors in the workflow.
