package cz.mendelu.pef.va1.xmichl.meminiapp.ui.elements

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.asIntState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import cz.mendelu.pef.va1.xmichl.meminiapp.R
import cz.mendelu.pef.va1.xmichl.meminiapp.dataStore.UserSettingsStore
import cz.mendelu.pef.va1.xmichl.meminiapp.navigation.INavigationRouter

@Composable
fun AddEditMemoryFAB(navigation: INavigationRouter) {
    val store = UserSettingsStore(LocalContext.current)
    val theColor = store.getUserColor.collectAsState(initial = R.color.memini_default)

    FloatingActionButton(
        containerColor = colorResource(id = theColor.value),
        onClick = {
            navigation.navigateAddEditMemoryScreen(-1L)
        }) {
        Icon(Icons.Default.Add, contentDescription = "Add")
    }
}