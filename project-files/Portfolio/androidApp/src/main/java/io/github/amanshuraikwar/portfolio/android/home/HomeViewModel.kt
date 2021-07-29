package io.github.amanshuraikwar.portfolio.android.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.amanshuraikwar.portfolio.PortfolioRepository
import io.github.amanshuraikwar.portfolio.android.theme.ThemeState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

private const val TAG = "HomeViewModel"

class HomeViewModel : ViewModel() {
    private val portfolioRepository = PortfolioRepository()

    private val _isDarkTheme = MutableStateFlow(portfolioRepository.isDarkThemeEnabled())
    val isDarkTheme = _isDarkTheme as StateFlow<Boolean>

    private val _screenState = MutableStateFlow<HomeScreenState>(HomeScreenState.Fetching)
    val screenState = _screenState as StateFlow<HomeScreenState>

    private val themeData = portfolioRepository.getThemeData()

    private val _themeState = MutableStateFlow(
        ThemeState(
            lightColor = themeData.value.lightTheme,
            darkColors = themeData.value.darkTheme,
        )
    )
    val themeState = _themeState as StateFlow<ThemeState>

    private val errorHandler = CoroutineExceptionHandler { _, th ->
        Log.e(TAG, "errorHandler: $th", th)
    }
    private val coroutineContext = Dispatchers.Default + errorHandler

    init {
        fetchData()

        viewModelScope.launch(coroutineContext) {
            themeData
                .collect { themeData ->
                    _themeState.value = ThemeState(
                        lightColor = themeData.lightTheme,
                        darkColors = themeData.darkTheme,
                    )
                }
        }
    }

    private fun fetchData() {
        viewModelScope.launch(coroutineContext) {
            _screenState.value = HomeScreenState.Success(
                portfolioRepository.getPortfolioData()
            )
        }
    }

    fun setDarkThemeEnabled(enabled: Boolean) {
        portfolioRepository.setDarkThemeEnabled(enabled)
        _isDarkTheme.value = enabled
    }
}