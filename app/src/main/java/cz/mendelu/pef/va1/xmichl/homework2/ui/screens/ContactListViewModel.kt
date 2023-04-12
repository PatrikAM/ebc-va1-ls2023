package cz.mendelu.pef.va1.xmichl.homework2.ui.screens

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import cz.mendelu.pef.va1.xmichl.homework2.architecture.BaseViewModel
import cz.mendelu.pef.va1.xmichl.homework2.database.IContactsRepository
import kotlinx.coroutines.launch

class ContactListViewModel(
    private val repository: IContactsRepository
) : BaseViewModel() {

    val contactListUIState: MutableState<ContactListUIState> =
        mutableStateOf(ContactListUIState.Default)

    fun loadContacts() {
        launch {
            repository.getAll().collect {
                contactListUIState.value = ContactListUIState.Success(it)
            }
        }
    }
}