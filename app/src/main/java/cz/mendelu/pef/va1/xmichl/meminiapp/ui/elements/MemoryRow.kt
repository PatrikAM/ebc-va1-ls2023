package cz.mendelu.pef.va1.xmichl.meminiapp.ui.elements

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.ListItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemoryRow() {
    Row {
        Text(text = "date")
        ListItem(
            headlineText = { Text(text = "Memory Title") },
            leadingContent = {
                Text(text = "photo")
            },
            overlineText = { },
            //supportingText = { Text(text = contact.phone_number) },
        )
    }
}