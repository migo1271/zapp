package de.christinecoenen.code.zapp.tv2.player

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import de.christinecoenen.code.zapp.tv2.theme.TvPreview

@TvPreview
@Composable
fun ControllerOverlay(
	title: String = "My Test Title",
	isVisible: Boolean = true,
) {
	if (!isVisible) {
		return
	}

	Box(
		modifier = Modifier
			.fillMaxWidth()
			.height(50.dp)
			.background(MaterialTheme.colorScheme.scrim)
	) {
		Text(
			text = title,
			color = MaterialTheme.colorScheme.onSurface,
			style = MaterialTheme.typography.headlineLarge,
			modifier = Modifier.fillMaxSize()
		)
	}
}
