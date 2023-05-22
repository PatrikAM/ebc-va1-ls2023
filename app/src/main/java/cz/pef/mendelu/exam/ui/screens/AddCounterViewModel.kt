package cz.pef.mendelu.exam.ui.screens

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.core.text.isDigitsOnly
import cz.pef.mendelu.exam.architecture.BaseViewModel
import cz.pef.mendelu.exam.database.ILocalRepository
import cz.pef.mendelu.exam.xmichl.R
import kotlinx.coroutines.launch

class AddCounterViewModel(private val repository: ILocalRepository)
    : BaseViewModel(), AddCounterActions {

    var counterId: Long? = null
    var data: AddCounterScreenData = AddCounterScreenData()


    val addCounterUIState: MutableState<AddCounterUIState> =
        mutableStateOf(AddCounterUIState.Loading)

    override fun saveCounter() {
        var inBounds: Boolean = true
        if (data.counter.name.isEmpty()) data.counterNameError = R.string.can_not_be_empty
        if (data.counter.currentValue == null) data.counterCurrentValueError = R.string.can_not_be_empty
        if (data.counter.maximumValue != null && data.counter.currentValue != null) {
            if (data.counter.maximumValue!! < data.counter.currentValue!!) {
                data.counterMaximumValueError = R.string.current_value_large
                inBounds = false
            }
        }

        if (data.counter.name.isEmpty() || data.counter.currentValue == null || !inBounds){
            // error
            addCounterUIState.value = AddCounterUIState.CounterChanged
        } else {
            launch {
                if (counterId == null){
                    val id = repository.insert(data.counter)
                    if (id > 0) {
                        addCounterUIState.value = AddCounterUIState.CounterSaved
                    } else {
                        // error
                    }
                }
            }
        }
    }

    override fun deleteCounter() {
        launch {
            repository.delete(data.counter)
            addCounterUIState.value = AddCounterUIState.CounterDeleted
        }
    }

    override fun onNameChanged(text: String) {
        data.counter.name = text
        addCounterUIState.value = AddCounterUIState.CounterChanged
    }

    override fun onCurrentValueChanged(currentValue: String) {
        if (currentValue.isEmpty()) {
            data.counter.currentValue = null
            addCounterUIState.value = AddCounterUIState.CounterChanged
        } else {

            if (currentValue.isDigitsOnly()) {
                data.counter.currentValue = currentValue.toInt()
            }
            addCounterUIState.value = AddCounterUIState.CounterChanged
        }
    }

    override fun onMinimumValueChanged(minimumValue: String) {
        if (minimumValue.isEmpty()) {
            data.counter.minimumValue = null
            addCounterUIState.value = AddCounterUIState.CounterChanged
        } else {

            if (minimumValue.isDigitsOnly()) {
                data.counter.minimumValue = minimumValue.toInt()
            }
            addCounterUIState.value = AddCounterUIState.CounterChanged
        }    }

    override fun onMaximumValueChanged(maximumValue: String) {
        if (maximumValue.isEmpty()) {
            data.counter.maximumValue = null
            addCounterUIState.value = AddCounterUIState.CounterChanged
        } else {

            if (maximumValue.isDigitsOnly()) {
                data.counter.maximumValue = maximumValue.toInt()
            }
            addCounterUIState.value = AddCounterUIState.CounterChanged
        }
    }


    fun initCounter(){
        if (counterId != null){
            launch {
                data.counter = repository.getCounterById(counterId!!)
                data.loading = false
                addCounterUIState.value = AddCounterUIState.CounterChanged
            }
        } else {
            data.loading = false
            addCounterUIState.value = AddCounterUIState.CounterChanged
        }

    }


}