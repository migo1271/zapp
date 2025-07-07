package de.christinecoenen.code.zapp.tv2.about

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import de.christinecoenen.code.zapp.R
import de.christinecoenen.code.zapp.tv2.main.navigation.Location
import de.christinecoenen.code.zapp.tv2.theme.TvPreview

class AboutScreenLocation : Location(
	titleResId = R.string.menu_about_short,
	isMainTab = true,
)

@TvPreview
@Composable
fun AboutScreen() {
	Text(
		text = "About!",
		color = MaterialTheme.colorScheme.onSurface,
		style = MaterialTheme.typography.headlineLarge,
		modifier = Modifier.fillMaxSize()
	)
}
