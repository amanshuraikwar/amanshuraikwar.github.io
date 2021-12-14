# How to use highlight.js in your Jetpack Compose Web project
#Date December 14, 2021

If you are building your web app using Jetpack Compose for Web and want to use highlight.js, this is the blog for you.

### Integrating in the index.html file

If we follow the official documentation if highlight.js, it instructs us to add the following lines in our html file.

```html
<link rel="stylesheet"
      href="//cdnjs.cloudflare.com/ajax/libs/highlight.js/11.3.1/styles/default.min.css">
<script src="//cdnjs.cloudflare.com/ajax/libs/highlight.js/11.3.1/highlight.min.js"></script>
<script>hljs.highlightAll();</script>
```

In out Jetpack Compose Web project, this file is usually located here:

```shell
path_to_you_web_module_root/src/jsMain/resources/index.html
```

After adding this in our index.html file, it should automatically style our html code elements. We can also add a language specific class to the code block. In Jetpack Compose Web you can do it like this:

```kotlin
Pre {
    Code(
        attrs = {
            classes("language-kotlin")
        }
    ) {
        Text("val x = 4\n")
        Text("more code here...\n")
    }
}
```

### No changes to the web page?

Ideally, after doing this, highlight.js should work, but you will observe that this change anything to your content. The reason being, above integration steps are for static web pages.

For any dynamically generated web pages, we need to call this function manually after the code DOM element is added to the web page:

```js
hljs.highlightElement(el);
```

Or if we wanna do it for all of the code elements at once, we can do something like this as mentioned in the official documentation:

```js
document.addEventListener('DOMContentLoaded', (event) => {
  document.querySelectorAll('pre code').forEach((el) => {
    hljs.highlightElement(el);
  });
});
```

But since we are using Jetpack Compose for Web, our DOM tree is managed by the compose runtime, so we need to call this js function everytime our code UI element re-composes.

### Calling highlight.js from our Compose code

To be able to call highlight.js from our Compose code, we first need add it as a gradle dependency. In your web module's build.gradle(.kts) file, add the following npm dependency.

```kotlin
kotlin {
    sourceSets {
        val jsMain by getting {
            dependencies {
                implementation(npm("highlight.js", "11.3.1"))
            }
        }
    }
}
```

Since we have added it as an npm dependency, we no longer need to add the scripts in our index.html file. To be able to call the js code from this dependency, we need to define a JsModule in our code. Create the following class in your web module.

```kotlin
import org.w3c.dom.HTMLElement

@JsName("hljs")
@JsModule("highlight.js")
@JsNonModule
external class HighlightJs {
    companion object {
        fun highlightElement(block: HTMLElement)
    }
}
```

Now, we need to call this function everytime our code dom element re-composes. Update the previous compose code like below:

```kotlin
@Composable
fun HighlightedCode(code: String) {
    Pre {
        Code(
            attrs = {
                classes("language-kotlin", "hljs")
            }
        ) {
            Text(code)

            // A side effect of composition 
            // that runs on every successful recomposition 
            // if key changes.
            DomSideEffect(code) {
                HighlightJs.highlightElement(it)
            }
        }
    }
}
```

### References

If I got something wrong, feel free to DM me on twitter to correct me. Here are some of the references, happy composing :)

!Btn[Highlight.js](https://highlightjs.org/)

!Btn[compose-jb example](https://github.com/JetBrains/compose-jb/tree/master/examples/web-landing)

!Btn[Go To My Twitter](https://twitter.com/amanshuraikwar_)