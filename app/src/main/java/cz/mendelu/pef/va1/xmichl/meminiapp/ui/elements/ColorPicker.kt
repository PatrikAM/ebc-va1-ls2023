package cz.mendelu.pef.va1.xmichl.meminiapp.ui.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import cz.mendelu.pef.va1.xmichl.meminiapp.R

@Composable
fun ColorPicker(onColorSelected: (Color) -> Unit) {
    val showDialog = remember { mutableStateOf(false) }
    val selectedColor = remember { mutableStateOf(Color.White) }

    if (showDialog.value) {
        Dialog(onDismissRequest = { showDialog.value = false }) {
            Column(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
                    .padding(16.dp)
            ) {
                Text(text = "Pick a color")

                Spacer(modifier = Modifier.height(16.dp))

                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                ColorOption(
                    color = colorResource(id = R.color.memini_blue),
                    selectedColor = selectedColor.value,
                    onColorSelected = { color ->
                        selectedColor.value = color
                    }
                )

                Spacer(modifier = Modifier.height(8.dp))

                ColorOption(
                    color = colorResource(id = R.color.memini_green),
                    selectedColor = selectedColor.value,
                    onColorSelected = { color ->
                        selectedColor.value = color
                    }
                )

                Spacer(modifier = Modifier.height(8.dp))

                ColorOption(
                    color = colorResource(id = R.color.memini_pink),
                    selectedColor = selectedColor.value,
                    onColorSelected = { color ->
                        selectedColor.value = color
                    }
                )
                    }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(onClick = {
                        onColorSelected(selectedColor.value)
                        showDialog.value = false
                    }) {
                        Text(text = "Select")
                    }
                }
            }
        }
    }

    Column(modifier = Modifier.fillMaxWidth()) {
        TextButton(
            onClick = { showDialog.value = true },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Pick Color")
        }
    }
}

@Composable
fun ColorOption(
    color: Color,
    selectedColor: Color,
    onColorSelected: (Color) -> Unit
) {
    val isSelected = color == selectedColor

    Box(
        modifier = Modifier
            .size(40.dp)
            .background(color = color, shape = MaterialTheme.shapes.small)
            .padding(4.dp)
            .clickable { onColorSelected(color) }
            .border(
                width = if (isSelected) 2.dp else 0.dp,
                color =
                if (isSelected)
                    MaterialTheme.colorScheme.onSurface
                else
                    color,
                shape = MaterialTheme.shapes.small
            )
    )
}