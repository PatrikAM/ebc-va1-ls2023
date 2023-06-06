import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import cz.mendelu.pef.va1.xmichl.meminiapp.R

@Composable
fun ConfirmationAlertDialog(
    message: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    val openDialog = remember { mutableStateOf(true) }

    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = {
                openDialog.value = false
                onDismiss()
            },
            title = { Text(text = stringResource(R.string.confirmation)) },
            text = { Text(text = message) },
            confirmButton = {
                TextButton(onClick = {
                    openDialog.value = false
                    onConfirm()
                }) {
                    Text(text = stringResource(R.string.confirm))
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    openDialog.value = false
                    onDismiss()
                }) {
                    Text(text = stringResource(R.string.no))
                }
            }
        )
    }
}