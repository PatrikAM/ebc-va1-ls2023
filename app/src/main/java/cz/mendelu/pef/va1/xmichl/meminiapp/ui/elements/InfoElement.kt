package cz.mendelu.pef.va1.xmichl.meminiapp.ui.elements

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InfoElement(
    value: String?,
    label: String,
    leadingIcon: ImageVector,
    onClick: () -> Unit,
    showClearIcon: Boolean = true,
    onClearClick: () -> Unit = {},

) {

    val interactionSource = remember {
        MutableInteractionSource()
    }
    val isPressed: Boolean by interactionSource.collectIsPressedAsState()

    val focusManager = LocalFocusManager.current

    if (isPressed){
        LaunchedEffect(isPressed){
            onClick()
        }
    }

    OutlinedTextField(
        value = value ?: "",
        onValueChange = {},
        label = { Text(text = label)},
        leadingIcon = {
            Icon(
                imageVector = leadingIcon,
                contentDescription = null)
        },
        trailingIcon = {
            if (value != null && value != "" && showClearIcon) {
                IconButton(onClick = {
                    focusManager.clearFocus()
                    onClearClick()
                }) {
                    Icon(
                        painter = rememberVectorPainter(
                            image = Icons.Default.Clear
                        ),
                        contentDescription = null)
                }
            }
        },
        readOnly = true,
        modifier = Modifier.fillMaxWidth(),
        interactionSource = interactionSource
    )
}