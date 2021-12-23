package io.github.amanshuraikwar.portfolio

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.runBlocking

actual fun runTest(block: suspend CoroutineScope.() -> Unit) = runBlocking { block() }

actual annotation class JsName actual constructor(
    actual val name: String
)

actual class Platform actual constructor() {
    actual val platform: String = "Android ${android.os.Build.VERSION.SDK_INT}"
}