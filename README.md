# My Portfolio

<p align="center">

<a href="https://github.com/amanshuraikwar/amanshuraikwar.github.io/tree/gh-pages">![](https://github.com/amanshuraikwar/amanshuraikwar.github.io/actions/workflows/build-web-html-js-deploy-github-pages.yml/badge.svg?branch=trunk)</a>
&nbsp; &nbsp; &nbsp; &nbsp;
![](https://github.com/amanshuraikwar/amanshuraikwar.github.io/actions/workflows/build-web-html-js.yml/badge.svg)
&nbsp; &nbsp; &nbsp; &nbsp;
<a href="https://amanshuraikwar.github.io/tests/shared/android-jvm/">![](https://github.com/amanshuraikwar/amanshuraikwar.github.io/actions/workflows/shared-tests-android-jvm.yml/badge.svg)</a>
&nbsp; &nbsp; &nbsp; &nbsp;
<a href="https://amanshuraikwar.github.io/tests/shared/js-browser/">![](https://github.com/amanshuraikwar/amanshuraikwar.github.io/actions/workflows/shared-tests-js-browser.yml/badge.svg)</a>

</p>

This is my portfolio. It is a KMM project which runs on Web, Android, iOS. The business logic is written in Kotlin. The Web app is written in Kotlin, built using Jetpack Compose for Web. The Android app is written in Kotlin, built using Jetpack Compose. The iOS app UI is written in Swift, built using Swift UI and it uses the KMM shared module for business logic.

## Building the Web App

Just run the kotlin script file buildWebsite.kts, it will spit out the built website in build/ folder.

```
kotlinc -script buildWebsite.kts
```

Make sure you have Kotlin 1.6.0 CLI and Java 11 installed. Or, just run the Build Web HTML & JS Files workflow and download the artifacts.

## Building the Android App

Run tests for shared module:
```
./gradlew :shared:cleanTestDebugUnitTest :shared:testDebugUnitTest
```

## Building the iOS App

Coming soon...

## Get the App

[Go to the Web App](https://amanshuraikwar.github.io)

## Special Thanks :)
Special thanks to [John O'Reilly's](https://github.com/joreilly) [People in Space repo](https://github.com/joreilly/PeopleInSpace), which I found extremely helpful for getting started with KMM.
