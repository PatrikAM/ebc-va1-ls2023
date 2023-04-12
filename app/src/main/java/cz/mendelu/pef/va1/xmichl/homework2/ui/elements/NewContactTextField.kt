package cz.mendelu.pef.va1.xmichl.homework2.ui.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.materialIcon
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults.textFieldColors
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewContactTextField(
    value : String,
    label: String,
    icon: ImageVector,
    onValueChange: (String) -> Unit,
    error: String
) {
    OutlinedTextField(
        value = value,
        leadingIcon = {
            Icon(
                imageVector = icon,
                contentDescription = "Icon",
                //tint = if (isFocused) MaterialTheme.colors.primary else MaterialTheme.colors.onSurface
            )
        },
        onValueChange = onValueChange,
        label = { Text(text = label) },
        maxLines = 1,
        modifier = Modifier.fillMaxWidth()
    )
    if (error.isNotEmpty()){
        Text(text = error, modifier = Modifier.background(Color.Red))
    }

}