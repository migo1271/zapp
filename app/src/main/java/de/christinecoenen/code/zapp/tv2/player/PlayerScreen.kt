package de.christinecoenen.code.zapp.tv2.player

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import de.christinecoenen.code.zapp.tv2.theme.TvPreview

@TvPreview
@Composable
fun PlayerScreen() {
	Text(
		text = "Player!",
		color = MaterialTheme.colorScheme.onSurface,
		style = MaterialTheme.typography.headlineLarge,
		modifier = Modifier.fillMaxSize()
	)
}
