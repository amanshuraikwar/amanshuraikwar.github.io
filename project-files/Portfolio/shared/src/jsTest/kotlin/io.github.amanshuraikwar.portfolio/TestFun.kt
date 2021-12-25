package io.github.amanshuraikwar.portfolio

import io.ktor.utils.io.core.toByteArray
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

private external fun require(module: String): dynamic

@Suppress("ObjectPropertyName")
external val __dirname: dynamic

/** Read the given resource as binary data. */
actual fun readBinaryResource(resourceName: String): ByteArray {
    if (resourceName.endsWith(".json")) {
        println(JSON.stringify(require("${__dirname}/$resourceName")))
        return JSON.stringify(require("${__dirname}/$resourceName")).toByteArray()
    } else {
        throw IllegalArgumentException("Only .json files are supported")
    }
}