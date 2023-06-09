package cz.mendelu.pef.va1.xmichl.meminiapp.ui.screens

sealed class SettingsUIState {
    object Default : SettingsUIState()
    object SettingsChanged : SettingsUIState()
    object Loading : SettingsUIState()
    class Success(val color: Int) : SettingsUIState()
}