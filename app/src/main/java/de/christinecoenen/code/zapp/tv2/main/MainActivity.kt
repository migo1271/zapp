package de.christinecoenen.code.zapp.tv2.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import de.christinecoenen.code.zapp.tv2.about.AboutScreen
import de.christinecoenen.code.zapp.tv2.about.MediaCenterScreen
import de.christinecoenen.code.zapp.tv2.live.LiveScreen
import de.christinecoenen.code.zapp.tv2.theme.AppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppTheme {
                val navController = rememberNavController()

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .fillMaxWidth()
                ) {

                    TopNavigation(
                        onTabSelected = { index ->
                            navController.navigate(
                                route = TopNavigationViewModel.getRoute(index)
                            )
                        },
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )

                    Box(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        NavHost(
                            navController = navController,
                            startDestination = TopNavigationViewModel.Live
                        ) {
                            composable<TopNavigationViewModel.Live> { LiveScreen() }
                            composable<TopNavigationViewModel.MediaCenter> { MediaCenterScreen() }
                            composable<TopNavigationViewModel.About> { AboutScreen() }
                        }
                    }
                }
            }
        }
    }

}
