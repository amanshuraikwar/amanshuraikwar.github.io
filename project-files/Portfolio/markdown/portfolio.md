# My Portfolio
#Date December 27, 2021

This is my portfolio. It is a KMM project which currently supports Web, Android & iOS clients coming soon. The Web app is written in Kotlin, built using Jetpack Compose for Web and it has a KMM shared module for business logic.

### Web App

Just run the kotlin script file buildWebsite.kts, it will spit out the built website in build/ folder.

```shell
kotlinc -script buildWebsite.kts
```

Make sure you have Kotlin 1.6.0 CLI and Java 11 installed.

Run tests for shared module:

```shell
./gradlew :shared:cleanJsBrowserTest :shared:jsBrowserTest
```

### Android App

Run tests for shared module:

```shell
./gradlew :shared:cleanTestDebugUnitTest :shared:testDebugUnitTest
```

### iOS App

Coming soon...

### Get the App

!Btn[Go to the Web App](https://amanshuraikwar.github.io)

!Btn[Go to the source code](https://github.com/amanshuraikwar/amanshuraikwar.github.io)

### Special Thanks :)

Special thanks to John O'Reilly's  People in Space repo, which I found extremely helpful for getting started with KMM.

!Btn[John O'Reilly](https://github.com/joreilly)

!Btn[People in Space repo](https://github.com/joreilly/PeopleInSpace)