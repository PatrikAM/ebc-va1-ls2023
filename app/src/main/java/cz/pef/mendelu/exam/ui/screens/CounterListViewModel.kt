package cz.pef.mendelu.exam.ui.screens

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import cz.pef.mendelu.exam.architecture.BaseViewModel
import cz.pef.mendelu.exam.database.ILocalRepository
import kotlinx.coroutines.launch

class CounterListViewModel(private val repository: ILocalRepository)
    : BaseViewModel(), CounterListActions {

    val counterListUIState: MutableState<CounterListUIState> =
        mutableStateOf(CounterListUIState.Default)

    fun loadCounters(){
        launch {
            repository.getAll().collect {
                counterListUIState.value = CounterListUIState.Success(it)
            }
        }

    }

    override fun changeCurrentValue(id: Long, currentValue: Int) {
        launch {
            repository.changeCurrentValue(id, currentValue)
        }
    }


}