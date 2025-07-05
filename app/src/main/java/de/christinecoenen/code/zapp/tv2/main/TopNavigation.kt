package de.christinecoenen.code.zapp.tv2.main

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.focusGroup
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.focusRestorer
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Tab
import androidx.tv.material3.TabRow
import androidx.tv.material3.Text
import de.christinecoenen.code.zapp.R
import de.christinecoenen.code.zapp.tv2.theme.AppTheme
import de.christinecoenen.code.zapp.tv2.theme.TvPreview

@TvPreview
@Composable
fun TopNavigation(
    modifier: Modifier = Modifier,
    tabStringIds: List<Int> = listOf(
        R.string.app_name,
        R.string.activity_main_tab_live
    ),
    selectedTabIndex: Int = 0,
    onTabSelected: (index: Int) -> Unit = {}
) {
    AppTheme {
        val focusRequester = remember { FocusRequester() }
        var hasFocus by remember { mutableStateOf(false) }

        BackHandler(selectedTabIndex != 0 || !hasFocus) {
            onTabSelected(0)
            focusRequester.requestFocus()
        }

        LaunchedEffect(Unit) {
            focusRequester.requestFocus()
        }

        TabRow(
            selectedTabIndex = selectedTabIndex,
            modifier = modifier
                .padding(top = 32.dp, bottom = 16.dp)
                .focusGroup()
                .focusRestorer()
                .focusRequester(focusRequester)
                .onFocusChanged { hasFocus = it.hasFocus }
        ) {
            tabStringIds.forEachIndexed { index, tabResId ->
                Tab(
                    selected = index == selectedTabIndex,
                    onFocus = { onTabSelected(index) },
                ) {
                    Text(
                        text = stringResource(tabResId),
                        modifier = Modifier
                            .padding(
                                horizontal = 16.dp,
                                vertical = 10.dp
                            )
                    )
                }
            }
        }
    }
}
