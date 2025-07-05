package de.christinecoenen.code.zapp.tv2.main.navigation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map

class NavigationViewModel : ViewModel() {

    companion object {
        val MainTabScreens = Screen.entries.filter { it.isMainTabScreen }
    }

    private var _currentScreen = MutableStateFlow(MainTabScreens.first())
    val currentScreen = _currentScreen.asStateFlow()

    val mainTabTitleResIds
        get() = MainTabScreens.map { it.titleResId!! }

    val currentSelectedTabIndex = currentScreen
        .map { MainTabScreens.indexOf(it) }

    fun selectMainTab(index: Int) {
        _currentScreen.value = MainTabScreens[index]
    }

    fun showScreen(screen: Screen) {
        _currentScreen.value = screen
    }
}
