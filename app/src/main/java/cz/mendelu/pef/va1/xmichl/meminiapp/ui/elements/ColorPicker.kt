package cz.mendelu.pef.va1.xmichl.meminiapp.ui.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import cz.mendelu.pef.va1.xmichl.meminiapp.R

@Composable
fun ColorPicker(
    onColorSelected: (Int) -> Unit,
    onColorConfirmed: (Int) -> Unit,
    color: Int
) {
    val showDialog = remember { mutableStateOf(false) }
    val selectedColor = remember { mutableIntStateOf(R.color.memini_default) }

    if (showDialog.value) {
        Dialog(onDismissRequest = { showDialog.value = false }) {
            Column(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
                    .padding(16.dp)
            ) {
                Text(text = stringResource(R.string.pick_a_color))

                Spacer(modifier = Modifier.height(16.dp))

                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                    ColorOption(
                        color = R.color.memini_blue,
                        selectedColor = selectedColor.intValue,
                        onColorSelected = { color ->
                            onColorSelected(color)
                            selectedColor.intValue = color
                        }
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    ColorOption(
                        color = R.color.memini_green,
                        selectedColor = selectedColor.intValue,
                        onColorSelected = { color ->
                            onColorSelected(color)
                            selectedColor.intValue = color
                        }
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    ColorOption(
                        color = R.color.memini_pink,
                        selectedColor = selectedColor.intValue,
                        onColorSelected = { color ->
                            onColorSelected(color)
                            selectedColor.intValue = color
                        }
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    MyButton(
                        text = stringResource(id = R.string.confirm),
                        onClick = {
                            onColorConfirmed(selectedColor.intValue)
                            showDialog.value = false
                        })
                }
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Row(modifier = Modifier.fillMaxWidth()) {

                Text(stringResource(R.string.button_color))

                ColorOption(
                    color = color,
                    selectedColor = color,
                    onColorSelected = { showDialog.value = true }
                )
            }
        }
    }
}

@Composable
fun ColorOption(
    color: Int,
    selectedColor: Int,
    onColorSelected: (Int) -> Unit
) {
    val isSelected = color == selectedColor

    Box(
        modifier = Modifier
            .size(40.dp)
            .background(color = colorResource(id = color), shape = MaterialTheme.shapes.small)
            .padding(4.dp)
            .clickable { onColorSelected(color) }
            .border(
                width = if (isSelected) 2.dp else 0.dp,
                color =
                if (isSelected)
                    MaterialTheme.colorScheme.onSurface
                else
                    colorResource(id = color),
                shape = MaterialTheme.shapes.small
            )
    )
}