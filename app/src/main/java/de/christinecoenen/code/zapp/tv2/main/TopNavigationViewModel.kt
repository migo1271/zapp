package de.christinecoenen.code.zapp.tv2.main

import androidx.compose.runtime.IntState
import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel
import de.christinecoenen.code.zapp.R
import kotlinx.serialization.Serializable

class TopNavigationViewModel : ViewModel() {

    @Serializable
    object Live

    @Serializable
    object MediaCenter

    @Serializable
    object About

    companion object {
        private val Tabs = mapOf(
            Live to R.string.activity_main_tab_live,
            MediaCenter to R.string.activity_main_tab_mediathek,
            About to R.string.menu_about_short,
        )

        fun getRoute(index: Int) = Tabs.keys.toList()[index]
    }

    private val _selectedTab = mutableIntStateOf(0)
    val selectedTab: IntState = _selectedTab

    val tabStringIds
        get() = Tabs.values

    fun isSelected(index: Int): Boolean {
        return index == _selectedTab.intValue
    }

    fun select(index: Int) {
        _selectedTab.intValue = index
    }
}
