package io.github.amanshuraikwar.portfolio

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.runBlocking

actual fun runTest(block: suspend CoroutineScope.() -> Unit) = runBlocking { block() }

actual annotation class JsName actual constructor(
    actual val name: String
)

/** Read the given resource as binary data. */
actual fun readBinaryResource(
    resourceName: String
): ByteArray {
    return ClassLoader
        .getSystemResourceAsStream(resourceName)!!
        .readBytes()
}