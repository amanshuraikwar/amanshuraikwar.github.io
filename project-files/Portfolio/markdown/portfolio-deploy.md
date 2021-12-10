# Jetpack Compose to Github Pages via Github Actions
#Date December 10, 2021

Here is the Github Action workflow file I used to deploy my project written in Jetpack Compose for Web to Github Pages

```
name: Deploy Github Pages

on:
  workflow_dispatch:
  push:
    branches:
      - trunk

jobs:
  web-build-and-deploy:
    runs-on: macos-11
    steps:
      - name: Checkout
        uses: actions/checkout@v2.3.1
        
      - name: Set up JDK 11
        uses: actions/setup-java@v1
        with:
          java-version: 11

      - name: Set up Kotlin 1.6.0
        uses: fwilhe2/setup-kotlin@main
        with:
          version: 1.6.0
          
      - name: Build Web HTML & JS Files
        run: |
          cd project-files/Portfolio
          kotlinc -script generateWebComposeMdLayouts.kts

      - name: Deploy to Github Pages
        uses: JamesIves/github-pages-deploy-action@3.7.1
        with:
          branch: gh-pages
          folder: project-files/Portfolio/build
```

!Btn[Go to the Workflow File](https://github.com/amanshuraikwar/amanshuraikwar.github.io/blob/trunk/.github/workflows/main.yml)