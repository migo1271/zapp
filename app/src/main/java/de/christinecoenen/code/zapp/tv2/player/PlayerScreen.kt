package de.christinecoenen.code.zapp.tv2.player

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.ui.PlayerView
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
	val context = LocalContext.current

	LaunchedEffect(Unit) {
		player.load(videoInfo)
		player.resume()
	}

	DisposableEffect(Unit) {
		onDispose {
			player.destroy()
		}
	}

	AndroidView(
		factory = {
			PlayerView(context)
				.apply {
					player.setView(this)
					useController = false
				}
		},
	)

	Text(
		text = videoInfo.title,
		color = MaterialTheme.colorScheme.onSurface,
		style = MaterialTheme.typography.headlineLarge,
		modifier = Modifier.fillMaxSize()
	)
}
