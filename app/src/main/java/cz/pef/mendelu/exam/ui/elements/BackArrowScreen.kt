package cz.pef.mendelu.exam.ui.elements

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BackArrowScreen(
    topBarTitle: String,
    onBackClick: () -> Unit,
    actions: @Composable RowScope.() -> Unit = {},
    drawFullScreenContent: Boolean = false,
    content: @Composable (paddingValues: PaddingValues) -> Unit
){
    Scaffold(topBar = {
        TopAppBar(
            actions = actions,
            title = {
                Text(text = topBarTitle)
            },
            navigationIcon = {
                IconButton(onClick = { onBackClick() }) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "")
                }

            })
    }) {
        if (!drawFullScreenContent) {
            LazyColumn(modifier = Modifier.padding(it)) {
                item {
                    content(it)
                }
            }
        } else {
            content(it)
        }
    }
}