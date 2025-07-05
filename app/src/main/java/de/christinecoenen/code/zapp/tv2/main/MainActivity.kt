package de.christinecoenen.code.zapp.tv2.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.tv.material3.MaterialTheme
import de.christinecoenen.code.zapp.tv2.about.AboutScreen
import de.christinecoenen.code.zapp.tv2.about.MediaCenterScreen
import de.christinecoenen.code.zapp.tv2.live.LiveScreen
import de.christinecoenen.code.zapp.tv2.main.navigation.NavigationViewModel
import de.christinecoenen.code.zapp.tv2.main.navigation.Screen
import de.christinecoenen.code.zapp.tv2.player.PlayerScreen
import de.christinecoenen.code.zapp.tv2.theme.AppTheme
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private val navigationViewModel: NavigationViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.surface)
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize()
                    )
                    {
                        val currentScreen by navigationViewModel.currentScreen
                            .collectAsStateWithLifecycle()
                        val currentSelectedTabIndex by navigationViewModel.currentSelectedTabIndex
                            .collectAsStateWithLifecycle(-1)

                        when (currentScreen) {
                            Screen.LIVE -> LiveScreen(
                                onChannelClick = {
                                    navigationViewModel.showScreen(Screen.PLAYER)
                                }
                            )

                            Screen.MEDIA_CENTER -> MediaCenterScreen()
                            Screen.ABOUT -> AboutScreen()
                            Screen.PLAYER -> PlayerScreen()
                        }

                        if (currentScreen.isMainTabScreen) {
                            TopNavigation(
                                selectedTabIndex = currentSelectedTabIndex,
                                onTabSelected = { index -> navigationViewModel.selectMainTab(index) },
                                tabStringIds = navigationViewModel.mainTabTitleResIds,
                                modifier = Modifier.align(Alignment.TopCenter)
                            )
                        }
                    }
                }
            }
        }
    }

}
