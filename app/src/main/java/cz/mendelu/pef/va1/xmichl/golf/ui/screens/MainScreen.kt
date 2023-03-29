package cz.mendelu.pef.va1.xmichl.golf.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cz.mendelu.pef.va1.xmichl.golf.navigation.INavigationRouter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navigation: INavigationRouter) {
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = "Main Screen")
            })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navigation.navigateToAddScreen()
            }) {

            }
        }
    ) {
        MainScreenContent(
            paddingValues = it,
            navigation = navigation
        )
    }
}

@Composable
fun MainScreenContent(
    paddingValues: PaddingValues,
    navigation: INavigationRouter
    //golf player
) {

    Column(modifier = Modifier.padding(paddingValues)) {
        Text(text = "Who has best score:")
        Button(
            onClick = { navigation.navigateToListResultScreen() }) {
            Text(text = "List of words")
            
        }
    }
}
