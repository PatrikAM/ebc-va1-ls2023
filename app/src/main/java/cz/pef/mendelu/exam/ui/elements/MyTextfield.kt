package cz.pef.mendelu.exam.ui.elements

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTextField(
    value: String,
    label: String,
    onValueChange: (String) -> Unit,
    error: String,
    enabled: Boolean
){
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label) },
        maxLines = 1,
        enabled = enabled,
        modifier = Modifier.fillMaxWidth()
    )

    if (error.isNotEmpty()){
        Text(text = error)
    }


}