package cz.mendelu.pef.va1.xmichl.meminiapp.ui.elements.memoryList

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import cz.mendelu.pef.va1.xmichl.meminiapp.models.Memory
import cz.mendelu.pef.va1.xmichl.meminiapp.utils.DateUtils
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemoryRow(
    memory: Memory
) {
    Row {
        ListItem(
            headlineText = {
                Text(text = memory.title)
            },
            leadingContent = {
                Icon(
                    imageVector = Icons.Default.Image,
                    contentDescription = null
                )
            },
            supportingText = {
                Text(text = DateUtils.getDateString(memory.date))
//                Text(text = "date")
            }
        )
    }
}