package de.christinecoenen.code.zapp.tv2.main

import androidx.compose.foundation.focusGroup
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRestorer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Tab
import androidx.tv.material3.TabRow
import androidx.tv.material3.Text
import de.christinecoenen.code.zapp.tv2.theme.AppTheme
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun TopNavigation(
    modifier: Modifier = Modifier,
    viewModel: TopNavigationViewModel = koinViewModel(),
    onTabSelected: (index: Int) -> Unit = {}
) {
    AppTheme {
        val selectedTab by viewModel.selectedTab

        TabRow(
            selectedTabIndex = selectedTab,
            modifier = modifier
                .padding(top = 32.dp, bottom = 16.dp)
                .focusGroup()
                .focusRestorer()
        ) {
            viewModel.tabStringIds.forEachIndexed { index, tabResId ->
                Tab(
                    selected = viewModel.isSelected(index),
                    onFocus = {
                        viewModel.select(index)
                        onTabSelected(index)
                    },
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
