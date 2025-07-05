package de.christinecoenen.code.zapp.tv2.main.navigation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest

class NavigationViewModel : ViewModel() {

    companion object {
        val MainTabScreens = Screen.entries.filter { it.isMainTabScreen }
    }

    private val _backstack = MutableStateFlow(listOf(MainTabScreens.first()))

    val currentScreen = _backstack.map { it.last() }

    val mainTabTitleResIds
        get() = MainTabScreens.map { it.titleResId!! }

    @OptIn(ExperimentalCoroutinesApi::class)
    val currentSelectedTabIndex = currentScreen
        .mapLatest { MainTabScreens.indexOf(it) }

    fun selectMainTab(index: Int) {
        // main tabs always clear back stack
        _backstack.value = listOf(MainTabScreens[index])
    }

    // TODO: add support for parameters
    fun showScreen(screen: Screen) {
        _backstack.value += screen
    }
}
