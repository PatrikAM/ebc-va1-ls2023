package cz.mendelu.pef.va1.xmichl.meminiapp.ui.screens

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import cz.mendelu.pef.va1.xmichl.meminiapp.architecture.BaseViewModel
import cz.mendelu.pef.va1.xmichl.meminiapp.dataStore.UserSettingsStore
import kotlinx.coroutines.launch

class SettingsViewModel() : BaseViewModel() {

    val settingsUIState: MutableState<SettingsUIState> =
        mutableStateOf(SettingsUIState.Loading)

    var data: SettingsScreenData = SettingsScreenData()

    fun onColorChanged(color: Int) {
        data.selectedColor = color
        settingsUIState.value = SettingsUIState.SettingsChanged
    }

    fun onColorSaved(context: Context) {
        data.color = data.selectedColor
        val store = UserSettingsStore(context = context)
        launch {
            store.saveUserColor(data.color)
            settingsUIState.value = SettingsUIState.SettingsChanged
        }
    }

    fun initColor(context: Context) {
        val store = UserSettingsStore(context = context)

        launch {
            store.getUserColor.collect() {
                settingsUIState.value = SettingsUIState.Success(it)
            }
        }
    }
}