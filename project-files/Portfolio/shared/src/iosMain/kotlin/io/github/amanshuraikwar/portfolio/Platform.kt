package io.github.amanshuraikwar.portfolio

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.runBlocking
import platform.UIKit.UIDevice

actual fun runTest(block: suspend CoroutineScope.() -> Unit) = runBlocking { block() }

actual annotation class JsName actual constructor(
    actual val name: String
)

actual class Platform actual constructor() {
    actual val platform: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}