package de.christinecoenen.code.zapp.tv2.main.navigation

import kotlinx.serialization.Serializable

@Serializable
open class Location(
	val titleResId: Int? = null,
	val isMainTab: Boolean = false,
)
