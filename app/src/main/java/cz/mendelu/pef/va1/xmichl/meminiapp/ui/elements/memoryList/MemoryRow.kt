package cz.mendelu.pef.va1.xmichl.meminiapp.ui.elements.memoryList

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import cz.mendelu.pef.va1.xmichl.meminiapp.models.Memory
import cz.mendelu.pef.va1.xmichl.meminiapp.ui.elements.SquareImageBox
import cz.mendelu.pef.va1.xmichl.meminiapp.utils.DateUtils
import java.io.File
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemoryRow(
    memory: Memory,
    onRowClick: () -> Unit
) {

    Row(modifier = Modifier.clickable(onClick = onRowClick)) {
        ListItem(
            headlineText = {
                Text(
                    text = memory.title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            },
            leadingContent = {
                val imageFile = File(LocalContext.current.filesDir, memory.primaryPhoto)
                SquareImageBox(
                    imageFilePath = imageFile.absolutePath,
                    size = 50.dp
                )
            },
            supportingText = {
                Text(text = DateUtils.getDateString(memory.date))
            }
        )
    }
}