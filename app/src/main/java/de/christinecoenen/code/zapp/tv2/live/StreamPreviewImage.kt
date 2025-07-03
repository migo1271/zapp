package de.christinecoenen.code.zapp.tv2.live

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import androidx.tv.material3.MaterialTheme
import de.christinecoenen.code.zapp.R
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

fun initializeExoPlayer(context: Context): ExoPlayer {
	return ExoPlayer.Builder(context).build()
}

@Composable
fun StreamPreviewImage(streamUrl: String) {
	val context = LocalContext.current
	val player = remember { initializeExoPlayer(context) }
	val bgColor = MaterialTheme.colorScheme.surface.toArgb()

	LaunchedEffect(streamUrl) {
		// TODO: animate out
		delay(1.seconds)
		player.stop()
		// TODO: suppress subtitles
		player.setMediaItem(MediaItem.fromUri(streamUrl))
		player.prepare()
		// TODO: animate in
	}

	DisposableEffect(Unit) {
		onDispose {
			player.release()
		}
	}

	Box(
		modifier = Modifier.aspectRatio(16 / 9f),
	) {
		AndroidView(
			factory = {
				PlayerView(context)
					.apply {
						resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM
						this.player = player
						useController = false
						setBackgroundColor(bgColor)
						setShutterBackgroundColor(bgColor)
					}
			}
		)
		Image(
			painter = painterResource(R.drawable.scrim),
			contentScale = ContentScale.FillBounds,
			contentDescription = null,
			modifier = Modifier.fillMaxSize()
		)
	}
}
