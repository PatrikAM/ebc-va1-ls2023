package cz.mendelu.pef.va1.xmichl.meminiapp.ui.elements

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import cz.mendelu.pef.va1.xmichl.meminiapp.ui.theme.basicMargin

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun MyTextfield(
    value: String,
    onValueChange: (String) -> Unit,
    leadingIcon: ImageVector,
    label: String,
    singleLine: Boolean = false,
    onClearClick: () -> Unit,
    charLimit: Int? = null,
    textError: Int? = null
) {

    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        value = value,
        onValueChange =
        if ((charLimit != null && value.length <= charLimit) || charLimit == null)
            { it -> if (value.length != charLimit || value.length > it.length) onValueChange(it) }
        else
            { _ -> },
        keyboardOptions =
        if (charLimit != null)
            KeyboardOptions.Default.copy(imeAction = ImeAction.Done)
        else
            KeyboardOptions()
        ,
        keyboardActions = KeyboardActions(onDone = {
            if (charLimit != null) {
                focusManager.clearFocus()
            }
        }),
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
        singleLine = singleLine,
        leadingIcon = {
            Icon(
                imageVector = leadingIcon,
                contentDescription = null
            )
        },
        label = {
            Text(text = label)
        },
        supportingText = {
            Row {
                textError?.let {
                    if (value.isEmpty()) {
                        Text(
                            text = stringResource(id = textError),
                            color = Color.Red
                        )
                    }
                }
                charLimit?.let {
                    Text(
                        text = "${value.length} / $charLimit",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.End,
                    )
                }
            }
        },
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = basicMargin())
    )
}