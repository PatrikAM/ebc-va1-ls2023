package cz.mendelu.pef.va1.xmichl.meminiapp.ui.elements

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import cz.mendelu.pef.va1.xmichl.meminiapp.navigation.INavigationRouter

@Composable
fun AddEditMemoryFAB(navigation: INavigationRouter) {
    FloatingActionButton(
        onClick = {
            navigation.navigateAddEditMemoryScreen(-1L)
        }) {
        Icon(Icons.Default.Add, contentDescription = "Add")
    }
}