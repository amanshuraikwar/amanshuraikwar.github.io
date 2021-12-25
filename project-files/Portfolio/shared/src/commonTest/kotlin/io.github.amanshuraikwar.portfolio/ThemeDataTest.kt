@file:Suppress("IllegalIdentifier")

package io.github.amanshuraikwar.portfolio

import com.russhwolf.settings.MockSettings
import io.github.amanshuraikwar.portfolio.model.ThemeColorsData
import io.github.amanshuraikwar.portfolio.model.ThemeData
import io.github.amanshuraikwar.portfolio.network.PortfolioApi
import io.github.amanshuraikwar.portfolio.network.model.ThemeColorsDataResponse
import io.github.amanshuraikwar.portfolio.network.model.ThemeDataResponse
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.utils.io.ByteReadChannel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class ThemeDataTest {
    @Test
    @JsName("verifyJsonSerializationIsCorrect")
    fun `verify json serialization is correct`() {
        val mockEngine = MockEngine { request ->
            if (request.url.toString().endsWith("/theme.json")) {
                respond(
                    content = ByteReadChannel(MOCK_THEME_DATA_API_RESPONSE),
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

    @Test
    @JsName("defaultSelectedThemeColorsNameAndTheThemeExists")
    fun `default selected theme colors name and the theme exists`() {
        val portfolioRepository = PortfolioRepository(
            settings = MockSettings(),
            defaultThemeColorsName = DEFAULT_THEME_DATA.themes[0].name,
            defaultThemeData = DEFAULT_THEME_DATA
        )

        runTest {
            assertEquals(
                DEFAULT_THEME_DATA.themes[0].name.replace(" ", "").lowercase(),
                portfolioRepository.getSelectedThemeColorsName().value
            )
        }
    }

    @Test
    @JsName("defaultSelectedThemeColorsNameIsFirstFromTheDefaultThemeDataWhenTheThemeDoesNotExist")
    fun `default selected theme colors name is first from the default theme data when the theme does not exist`() {
        val portfolioRepository = PortfolioRepository(
            settings = MockSettings(),
            defaultThemeColorsName = "Halufaluja Theme",
            defaultThemeData = DEFAULT_THEME_DATA
        )

        runTest {
            assertNotEquals(
                "halufalujatheme",
                portfolioRepository.getSelectedThemeColorsName().value
            )
            assertEquals(
                DEFAULT_THEME_DATA.themes[0].name.replace(" ", "").lowercase(),
                portfolioRepository.getSelectedThemeColorsName().value
            )
        }
    }

    @Test
    @JsName("setSelectedThemeColorsNameFlowEmitIsCorrect")
    fun `set selected theme colors name flow emit is correct`() {
        val portfolioRepository = PortfolioRepository(
            settings = MockSettings(),
            defaultThemeColorsName = DEFAULT_THEME_DATA.themes[0].name,
            defaultThemeData = DEFAULT_THEME_DATA
        )

        runTest {
            val job = launch {
                assertEquals(
                    DEFAULT_THEME_DATA.themes[2]
                        .name
                        .replace(" ", "").lowercase(),
                    portfolioRepository.getSelectedThemeColorsName().first()
                )
            }
            portfolioRepository.setSelectedThemeColorsName(
                DEFAULT_THEME_DATA.themes[2].name
            )
            job.join()
        }
    }

    @Test
    @JsName("setSelectedThemeColorsNameCorrespondingThemeDoesNotExist")
    fun `set selected theme colors name corresponding theme does not exist`() {
        val portfolioRepository = PortfolioRepository(
            settings = MockSettings(),
            defaultThemeColorsName = DEFAULT_THEME_DATA.themes[0].name,
            defaultThemeData = DEFAULT_THEME_DATA
        )

        runTest {
            portfolioRepository.setSelectedThemeColorsName(
                "Halufaluja Theme"
            )
            assertNotEquals(
                "halufalujatheme",
                portfolioRepository.getSelectedThemeColorsName().value
            )
            assertEquals(
                DEFAULT_THEME_DATA.themes[0].name.replace(" ", "").lowercase(),
                portfolioRepository.getSelectedThemeColorsName().value
            )
        }
    }

    companion object {
        private val DEFAULT_THEME_DATA = ThemeData(
            listOf(
                ThemeColorsData(
                    name = "Dark Salmon",
                    isDark = true,
                    primaryColor = "#FFFFCDD2",
                    onPrimaryColor = "#FF4E342E",
                    surfaceColor = "#FF212121",
                    onSurfaceColor = "#FFffffff",
                    errorColor = "#FFE57373",
                    onErrorColor = "#FF4E342E",
                ),
                ThemeColorsData(
                    name = "Light Blue",
                    isDark = false,
                    primaryColor = "#ffEA5C5A",
                    onPrimaryColor = "#FFffffff",
                    surfaceColor = "#ffCDECF9",
                    onSurfaceColor = "#FF030204",
                    errorColor = "#FFE57373",
                    onErrorColor = "#FF212121",
                ),
                ThemeColorsData(
                    name = "Matt D'av Ella",
                    isDark = false,
                    primaryColor = "#ffE35638",
                    onPrimaryColor = "#FFFADACA",
                    surfaceColor = "#ffFBF8EC",
                    onSurfaceColor = "#FF24242C",
                    errorColor = "#FFE57373",
                    onErrorColor = "#FF212121",
                )
            )
        )

        private val MOCK_THEME_DATA_API_RESPONSE = """ 
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