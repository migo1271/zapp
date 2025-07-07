package de.christinecoenen.code.zapp.tv2.live

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import de.christinecoenen.code.zapp.R
import de.christinecoenen.code.zapp.app.livestream.ui.ProgramInfoViewModel
import de.christinecoenen.code.zapp.models.channels.ChannelModel
import de.christinecoenen.code.zapp.models.channels.json.JsonChannelList
import de.christinecoenen.code.zapp.tv2.main.navigation.Location
import de.christinecoenen.code.zapp.tv2.theme.TvPreview
import org.koin.androidx.compose.koinViewModel

class LiveScreenLocation : Location(
	titleResId = R.string.activity_main_tab_live,
	isMainTab = true,
)

@TvPreview
@Composable
fun LiveScreen(
	programInfoViewModel: ProgramInfoViewModel = koinViewModel(),
	onChannelClick: (channel: ChannelModel) -> Unit = {},
) {

	val context = LocalContext.current
	val channels = rememberSaveable { JsonChannelList(context).list }
	var selectedChannelIndex by remember { mutableIntStateOf(0) }
	val selectedChannel = channels[selectedChannelIndex]

	LaunchedEffect(selectedChannel.id) {
		selectedChannel.let { programInfoViewModel.setChannelId(it.id) }
	}

	val title by programInfoViewModel.titleFlow.collectAsStateWithLifecycle("")
	val subtitle by programInfoViewModel.subtitleFlow.collectAsStateWithLifecycle(null)
	val description by programInfoViewModel.descriptionFlow.collectAsStateWithLifecycle(null)
	val time by programInfoViewModel.timeFlow.collectAsStateWithLifecycle(null)

	Box(
		modifier = Modifier.fillMaxWidth()
	) {
		StreamPreviewImage(
			streamUrl = selectedChannel.streamUrl,
			modifier = Modifier
				.width(758.dp)
				.align(Alignment.TopEnd)
		)
	}

	Column(
		verticalArrangement = Arrangement.Bottom,
		modifier = Modifier.fillMaxSize()
	) {
		ChannelInfo(
			showTitle = title,
			showSubtitle = subtitle,
			description = description,
			time = time,
			modifier = Modifier
				.fillMaxWidth(0.7f)
				.padding(horizontal = 58.dp)
		)

		ChannelList(
			channels = channels,
			selectedChannelIndex = selectedChannelIndex,
			onChannelClick = { index -> onChannelClick(channels[index]) },
			onChannelSelected = { index -> selectedChannelIndex = index },
		)
	}
}


