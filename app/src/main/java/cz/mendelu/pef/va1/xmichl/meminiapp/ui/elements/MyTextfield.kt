package cz.mendelu.pef.va1.xmichl.meminiapp.ui.elements

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalFocusManager
import cz.mendelu.pef.va1.xmichl.meminiapp.ui.theme.basicMargin

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTextfield(
    value: String,
    onValueChange: (String) -> Unit,
    leadingIcon: ImageVector,
    label: String,
    onClearClick: () -> Unit,
) {

    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        trailingIcon = {
            if (value != "") {
                IconButton(onClick = {
                    focusManager.clearFocus()
                    onClearClick()
                }) {
                    Icon(
                        painter = rememberVectorPainter(
                            image = Icons.Default
                                .Clear
                        ),
                        contentDescription = null
                    )
                }
            }
        },
        leadingIcon = {
            Icon(
                imageVector = leadingIcon,
                contentDescription = null
            )
        },
        label = {
            Text(text = label)
        },
        modifier = Modifier.fillMaxSize().padding(bottom = basicMargin())
    )
}