package cz.pef.mendelu.exam.ui.screens

sealed class AddCounterUIState {
    object Default : AddCounterUIState()
    object CounterSaved : AddCounterUIState()
    object CounterDeleted : AddCounterUIState()
    object CounterChanged : AddCounterUIState()
    object Loading : AddCounterUIState()
}
