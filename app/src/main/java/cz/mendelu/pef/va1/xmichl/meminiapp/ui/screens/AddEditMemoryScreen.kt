package cz.mendelu.pef.va1.xmichl.meminiapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notes
import androidx.compose.material.icons.filled.Title
import androidx.compose.material.icons.filled.Today
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cz.mendelu.pef.va1.xmichl.meminiapp.R
import cz.mendelu.pef.va1.xmichl.meminiapp.navigation.Destination
import cz.mendelu.pef.va1.xmichl.meminiapp.navigation.INavigationRouter
import cz.mendelu.pef.va1.xmichl.meminiapp.ui.elements.MyTextfield
import cz.mendelu.pef.va1.xmichl.meminiapp.ui.elements.screenSkeletons.NavScreen

@Composable
fun AddEditMemoryScreen(
    navigation: INavigationRouter,
    //id: Long?
) {
    NavScreen(
        appBarTitle = "Add Edit", //TODO: memory title
        destination = Destination.AddEditMemoryScreen, //TODO: prev or prevprev destination
        navigation = navigation,
        backArrowNeeded = true,
        onBackClick = {
            navigation.returnBack()
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(0.9f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row {
                Box(modifier = Modifier.fillMaxSize(0.25f)) {
                    Image(
                        painter = painterResource(id = R.drawable.photo_place_holder),
                        contentDescription = null
                    )
                }
                Box(modifier = Modifier.fillMaxSize(0.33f)) {
                    Image(
                        painter = painterResource(id = R.drawable.photo_place_holder),
                        contentDescription = null
                    )
                }
                Box(modifier = Modifier.fillMaxSize(0.5f)) {
                    Image(
                        painter = painterResource(id = R.drawable.photo_place_holder),
                        contentDescription = null
                    )
                }
            }
            //Spacer(modifier = Modifier.height(20.dp))
            //Divider(thickness = 2.dp)
            Spacer(modifier = Modifier.height(50.dp))
            MyTextfield(
                value = "",
                onValueChange = {},
                leadingIcon = Icons.Default.Title,
                label = "Title"
            )
            MyTextfield(
                value = "",
                onValueChange = {},
                leadingIcon = Icons.Default.Today,
                label = "Date"
            )
            MyTextfield(
                value = "",
                onValueChange = {},
                leadingIcon = Icons.Default.LocationOn,
                label = "Place"
            )
            MyTextfield(
                value = "",
                onValueChange = {},
                leadingIcon = Icons.Default.Notes,
                label = "Description"
            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = { /*TODO*/ }
            ) {
                Text(text = "Save")
            }
        }
    }
}