package cz.mendelu.pef.va1.xmichl.homework2.ui.elements

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactTextField(
    value: String,
    label: String,
    icon: ImageVector,
    onValueChange: (String) -> Unit,
    error: String,
    numberInput: Boolean = false
) {
    OutlinedTextField(
        value = value,
        supportingText = {
            if (error.isNotEmpty())
                Text(text = error)
            else
                Text(text = "")
        },
        isError = error.isNotEmpty(),
        leadingIcon = {
            Icon(
                imageVector = icon,
                contentDescription = "Icon",
                //tint = if (isFocused) MaterialTheme.colors.primary else MaterialTheme.colors.onSurface
            )
        },
        keyboardOptions =
                if (numberInput)
                    KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone)
                else
                    KeyboardOptions.Default,
        onValueChange = onValueChange,
        label = { Text(text = label) },
        maxLines = 1,
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .padding(top = 10.dp)
    )
}