package de.christinecoenen.code.zapp.tv2.main.navigation

import de.christinecoenen.code.zapp.R

enum class Screen(
    val titleResId: Int? = null,
    val isMainTabScreen: Boolean = false,
) {
    LIVE(
        titleResId = R.string.activity_main_tab_live,
        isMainTabScreen = true,
    ),

    MEDIA_CENTER(
        titleResId = R.string.activity_main_tab_mediathek,
        isMainTabScreen = true,
    ),

    ABOUT(
        titleResId = R.string.menu_about_short,
        isMainTabScreen = true,
    ),

    PLAYER,
}
