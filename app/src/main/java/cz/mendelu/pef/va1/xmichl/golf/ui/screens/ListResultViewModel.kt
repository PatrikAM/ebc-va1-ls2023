package cz.mendelu.pef.va1.xmichl.golf.ui.screens


import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import cz.mendelu.pef.va1.xmichl.golf.architecture.BaseViewModel
import cz.mendelu.pef.va1.xmichl.golf.database.IGolfistsRepository
import kotlinx.coroutines.launch

class ListResultViewModel(private val repository: IGolfistsRepository)
    : BaseViewModel() {

    val listResultUIState: MutableState<ListResultUIState> =
        mutableStateOf(ListResultUIState.Default)

    fun loadGolfists(){
        launch {
            repository.getAll().collect {
                listResultUIState.value = ListResultUIState.Success(it)
            }
        }

    }

}