package cz.mendelu.pef.va1.xmichl.golf.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import cz.mendelu.pef.va1.xmichl.golf.model.Golfist
import cz.mendelu.pef.va1.xmichl.golf.navigation.INavigationRouter
import cz.mendelu.pef.va1.xmichl.golf.ui.elements.BackArrowScreen
import org.koin.androidx.compose.getViewModel

@Composable
fun ListResultScreen (navigation: INavigationRouter,
                      viewModel: ListResultViewModel = getViewModel()) {

    val golfists = remember { mutableStateListOf<Golfist>() }

    viewModel.listResultUIState.value.let {
        when(it){
            ListResultUIState.Default -> {
                viewModel.loadGolfists()
            }
            is ListResultUIState.Success -> {
                golfists.clear()
                golfists.addAll(it.golfists)
            }
        }
    }

    BackArrowScreen(
        fullScreenContent = true,
        appBarTitle = "List of results",
        onBackClick = { navigation.returnBack() }) {

        ResultScreenContent(golfists, it)

    }
}

@Composable
fun ResultScreenContent(golfists: List<Golfist>, paddingValues: PaddingValues) {


    LazyColumn(modifier = Modifier.padding(paddingValues)){
        golfists.forEach {
            item(key = it.id) {
                GolfistRow(golfist = it)
            }
        }
    }
}

@Composable
fun GolfistRow(golfist: Golfist) {
    Column {
        Text(text = golfist.name)
        Text(text = golfist.name)

    }
}
