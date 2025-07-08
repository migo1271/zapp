package de.christinecoenen.code.zapp.tv2.player

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.media3.ui.compose.PlayerSurface
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import de.christinecoenen.code.zapp.app.player.Player
import de.christinecoenen.code.zapp.app.player.VideoInfo
import de.christinecoenen.code.zapp.tv2.main.navigation.Location
import org.koin.compose.koinInject

data class PlayerLocation(
	val videoInfo: VideoInfo,
) : Location()

@Composable
fun PlayerScreen(
	videoInfo: VideoInfo,
	player: Player = koinInject<Player>(),
) {
	LaunchedEffect(Unit) {
		player.load(videoInfo)
		player.resume()
	}

	DisposableEffect(Unit) {
		onDispose {
			player.destroy()
		}
	}

	PlayerSurface(
		player = player.exoPlayer
	)

	Text(
		text = videoInfo.title,
		color = MaterialTheme.colorScheme.onSurface,
		style = MaterialTheme.typography.headlineLarge,
		modifier = Modifier.fillMaxSize()
	)
}
