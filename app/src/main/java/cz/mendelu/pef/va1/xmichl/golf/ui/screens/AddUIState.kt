package cz.mendelu.pef.va1.xmichl.golf.ui.screens

sealed class AddUIState {
    object Default : AddUIState()
    object GolfistSaved : AddUIState()
}
