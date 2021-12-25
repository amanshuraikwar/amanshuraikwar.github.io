package io.github.amanshuraikwar.portfolio

import kotlinx.coroutines.CoroutineScope

expect fun runTest(block: suspend CoroutineScope.() -> Unit)
expect annotation class JsName constructor(val name: String)

/** Read the given resource as binary data. */
expect fun readBinaryResource(
    resourceName: String
): ByteArray