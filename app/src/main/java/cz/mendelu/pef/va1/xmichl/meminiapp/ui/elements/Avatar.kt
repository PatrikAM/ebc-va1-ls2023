package cz.mendelu.pef.va1.xmichl.meminiapp.ui.elements

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable

@Composable
fun Avatar() {
    IconButton(onClick = {
        //TODO: avatar onclick
    }) {
        Icon(
            imageVector = Icons.Outlined.AccountCircle,
            contentDescription = "avatar",
            )
        Menu()
    }
}