# Issues using MockK in a KMM shared module
#Date December 12, 2021

If you are writing unit tests for your Kotlin project and need to mock certain objects, there is high chance that you are using MockK. Here are a couple of issues that I faced while using MockK in my KMM project. The project has Android and iOS apps with business logic written in shared KMM modules, I was writing unit tests to test this business logic.

PS: MockK does not support Kotlin/Native as of now.

### Issue 1: Missing run button in Android Studio

When I was integrating MockK in my project, like any down to earth developer :), I added the gradle dependency with the latest version 1.12.1. After I synced gradle, I found out that the run button disappeared from my test classes and test functions.

```
val commonTest by getting {
    dependencies {
        implementation(\"io.mockk:mockk:1.12.1\")
    }
}
```

![Missing Run button](../assets/mockk-kmm-issues-1.png)

The issue here is that, even though gradle synced sucessfully, when I looked into the build tab in android studio, I found this warning/error -_-. This is because MockK version 1.12.1 is not a KMM library, hence the artifacts were not found.

![Artifact not found](../assets/mockk-kmm-issues-2.png)

To fix this issue, I needed to use the version suffixed with .kotlinxx, where x is a number. So I used the lastest version matching this pattern, which was 1.9.3.kotlin12.

```
val commonTest by getting {
    dependencies {
        implementation(\"io.mockk:mockk:1.9.3.kotlin12\")
    }
}
```

The run button re-appeared in my test classes and test functions. Hurray!

![Run button re-appeared](../assets/mockk-kmm-issues-3.png)

### Issue 2: Using coEvery {} throws an exception

After correcting the library button as mentioned above, most tests were working fine. Until I tried to mock the return value of a suspend function by using coEvery {}. When I used coEvery {}, it threw the following NoClassDefFoundError exception.

![Artifact not found](../assets/mockk-kmm-issues-4.png)

This is because the library versions suffixed with kotlin12 are meant for kotlin 1.2.x versions. The kotlin version that I was using in my project was 1.3.0+ and the CoroutineImpl file is in a different location if compared to 1.2.x.

To fix the issue, I needed to use the library version suffixed with kotlin13.

```
val commonTest by getting {
    dependencies {
        implementation(\"io.mockk:mockk:1.8.13.kotlin13\")
    }
}
```

Everything seems to be working for me now, if I discover any more issues, will update this page.

### References

If I got something wrong, feel free to DM me on twitter to correct me. Here are some of the references, happy mocking :) 

!Btn[MockK](https://mockk.io/)

!Btn[MockK Versions](https://mvnrepository.com/artifact/io.mockk/mockk)

!Btn[Go To My Twitter](https://twitter.com/amanshuraikwar_)