package cz.mendelu.pef.va1.xmichl.homework2.ui.elements

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewContactTextField(
    value : String,
    label: String,
    onValueChange: (String) -> Unit,
    error: String
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label) },
        maxLines = 1,
        modifier = Modifier.fillMaxWidth()
    )
    if (error.isNotEmpty()){
        Text(text = error)
    }

}