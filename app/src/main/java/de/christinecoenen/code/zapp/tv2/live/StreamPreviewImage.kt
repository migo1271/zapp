package de.christinecoenen.code.zapp.tv2.live

import android.content.Context
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import androidx.tv.material3.MaterialTheme
import de.christinecoenen.code.zapp.R
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

val fadeDuration = 750.milliseconds

fun initializeExoPlayer(context: Context): ExoPlayer {
	return ExoPlayer.Builder(context).build()
}

@Composable
fun StreamPreviewImage(
	modifier: Modifier = Modifier,
	streamUrl: String
) {
	val context = LocalContext.current

	var isVisible by remember { mutableStateOf(false) }

	val bgColor = MaterialTheme.colorScheme.surface.toArgb()
	val alpha by animateFloatAsState(
		targetValue = if (isVisible) 1f else 0f,
		animationSpec = tween(
			durationMillis = fadeDuration.inWholeMilliseconds.toInt(),
			easing = FastOutSlowInEasing,
		)
	)

	val player = remember {
		initializeExoPlayer(context).apply {
			addListener(object : Player.Listener {
				override fun onRenderedFirstFrame() {
					isVisible = true
				}
			})
		}
	}

	LaunchedEffect(streamUrl) {
		isVisible = false
		delay(fadeDuration)

		player.apply {
			stop()
			removeMediaItem(0)
			// TODO: suppress subtitles
			setMediaItem(MediaItem.fromUri(streamUrl))
			prepare()
		}
	}

	DisposableEffect(Unit) {
		onDispose {
			player.release()
		}
	}

	Box(
		modifier = modifier.aspectRatio(16 / 9f),
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
			},
			modifier = Modifier.alpha(alpha),
		)
		Image(
			painter = painterResource(R.drawable.scrim),
			contentScale = ContentScale.FillBounds,
			contentDescription = null,
			modifier = Modifier.fillMaxSize()
		)
	}
}
