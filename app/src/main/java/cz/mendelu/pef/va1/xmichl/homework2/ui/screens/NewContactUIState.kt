package cz.mendelu.pef.va1.xmichl.homework2.ui.screens

sealed class NewContactUIState {
    object Default : NewContactUIState()
    object ContactSaved : NewContactUIState()
    object ContactChanged : NewContactUIState()
    object ContactWasNotSaved : NewContactUIState()
    //object Loading : NewContactUIState()
}