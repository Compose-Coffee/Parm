package kr.co.main.accountbook.content

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import kr.co.main.accountbook.main.CircleProgress
import kr.co.main.accountbook.model.AccountBookDialog
import kr.co.main.accountbook.model.contentDateFormat
import kr.co.main.accountbook.model.formatNumber
import kr.co.main.accountbook.model.getDisplay
import kr.co.main.accountbook.model.getTransactionType
import kr.co.ui.icon.DreamIcon
import kr.co.ui.icon.dreamicon.Arrowleft
import kr.co.ui.theme.Paddings
import kr.co.ui.theme.Shapes
import kr.co.ui.theme.colors
import kr.co.ui.theme.typo
import kr.co.ui.widget.DreamCenterTopAppBar
import kr.co.ui.widget.LoadingShimmerEffect


@Composable
internal fun AccountBookContentRoute(
    viewModel: AccountBookContentViewModel = hiltViewModel(),
    navigationToUpdate: (Long?) -> Unit,
    navigationTopAccountBook: () -> Unit,
    popBackStack: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()
    val isDeleteLoading by viewModel.isDeleteLoading.collectAsStateWithLifecycle(initialValue = false)

    LaunchedEffect(Unit) {
        viewModel.complete.collect {
            navigationTopAccountBook()
        }
    }
    AccountBookContentScreen(
        state = state,
        isLoading = isLoading,
        isDeleteLoading = isDeleteLoading,
        navigationToUpdate = navigationToUpdate,
        popBackStack = popBackStack,
        onRemoveItem = viewModel::deleteAccountBookById,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = Paddings.large),
    )
}

@Composable
internal fun AccountBookContentScreen(
    state: AccountBookContentViewModel.State = AccountBookContentViewModel.State(),
    isLoading: Boolean,
    isDeleteLoading: Boolean,
    navigationToUpdate: (Long?) -> Unit,
    popBackStack: () -> Unit,
    onRemoveItem: () -> Unit = {},
    modifier: Modifier
) {
    var showDropDownMenu by remember { mutableStateOf(false) }
    var showDeleteDialog by remember { mutableStateOf(false) }

    Surface(
        color = MaterialTheme.colors.white
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            DreamCenterTopAppBar(
                title = "장부 상세",
                navigationIcon = {
                    IconButton(onClick = popBackStack) {
                        Icon(
                            imageVector = DreamIcon.Arrowleft,
                            contentDescription = null,
                            tint = Color.Black
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { showDropDownMenu = true }) {
                        Icon(
                            imageVector = Icons.Filled.MoreVert,
                            contentDescription = "설정",
                            tint = Color.Black
                        )
                    }
                    DropdownMenu(
                        expanded = showDropDownMenu,
                        onDismissRequest = { showDropDownMenu = false },
                        modifier = Modifier.background(MaterialTheme.colors.white)
                    ) {
                        DropdownMenuItem(
                            text = {
                                Row {
                                    Text("수정하기")
                                    Spacer(modifier = Modifier.width(8.dp))
                                }
                            },
                            onClick = {
                                navigationToUpdate(state.id)
                                showDropDownMenu = false
                            }
                        )
                        DropdownMenuItem(
                            text = {
                                Row {
                                    Text("삭제하기")
                                    Spacer(modifier = Modifier.width(8.dp))
                                }
                            },
                            onClick = {
                                showDeleteDialog = true
                                showDropDownMenu = false
                            }
                        )
                    }
                },
                colorBackground = true
            )
            LazyColumn(
                modifier = Modifier
                    .padding(Paddings.xlarge),
                verticalArrangement = Arrangement.spacedBy(Paddings.extra)
            ) {
                if (isLoading && isDeleteLoading.not()) {
                    items(5) {
                        Column {
                            LoadingShimmerEffect {
                                ShimmerGridItem(brush = it)
                            }
                        }
                    }
                } else {
                    item {
                        Column {
                            Text(
                                text = state.transactionType.getTransactionType(),
                                style = MaterialTheme.typo.h4,
                                color = MaterialTheme.colors.gray1,
                                modifier = Modifier
                                    .padding(bottom = Paddings.large)
                            )
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    text = "${formatNumber(state.amount)}원",
                                    style = MaterialTheme.typo.h2,
                                    color = MaterialTheme.colors.gray1
                                )
                            }
                        }
                    }

                    item {
                        Row(
                            modifier = modifier,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text(
                                text = "카테고리",
                                style = MaterialTheme.typo.h4,
                                modifier = Modifier
                                    .padding(end = Paddings.xxlarge)
                            )
                            Box(
                                modifier = Modifier
                                    .height(32.dp)
                                    .border(
                                        1.dp,
                                        MaterialTheme.colors.gray7,
                                        shape = Shapes.medium
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = state.category.getDisplay(),
                                    style = MaterialTheme.typo.body1,
                                    color = MaterialTheme.colors.gray2,
                                    modifier = Modifier.padding(horizontal = Paddings.large)
                                )
                            }
                        }
                    }

                    item {
                        Row(
                            modifier = modifier,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text(
                                text = "내역",
                                style = MaterialTheme.typo.h4,
                            )
                            Spacer(modifier = Modifier.width(20.dp))
                            Text(
                                text = state.title.takeIf { !it.isNullOrEmpty() }
                                    ?: "입력된 내역이 없습니다.",
                                style = MaterialTheme.typo.body1,
                                color = if (state.title.isNullOrBlank()) MaterialTheme.colors.gray6 else MaterialTheme.colors.gray2
                            )
                        }
                    }

                    item {
                        Row(
                            modifier = modifier,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text(
                                text = "날짜",
                                style = MaterialTheme.typo.h4,
                            )
                            Spacer(modifier = Modifier.width(20.dp))
                            Text(
                                text = state.registerDateTime?.contentDateFormat() ?: "",
                                style = MaterialTheme.typo.body1,
                                color = MaterialTheme.colors.gray2
                            )
                        }
                    }

                    item {
                        Column {
                            Text(
                                text = "사진",
                                style = MaterialTheme.typo.h4,
                                modifier = Modifier
                                    .padding(bottom = Paddings.large)
                            )
                            state.imageUrls.forEach {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clip(Shapes.medium)
                                        .padding(vertical = Paddings.medium),
                                    contentAlignment = Alignment.Center,
                                ) {
                                    AsyncImage(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clip(Shapes.medium),
                                        model = it,
                                        contentDescription = "image",
                                        contentScale = ContentScale.Crop,
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
        if (isDeleteLoading) {
            CircleProgress()
        }
    }

    if (showDeleteDialog) {
        AccountBookDialog(
            onDismissRequest = { showDeleteDialog = false },
            title = "삭제 확인",
            message = "정말 삭제하시겠습니까?",
            onConfirm = {
                onRemoveItem()
            }
        )
    }

}

@Composable
internal fun ShimmerGridItem(brush: Brush) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = Paddings.xextra),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(
            modifier = Modifier
                .height(32.dp)
                .weight(1f)
                .background(brush)
        )
        Spacer(modifier = Modifier.width(Paddings.xxlarge))
        Spacer(
            modifier = Modifier
                .height(32.dp)
                .weight(2f)
                .background(brush)
        )
    }
}
