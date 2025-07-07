package de.christinecoenen.code.zapp.tv2.player

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import de.christinecoenen.code.zapp.app.player.VideoInfo
import de.christinecoenen.code.zapp.tv2.main.navigation.Location

data class PlayerLocation(
	val videoInfo: VideoInfo,
) : Location()

@Composable
fun PlayerScreen(
	videoInfo: VideoInfo,
	onCloseClick: () -> Unit = {}
) {
	Button(
		onClick = onCloseClick
	) {

	}
	Text(
		text = videoInfo.title,
		color = MaterialTheme.colorScheme.onSurface,
		style = MaterialTheme.typography.headlineLarge,
		modifier = Modifier.fillMaxSize()
	)
}
