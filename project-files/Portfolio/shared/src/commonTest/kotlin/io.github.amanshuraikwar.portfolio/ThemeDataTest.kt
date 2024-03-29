@file:Suppress("IllegalIdentifier")

package io.github.amanshuraikwar.portfolio

import com.russhwolf.settings.MockSettings
import io.github.amanshuraikwar.portfolio.theme.ThemeRepository
import io.github.amanshuraikwar.portfolio.theme.model.ThemeColorsData
import io.github.amanshuraikwar.portfolio.theme.model.ThemeData
import io.github.amanshuraikwar.portfolio.theme.network.ThemeApi
import io.github.amanshuraikwar.portfolio.theme.network.model.ThemeColorsDataResponse
import io.github.amanshuraikwar.portfolio.theme.network.model.ThemeDataResponse
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.utils.io.ByteReadChannel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

@ExperimentalCoroutinesApi
class ThemeDataTest {
    //region api
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
                respond(
                    content = ByteReadChannel(""),
                    status = HttpStatusCode.OK,
                    headers = headersOf(HttpHeaders.ContentType, "application/json")
                )
            }
        }

        val themeApi = ThemeApi(
            Factory.createHttpClient(
                engine = mockEngine,
                enableNetworkLogs = true
            )
        )


        runTest {
            assertEquals(
                DEFAULT_THEME_DATA_RESPONSE,
                themeApi.getThemeData()
            )
        }
    }

    @Test
    @JsName("verifyRemoteJsonSerializationIsWorking")
    fun `verify remote json serialization is working`() {
        val themeApi = ThemeApi(
            Factory.createHttpClient(enableNetworkLogs = true)
        )

        runTest {
            assertEquals(
                true,
                themeApi.getThemeData().themes.isNotEmpty()
            )
        }
    }

    @Test
    @JsName("verifyRemoteJsonColorStringsAreAccurate")
    fun `verify remote json color strings are accurate`() {
        val themeApi = ThemeApi(
            Factory.createHttpClient(enableNetworkLogs = true)
        )

        runTest {
            assertEquals(
                true,
                themeApi
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
    //endregion

    //region theme colors name
    @Test
    @JsName("defaultSelectedThemeColorsNameAndTheThemeExists")
    fun `default selected theme colors name and the theme exists`() {
        val themeRepository = ThemeRepository(
            ThemeApi(
                Factory.createHttpClient(enableNetworkLogs = true)
            ),
            settings = MockSettings(),
            defaultThemeColorsName = DEFAULT_THEME_DATA.themes[0].name,
            defaultThemeData = DEFAULT_THEME_DATA
        )

        runTest {
            assertEquals(
                DEFAULT_THEME_DATA.themes[0].name.replace(" ", "").lowercase(),
                themeRepository.getSelectedThemeColorsName().value
            )
        }
    }

    @Test
    @JsName("defaultSelectedThemeColorsNameIsFirstFromTheDefaultThemeDataWhenTheThemeDoesNotExist")
    fun `default selected theme colors name is first from the default theme data when the theme does not exist`() {
        val themeRepository = ThemeRepository(
            ThemeApi(
                Factory.createHttpClient(enableNetworkLogs = true)
            ),
            settings = MockSettings(),
            defaultThemeColorsName = "Halufaluja Theme",
            defaultThemeData = DEFAULT_THEME_DATA
        )

        runTest {
            assertNotEquals(
                "halufalujatheme",
                themeRepository.getSelectedThemeColorsName().value
            )
            assertEquals(
                DEFAULT_THEME_DATA.themes[0].name.replace(" ", "").lowercase(),
                themeRepository.getSelectedThemeColorsName().value
            )
        }
    }

    @Test
    @JsName("selectedThemeColorsNameWhenApiReturnsValidListAndExistingSelectedThemeColorsNameExistsInTheNewThemeDataList")
    fun `selected theme colors name when api returns valid list and existing selected theme colors name exists in the new theme data list`() {
        val singleThemeDataResponse = ThemeDataResponse(
            themes = listOf(
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
                    name = "Dark Salmon",
                    isDark = true,
                    primaryColor = "#FFFFCDD2",
                    onPrimaryColor = "#FF4E342E",
                    surfaceColor = "#FF212121",
                    onSurfaceColor = "#FFffffff",
                    errorColor = "#FFE57373",
                    onErrorColor = "#FF4E342E",
                ),
            )
        )

        val mockEngine = MockEngine {
            respond(
                content = ByteReadChannel(
                    Json.encodeToString(
                        ThemeDataResponse.serializer(),
                        singleThemeDataResponse
                    )
                ),
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }

        val themeApi = ThemeApi(
            Factory.createHttpClient(
                engine = mockEngine,
                enableNetworkLogs = true
            )
        )

        val themeRepository = ThemeRepository(
            settings = MockSettings(),
            themeApi = themeApi,
            defaultThemeColorsName = DEFAULT_THEME_DATA.themes[0].name,
            defaultThemeData = DEFAULT_THEME_DATA
        )

        runTest {
            assertEquals(
                DEFAULT_THEME_DATA.themes[0]
                    .name
                    .replace(" ", "")
                    .lowercase(),
                themeRepository.getSelectedThemeColorsName().first()
            )
        }
    }

    @Test
    @JsName("setSelectedThemeColorsNameFlowEmitIsCorrect")
    fun `set selected theme colors name flow emit is correct`() {
        val themeRepository = ThemeRepository(
            ThemeApi(
                Factory.createHttpClient(enableNetworkLogs = true)
            ),
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
                    themeRepository.getSelectedThemeColorsName().first()
                )
            }
            themeRepository.setSelectedThemeColorsName(
                DEFAULT_THEME_DATA.themes[2].name
            )
            job.join()
        }
    }

    @Test
    @JsName("setSelectedThemeColorsNameCorrespondingThemeDoesNotExist")
    fun `set selected theme colors name corresponding theme does not exist`() {
        val themeRepository = ThemeRepository(
            ThemeApi(
                Factory.createHttpClient(enableNetworkLogs = true)
            ),
            settings = MockSettings(),
            defaultThemeColorsName = DEFAULT_THEME_DATA.themes[0].name,
            defaultThemeData = DEFAULT_THEME_DATA
        )

        runTest {
            themeRepository.setSelectedThemeColorsName(
                "Halufaluja Theme"
            )
            assertNotEquals(
                "halufalujatheme",
                themeRepository.getSelectedThemeColorsName().value
            )
            assertEquals(
                DEFAULT_THEME_DATA.themes[0].name.replace(" ", "").lowercase(),
                themeRepository.getSelectedThemeColorsName().value
            )
        }
    }
    //endregion

    //region theme colors
    @Test
    @JsName("defaultSelectedThemeColorsAndTheThemeExists")
    fun `default selected theme colors and the theme exists`() {
        val themeRepository = ThemeRepository(
            ThemeApi(
                Factory.createHttpClient(enableNetworkLogs = true)
            ),
            settings = MockSettings(),
            defaultThemeColorsName = DEFAULT_THEME_DATA.themes[0].name,
            defaultThemeData = DEFAULT_THEME_DATA
        )

        runTest {
            assertEquals(
                DEFAULT_THEME_DATA.themes[0],
                themeRepository.getSelectedThemeColors().value
            )
        }
    }

    @Test
    @JsName("defaultSelectedThemeColorsIsFirstFromTheDefaultThemeDataWhenTheThemeDoesNotExist")
    fun `default selected theme colors is first from the default theme data when the theme does not exist`() {
        val themeRepository = ThemeRepository(
            ThemeApi(
                Factory.createHttpClient(enableNetworkLogs = true)
            ),
            settings = MockSettings(),
            defaultThemeColorsName = "Halufaluja Theme",
            defaultThemeData = DEFAULT_THEME_DATA
        )

        runTest {
            assertNotEquals(
                "halufalujatheme",
                themeRepository.getSelectedThemeColorsName().value
            )
            assertEquals(
                DEFAULT_THEME_DATA.themes[0],
                themeRepository.getSelectedThemeColors().value
            )
        }
    }

    @Test
    @JsName("selectedThemeColorsWhenApiReturnsValidListAndExistingSelectedThemeColorsNameExistsInTheNewThemeDataList")
    fun `selected theme colors when api returns valid list and existing selected theme colors name exists in the new theme data list`() {
        val singleThemeDataResponse = ThemeDataResponse(
            themes = listOf(
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
                    name = "Dark Salmon",
                    isDark = true,
                    primaryColor = "#FFFFCDD2",
                    onPrimaryColor = "#FF4E342E",
                    surfaceColor = "#FF212121",
                    onSurfaceColor = "#FFffffff",
                    errorColor = "#FFE57373",
                    onErrorColor = "#FF4E342E",
                ),
            )
        )

        val mockEngine = MockEngine {
            respond(
                content = ByteReadChannel(
                    Json.encodeToString(
                        ThemeDataResponse.serializer(),
                        singleThemeDataResponse
                    )
                ),
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }

        val themeApi = ThemeApi(
            Factory.createHttpClient(
                engine = mockEngine,
                enableNetworkLogs = true
            )
        )

        val themeRepository = ThemeRepository(
            settings = MockSettings(),
            themeApi = themeApi,
            defaultThemeColorsName = DEFAULT_THEME_DATA.themes[0].name,
            defaultThemeData = DEFAULT_THEME_DATA
        )

        runTest {
            assertEquals(
                DEFAULT_THEME_DATA.themes[0],
                themeRepository.getSelectedThemeColors().first()
            )
        }
    }

    @Test
    @JsName("setSelectedThemeColorsFlowEmitIsCorrect")
    fun `set selected theme colors flow emit is correct`() {
        val themeRepository = ThemeRepository(
            ThemeApi(
                Factory.createHttpClient(enableNetworkLogs = true)
            ),
            settings = MockSettings(),
            defaultThemeColorsName = DEFAULT_THEME_DATA.themes[0].name,
            defaultThemeData = DEFAULT_THEME_DATA
        )

        runTest {
            val job = launch {
                assertEquals(
                    DEFAULT_THEME_DATA.themes[2],
                    themeRepository.getSelectedThemeColors().first()
                )
            }
            themeRepository.setSelectedThemeColorsName(
                DEFAULT_THEME_DATA.themes[2].name
            )
            job.join()
        }
    }

    @Test
    @JsName("setSelectedThemeColorsCorrespondingThemeDoesNotExist")
    fun `set selected theme colors corresponding theme does not exist`() {
        val themeRepository = ThemeRepository(
            ThemeApi(
                Factory.createHttpClient(enableNetworkLogs = true)
            ),
            settings = MockSettings(),
            defaultThemeColorsName = DEFAULT_THEME_DATA.themes[0].name,
            defaultThemeData = DEFAULT_THEME_DATA
        )

        runTest {
            themeRepository.setSelectedThemeColorsName(
                "Halufaluja Theme"
            )
            assertEquals(
                DEFAULT_THEME_DATA.themes[0],
                themeRepository.getSelectedThemeColors().value
            )
        }
    }
    //endregion

    //region theme data
    @Test
    @JsName("defaultThemeData")
    fun `default theme data`() {
        val mockEngine = MockEngine {
            respond(
                content = ByteReadChannel(""),
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }

        val themeApi = ThemeApi(
            Factory.createHttpClient(
                engine = mockEngine,
                enableNetworkLogs = true
            )
        )

        val themeRepository = ThemeRepository(
            settings = MockSettings(),
            themeApi = themeApi,
            defaultThemeColorsName = DEFAULT_THEME_DATA.themes[0].name,
            defaultThemeData = DEFAULT_THEME_DATA
        )

        runTest {
            assertEquals(
                DEFAULT_THEME_DATA,
                themeRepository.getThemeData().value
            )
        }
    }

    @Test
    @JsName("selectedThemeDataWhenApiReturnsEmptyList")
    fun `selected theme data when api returns empty list`() {
        var apiResponded = false
        val mockEngine = MockEngine {
            respond(
                content = ByteReadChannel(
                    Json.encodeToString(
                        ThemeDataResponse.serializer(),
                        ThemeDataResponse(
                            themes = emptyList()
                        )
                    ),
                ),
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            ).apply {
                apiResponded = true
            }
        }

        val themeApi = ThemeApi(
            Factory.createHttpClient(
                engine = mockEngine,
                enableNetworkLogs = true
            )
        )

        val themeRepository = ThemeRepository(
            settings = MockSettings(),
            themeApi = themeApi,
            defaultThemeColorsName = DEFAULT_THEME_DATA.themes[0].name,
            defaultThemeData = DEFAULT_THEME_DATA
        )

        runTest {
            val themeDataFlow = themeRepository.getThemeData()
            while (true) {
                if (apiResponded) {
                    delay(300)
                    break
                }
                delay(300)
            }
            assertEquals(
                DEFAULT_THEME_DATA,
                themeDataFlow.value
            )
        }
    }

    @Test
    @JsName("selectedThemeDataWhenApiReturnsValidListAndExistingSelectedThemeColorsNameExistsInTheNewThemeDataList")
    fun `selected theme data when api returns valid list and existing selected theme colors name exists in the new theme data list`() {
        val singleThemeDataResponse = ThemeDataResponse(
            themes = listOf(
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
                    name = "Dark Salmon",
                    isDark = true,
                    primaryColor = "#FFFFCDD2",
                    onPrimaryColor = "#FF4E342E",
                    surfaceColor = "#FF212121",
                    onSurfaceColor = "#FFffffff",
                    errorColor = "#FFE57373",
                    onErrorColor = "#FF4E342E",
                ),
            )
        )

        val mockEngine = MockEngine {
            respond(
                content = ByteReadChannel(
                    Json.encodeToString(
                        ThemeDataResponse.serializer(),
                        singleThemeDataResponse
                    )
                ),
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }

        val themeApi = ThemeApi(
            Factory.createHttpClient(
                engine = mockEngine,
                enableNetworkLogs = true
            )
        )

        val themeRepository = ThemeRepository(
            settings = MockSettings(),
            themeApi = themeApi,
            defaultThemeColorsName = DEFAULT_THEME_DATA.themes[0].name,
            defaultThemeData = DEFAULT_THEME_DATA
        )

        runTest {
            assertEquals(
                ThemeData(
                    themes = singleThemeDataResponse.themes.map {
                        ThemeColorsData(
                            name = it.name,
                            isDark = it.isDark,
                            primaryColor = it.primaryColor,
                            onPrimaryColor = it.onPrimaryColor,
                            surfaceColor = it.surfaceColor,
                            onSurfaceColor = it.onSurfaceColor,
                            errorColor = it.errorColor,
                            onErrorColor = it.onErrorColor,
                        )
                    }
                ),
                themeRepository.getThemeData().first { it != DEFAULT_THEME_DATA }
            )

            assertEquals(
                DEFAULT_THEME_DATA.themes[0]
                    .name
                    .replace(" ", "")
                    .lowercase(),
                themeRepository.getSelectedThemeColorsName().first()
            )
        }
    }

    @Test
    @JsName("selectedThemeDataWhenApiReturnsValidListAndExistingSelectedThemeColorsNameDoesNotExistInTheNewThemeDataList")
    fun `selected theme data when api returns valid list and existing selected theme colors name does not exist in the new theme data list`() {
        val singleThemeDataResponse = ThemeDataResponse(
            themes = listOf(
                ThemeColorsDataResponse(
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

        val mockEngine = MockEngine {
            respond(
                content = ByteReadChannel(
                    Json.encodeToString(
                        ThemeDataResponse.serializer(),
                        singleThemeDataResponse
                    )
                ),
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }

        val themeApi = ThemeApi(
            Factory.createHttpClient(
                engine = mockEngine,
                enableNetworkLogs = true
            )
        )

        val themeRepository = ThemeRepository(
            settings = MockSettings(),
            themeApi = themeApi,
            defaultThemeColorsName = DEFAULT_THEME_DATA.themes[0].name,
            defaultThemeData = DEFAULT_THEME_DATA
        )

        runTest {
            assertEquals(
                ThemeData(
                    themes = singleThemeDataResponse.themes.map {
                        ThemeColorsData(
                            name = it.name,
                            isDark = it.isDark,
                            primaryColor = it.primaryColor,
                            onPrimaryColor = it.onPrimaryColor,
                            surfaceColor = it.surfaceColor,
                            onSurfaceColor = it.onSurfaceColor,
                            errorColor = it.errorColor,
                            onErrorColor = it.onErrorColor,
                        )
                    }
                ),
                themeRepository.getThemeData().first { it != DEFAULT_THEME_DATA }
            )

            assertEquals(
                singleThemeDataResponse.themes[0]
                    .name
                    .replace(" ", "")
                    .lowercase(),
                themeRepository.getSelectedThemeColorsName().first {
                    it != DEFAULT_THEME_DATA.themes[0]
                        .name
                        .replace(" ", "")
                        .lowercase()
                }
            )
        }
    }
    //endregion

    companion object {
        private fun String.isAccurateColorString(): Boolean {
            return matches(Regex("#([A-Fa-f0-9]){8}"))
        }

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

        private val DEFAULT_THEME_DATA_RESPONSE = ThemeDataResponse(
            themes = DEFAULT_THEME_DATA.themes.map {
                ThemeColorsDataResponse(
                    name = it.name,
                    isDark = it.isDark,
                    primaryColor = it.primaryColor,
                    onPrimaryColor = it.onPrimaryColor,
                    surfaceColor = it.surfaceColor,
                    onSurfaceColor = it.onSurfaceColor,
                    errorColor = it.errorColor,
                    onErrorColor = it.onErrorColor,
                )
            }
        )

        private val MOCK_THEME_DATA_API_RESPONSE =
            Json.encodeToString(ThemeDataResponse.serializer(), DEFAULT_THEME_DATA_RESPONSE)
    }
}