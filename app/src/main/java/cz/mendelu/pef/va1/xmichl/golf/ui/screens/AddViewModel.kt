package cz.mendelu.pef.va1.xmichl.golf.ui.screens

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import cz.mendelu.pef.va1.xmichl.golf.architecture.BaseViewModel
import cz.mendelu.pef.va1.xmichl.golf.database.IGolfistsRepository
import cz.mendelu.pef.va1.xmichl.golf.model.Golfist
import kotlinx.coroutines.launch

class AddViewModel(private val repository: IGolfistsRepository)
    : BaseViewModel(), AddActions {

    val addUIState: MutableState<AddUIState> =
        mutableStateOf(AddUIState.Default)

    override fun saveGolfist(name: String, score: String) {
        launch {
            val id = repository.insert(Golfist(name = name, score = score))
            if (id > 0) {
                addUIState.value = AddUIState.GolfistSaved
            } else {
                // error
            }
        }
    }


}