package cz.pef.mendelu.exam.navigation

sealed class Destination(val route: String) {
    object CounterListScreen : Destination(route = "counter_list")
    object AddCounterScreen : Destination(route = "add_counter")
}
