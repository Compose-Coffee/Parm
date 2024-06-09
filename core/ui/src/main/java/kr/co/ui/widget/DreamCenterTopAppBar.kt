package kr.co.ui.widget

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kr.co.ui.theme.NBDreamTheme
import kr.co.ui.theme.typo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DreamCenterTopAppBar(
    modifier: Modifier = Modifier,
    title: String,
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = title,
                style = MaterialTheme.typo.headerB
            )
        },
        navigationIcon = navigationIcon,
        actions = actions
    )
}

@Preview
@Composable
private fun DreamCenterTopAppBarPreview() {
    NBDreamTheme {
        DreamCenterTopAppBar(
            title = "장부",
            navigationIcon = {},
            actions = {}
        )
    }
}