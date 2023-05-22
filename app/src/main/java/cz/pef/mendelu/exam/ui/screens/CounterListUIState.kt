package cz.pef.mendelu.exam.ui.screens

import cz.pef.mendelu.exam.model.Counter

sealed class CounterListUIState {
    object Default : CounterListUIState()
    class Success(val counters: List<Counter>) : CounterListUIState()
}