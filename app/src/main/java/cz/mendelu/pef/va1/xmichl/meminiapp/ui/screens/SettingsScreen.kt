package cz.mendelu.pef.va1.xmichl.meminiapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import cz.mendelu.pef.va1.xmichl.meminiapp.R
import cz.mendelu.pef.va1.xmichl.meminiapp.database.MemoriesDatabase
import cz.mendelu.pef.va1.xmichl.meminiapp.navigation.INavigationRouter
import cz.mendelu.pef.va1.xmichl.meminiapp.ui.elements.ColorPicker
import cz.mendelu.pef.va1.xmichl.meminiapp.ui.elements.screenSkeletons.BackArrowScreen

@Composable
fun SettingsScreen(navigation: INavigationRouter) {
    BackArrowScreen(
        appBarTitle = stringResource(R.string.settings_about),
        onBackClick = { navigation.returnBack() }
    ) {

        val selectedColor: MutableState<Color> = remember {
            mutableStateOf(Color.Blue)
        }

        val ver: Int = MemoriesDatabase
                .getDatabase(LocalContext.current)
            .openHelper
            .readableDatabase
            .version

        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                //TODO: logo
                AsyncImage(model = R.drawable.memini_logo, contentDescription = "")

                Row {
                    Column(modifier = Modifier.padding(horizontal = 10.dp)) {
                        Text("DB version:")
                        Text("Developer:")
                    }
                    Column {
                        Text("$ver")
                        Text(text = "Patrik Michl")
                        Text(text = "patrik.michl@mendelu.cz")
                    }
                }

                Text(text = selectedColor.value.toString())

                ColorPicker(onColorSelected = {
                    selectedColor.value = it
                } )
            }
        }
        
    }
}