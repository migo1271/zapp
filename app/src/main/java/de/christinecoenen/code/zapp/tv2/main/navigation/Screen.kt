package de.christinecoenen.code.zapp.tv2.main.navigation

import de.christinecoenen.code.zapp.R

enum class Screen(
	val titleResId: Int? = null,
) {
	LIVE(
		titleResId = R.string.activity_main_tab_live
	),

	MEDIA_CENTER(
		titleResId = R.string.activity_main_tab_mediathek
	),

	ABOUT(
		titleResId = R.string.menu_about_short
	),
}
