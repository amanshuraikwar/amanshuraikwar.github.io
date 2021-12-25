@file:Suppress("IllegalIdentifier")

package io.github.amanshuraikwar.portfolio

import io.github.amanshuraikwar.portfolio.network.PortfolioApi
import io.github.amanshuraikwar.portfolio.network.model.ThemeColorsDataResponse
import io.github.amanshuraikwar.portfolio.network.model.ThemeDataResponse
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.utils.io.ByteReadChannel
import kotlin.test.Test
import kotlin.test.assertEquals

class ThemeDataTest {
    @Test
    @JsName("verifyJsonSerializationIsCorrect")
    fun `verify json serialization is correct`() {
        val mockEngine = MockEngine { request ->
            if (request.url.toString().endsWith("/theme.json")) {
                respond(
                    content = ByteReadChannel(
                        """ 
                    {
                        "themes": [
                          {
                            "name": "Dark Salmon",
                            "isDark": true,
                            "primaryColor": "#FFFFCDD2",
                            "onPrimaryColor": "#FF4E342E",
                            "surfaceColor": "#FF212121",
                            "onSurfaceColor": "#FFffffff",
                            "errorColor": "#FFE57373",
                            "onErrorColor": "#FF4E342E"
                          },
                          {
                            "name": "Light Blue",
                            "isDark": false,
                            "primaryColor": "#ffEA5C5A",
                            "onPrimaryColor": "#FFffffff",
                            "surfaceColor": "#ffCDECF9",
                            "onSurfaceColor": "#FF030204",
                            "errorColor": "#FFE57373",
                            "onErrorColor": "#FF212121"
                          },
                          {
                            "name": "Matt D'av Ella",
                            "isDark": false,
                            "primaryColor": "#ffE35638",
                            "onPrimaryColor": "#FFFADACA",
                            "surfaceColor": "#ffFBF8EC",
                            "onSurfaceColor": "#FF24242C",
                            "errorColor": "#FFE57373",
                            "onErrorColor": "#FF212121"
                          },
                          {
                            "name": "Google",
                            "isDark": false,
                            "primaryColor": "#ff4285F4",
                            "onPrimaryColor": "#FFffffff",
                            "surfaceColor": "#ffffffff",
                            "onSurfaceColor": "#FF212121",
                            "errorColor": "#FFE57373",
                            "onErrorColor": "#FF212121"
                          },
                          {
                            "name": "Spotify",
                            "isDark": true,
                            "primaryColor": "#ff1FDF64",
                            "onPrimaryColor": "#FF212121",
                            "surfaceColor": "#ff212121",
                            "onSurfaceColor": "#FFFFFFFF",
                            "errorColor": "#FFE57373",
                            "onErrorColor": "#FF212121"
                          }
                        ]
                    }
                """.trimIndent()
                    ),
                    status = HttpStatusCode.OK,
                    headers = headersOf(HttpHeaders.ContentType, "application/json")
                )
            } else {
                respond(content = ByteReadChannel(""))
            }
        }

        val portfolioApi = PortfolioApi(
            PortfolioApi.createHttpClient(
                engine = mockEngine,
                enableNetworkLogs = true
            )
        )

        runTest {
            assertEquals(
                ThemeDataResponse(
                    listOf(
                        ThemeColorsDataResponse(
                            name = "Dark Salmon",
                            isDark = true,
                            primaryColor = "#FFFFCDD2",
                            onPrimaryColor = "#FF4E342E",
                            surfaceColor = "#FF212121",
                            onSurfaceColor = "#FFffffff",
                            errorColor = "#FFE57373",
                            onErrorColor = "#FF4E342E",
                        ),
                        ThemeColorsDataResponse(
                            name = "Light Blue",
                            isDark = false,
                            primaryColor = "#ffEA5C5A",
                            onPrimaryColor = "#FFffffff",
                            surfaceColor = "#ffCDECF9",
                            onSurfaceColor = "#FF030204",
                            errorColor = "#FFE57373",
                            onErrorColor = "#FF212121",
                        ),
                        ThemeColorsDataResponse(
                            name = "Matt D'av Ella",
                            isDark = false,
                            primaryColor = "#ffE35638",
                            onPrimaryColor = "#FFFADACA",
                            surfaceColor = "#ffFBF8EC",
                            onSurfaceColor = "#FF24242C",
                            errorColor = "#FFE57373",
                            onErrorColor = "#FF212121",
                        ),
                        ThemeColorsDataResponse(
                            name = "Google",
                            isDark = false,
                            primaryColor = "#ff4285F4",
                            onPrimaryColor = "#FFffffff",
                            surfaceColor = "#ffffffff",
                            onSurfaceColor = "#FF212121",
                            errorColor = "#FFE57373",
                            onErrorColor = "#FF212121",
                        ),
                        ThemeColorsDataResponse(
                            name = "Spotify",
                            isDark = true,
                            primaryColor = "#ff1FDF64",
                            onPrimaryColor = "#FF212121",
                            surfaceColor = "#ff212121",
                            onSurfaceColor = "#FFFFFFFF",
                            errorColor = "#FFE57373",
                            onErrorColor = "#FF212121",
                        )
                    )
                ),
                portfolioApi.getThemeData()
            )
        }
    }

