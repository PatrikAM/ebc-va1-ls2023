package cz.mendelu.pef.va1.xmichl.meminiapp.ui.elements.memoryList

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemoryRow() {
    Row {
        ListItem(
            headlineText = {
                Text(text = "Memory Title")
            },
            leadingContent = {
                Icon(
                    imageVector = Icons.Default.Image,
                    contentDescription = null
                )
            },
            supportingText = {
                Text(text = "date")
            }
        )
    }
}