package cz.mendelu.pef.va1.xmichl.meminiapp.ui.elements

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTextfield(
    value: String,
    onValueChange: (String) -> Unit,
    leadingIcon: ImageVector,
    label: String,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        trailingIcon = {
            //TODO: trailingIcon
            Icon(
                imageVector = Icons.Default.Clear,
                contentDescription = null
            )
        },
        leadingIcon = {
            Icon(
                imageVector = leadingIcon,
                contentDescription = null
            )
        },
        label = {
            Text(text = label) //TODO: extract to resources string
        },
        modifier = Modifier.fillMaxSize()
    )
}