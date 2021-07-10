# My Portfolio
Made with Kotlin Multiplatform Mobile and Jetpack Compose.

Platforms supported:
* Web
* Android
* *iOS (TODO)*
* *WearOS (TODO)*

## How to build
```
cd /.../amanshuraikwar.github.io/project-files/Portfolio/
```
### Andoid
```
./gradlew :androidApp:assembleDebug
```

### Web
* Launch web page
```
./gradlew :web:jsBrowserRun
```
* Export HTML and JS files for Github Pages
```
chmod +x exportForGithubPages
./exportForGithubPages
```

# Special Thanks :)
Special thanks to [John O'Reilly's](https://github.com/joreilly) [People in Space repo](https://github.com/joreilly/PeopleInSpace), which I found extremely helpful for getting started with KMM.
