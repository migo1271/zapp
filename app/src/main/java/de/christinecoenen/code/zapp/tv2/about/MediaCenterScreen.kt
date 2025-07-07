package de.christinecoenen.code.zapp.tv2.about

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.tv.material3.Button
import androidx.tv.material3.Text
import de.christinecoenen.code.zapp.R
import de.christinecoenen.code.zapp.tv2.main.navigation.Location
import de.christinecoenen.code.zapp.tv2.theme.TvPreview

class MediaCenterScreenLocation : Location(
	titleResId = R.string.activity_main_tab_mediathek,
	isMainTab = true,
)

@TvPreview
@Composable
fun MediaCenterScreen(
	onButtonClick: () -> Unit = {},
) {
	Column {
		listOf(1, 2, 3, 4, 5).forEach {
			Button(
				onClick = onButtonClick
			) {
				Text(it.toString())
			}
		}
	}
}