    @Test
    @JsName("verifyRemoteJsonSerializationIsWorking")
    fun `verify remote json serialization is working`() {
        val portfolioApi = PortfolioApi(
            PortfolioApi.createHttpClient(enableNetworkLogs = true)
        )

        runTest {
            assertEquals(
                true,
                portfolioApi.getThemeData().themes.isNotEmpty()
            )
        }
    }

    @Test
    @JsName("verifyRemoteJsonColorStringsAreAccurate")
    fun `verify remote json color strings are accurate`() {
        val portfolioApi = PortfolioApi(
            PortfolioApi.createHttpClient(enableNetworkLogs = true)
        )

        runTest {
            assertEquals(
                true,
                portfolioApi
                    .getThemeData()
                    .themes
                    .fold(true) { acc, cur ->
                        acc && cur.let {
                            it.primaryColor.isAccurateColorString()
                                    && it.onPrimaryColor.isAccurateColorString()
                                    && it.surfaceColor.isAccurateColorString()
                                    && it.onSurfaceColor.isAccurateColorString()
                                    && it.errorColor.isAccurateColorString()
                                    && it.onErrorColor.isAccurateColorString()
                        }
                    }
            )
        }
    }

    private fun String.isAccurateColorString(): Boolean {
        return matches(Regex("#([A-Fa-f0-9]){8}"))
    }

    /*
    @Test
    @JsName("verifyLocalJsonSerializationIsWorking")
    fun `verify local json serialization is working`() {
        val mockEngine = MockEngine { request ->
            if (request.url.toString().endsWith("/theme.json")) {
                respond(
                    content = ByteReadChannel(
                        readBinaryResource("api/theme.json").toString()
                    ),
                    status = HttpStatusCode.OK,
                    headers = headersOf(HttpHeaders.ContentType, "application/json")
                )
            } else {
                respond(content = ByteReadChannel(""))
            }
        }

        val portfolioApi = PortfolioApi(
            PortfolioApi.createHttpClient(
                engine = mockEngine,
                enableNetworkLogs = true
            )
        )

        runTest {
            assertEquals(
                ThemeDataResponse(
                    listOf(
                        ThemeColorsDataResponse(
                            name = "Dark Salmon",
                            isDark = true,
                            primaryColor = "#FFFFCDD2",
                            onPrimaryColor = "#FF4E342E",
                            surfaceColor = "#FF212121",
                            onSurfaceColor = "#FFffffff",
                            errorColor = "#FFE57373",
                            onErrorColor = "#FF4E342E",
                        ),
                        ThemeColorsDataResponse(
                            name = "Light Blue",
                            isDark = false,
                            primaryColor = "#ffEA5C5A",
                            onPrimaryColor = "#FFffffff",
                            surfaceColor = "#ffCDECF9",
                            onSurfaceColor = "#FF030204",
                            errorColor = "#FFE57373",
                            onErrorColor = "#FF212121",
                        ),
                        ThemeColorsDataResponse(
                            name = "Matt D'av Ella",
                            isDark = false,
                            primaryColor = "#ffE35638",
                            onPrimaryColor = "#FFFADACA",
                            surfaceColor = "#ffFBF8EC",
                            onSurfaceColor = "#FF24242C",
                            errorColor = "#FFE57373",
                            onErrorColor = "#FF212121",
                        ),
                        ThemeColorsDataResponse(
                            name = "Google",
                            isDark = false,
                            primaryColor = "#ff4285F4",
                            onPrimaryColor = "#FFffffff",
                            surfaceColor = "#ffffffff",
                            onSurfaceColor = "#FF212121",
                            errorColor = "#FFE57373",
                            onErrorColor = "#FF212121",
                        ),
                        ThemeColorsDataResponse(
                            name = "Spotify",
                            isDark = true,
                            primaryColor = "#ff1FDF64",
                            onPrimaryColor = "#FF212121",
                            surfaceColor = "#ff212121",
                            onSurfaceColor = "#FFFFFFFF",
                            errorColor = "#FFE57373",
                            onErrorColor = "#FF212121",
                        )
                    )
                ),
                portfolioApi.getThemeData()
            )
        }
    }
     */
}