package cz.pef.mendelu.exam.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Remove
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import cz.pef.mendelu.exam.model.Counter
import cz.pef.mendelu.exam.navigation.INavigationRouter
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CounterListScreen(
    navigation: INavigationRouter,
    viewModel: CounterListViewModel = getViewModel()
) {

    val counters = remember { mutableStateListOf<Counter>() }

    viewModel.counterListUIState.value.let {
        when (it) {
            CounterListUIState.Default -> {
                viewModel.loadCounters()
            }
            is CounterListUIState.Success -> {
                counters.clear()
                counters.addAll(it.counters)
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = "Counters")
            })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navigation.navigateToAddCounterScreen(-1L)
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }
    ) {
        CounterListScreenContent(
            paddingValues = it,
            counters = counters,
            navigation = navigation,
            actions = viewModel
        )
    }
}

@Composable
fun CounterListScreenContent(
    paddingValues: PaddingValues,
    counters: List<Counter>,
    navigation: INavigationRouter,
    actions: CounterListViewModel
) {
    LazyColumn(modifier = Modifier.padding(paddingValues)) {
        counters.forEach {
            item(key = it.id) {
                CounterRow(
                    counter = it,
                    actions = actions,
                    onClick = {
                        navigation.navigateToAddCounterScreen(it.id)
                    }
                )
            }
        }
    }
}

@Composable
fun CounterRow(
    counter: Counter,
    actions: CounterListViewModel,
    onClick: () -> Unit,
) {

    Row(modifier = Modifier
        .fillMaxWidth()
        .clickable {
            onClick()
        }) {
        IconButton(onClick = {
            if (counter.maximumValue != null) {
                if (counter.currentValue!! - 1 <= counter.maximumValue!!) {
                    actions.changeCurrentValue(counter.id!!, counter.currentValue!! + 1)
                }
            } else {
                actions.changeCurrentValue(counter.id!!, counter.currentValue!! + 1)
            }
        }) {
            Icon(
                imageVector = Icons.Rounded.Add,
                contentDescription = null
            )
        }

        Text(text = counter.name, textAlign = TextAlign.Center)
        IconButton(onClick = {
            if (counter.minimumValue != null) {
                if (counter.currentValue!! - 1 >= counter.minimumValue!!) {
                    actions.changeCurrentValue(counter.id!!, counter.currentValue!! - 1)
                }
            } else {
                actions.changeCurrentValue(counter.id!!, counter.currentValue!! - 1)
            }
        }) {
            Icon(
                imageVector = Icons.Rounded.Remove,
                contentDescription = null,
            )
        }
    }
}
