# RSS feed generator

`generate.py` converts configured JSON sources into static RSS 2.0 files. The
generated files live in `project-files/Portfolio/web/src/jsMain/resources/rss/`,
which means the website build publishes them at `/rss/`.

`sources.json` is also the repository's source catalogue. Its `source_catalog`
array records every requested website, including sources that cannot currently
be generated automatically (for example, an Instagram account without an
authorized API source). Only entries in `feeds` are generated.

Generate all feeds locally:

```sh
python3 rss/generate.py
```

Run the offline parser tests (the same command used by the nightly workflow):

```sh
python3 -m unittest discover -s rss/tests -v
```

## Automated refresh and deployment

The nightly workflow runs daily at 06:50 UTC (GitHub may delay scheduled runs).
It tests the parsers, generates all feeds, and uploads the XML as a workflow
artifact. The deployment job downloads those feeds before building the website,
checks that the built feeds match the generated files, and publishes to
`gh-pages`. Both jobs check out the same source commit.

Generated updates are not committed to `trunk`, so the workflow works with
pull-request-only branch protection. The checked-in XML remains a snapshot for
local builds. Deployments triggered by a push to `trunk` or a manual Pages
deployment use the same refresh workflow to avoid republishing old snapshots.
Refresh and deployment runs share a concurrency group to prevent overlapping
publishes. If feed generation fails, deployment is skipped.

The first feed is available at:

`https://amanshuraikwar.github.io/rss/inspiration-grid-photography.xml`

## Add another source

Add a new entry to `sources.json`. JSON sources use `item_path` to locate the
list of entries, and each value in `fields` is a dotted path inside an entry.
Set `output_path` beneath the web resources `rss` folder, then run the
generator and commit the resulting XML.

The Site of Sites feed uses the built-in `wix_repeater_html` parser for its
server-rendered catalogue. For another HTML-only source, add a small source
adapter to `generate.py` rather than placing page-specific selectors in the
workflow.
