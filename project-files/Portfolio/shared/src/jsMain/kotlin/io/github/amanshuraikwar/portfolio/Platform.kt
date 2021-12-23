package io.github.amanshuraikwar.portfolio

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.await
import kotlinx.coroutines.launch
import kotlinx.coroutines.promise

@Suppress("EXPERIMENTAL_API_USAGE")
actual fun runTest(block: suspend CoroutineScope.() -> Unit): dynamic {
    return GlobalScope.launch {
        promise {
            block()
        }.await()
    }
}

actual typealias JsName = kotlin.js.JsName

actual class Platform actual constructor() {
    actual val platform: String = "this is web"

}