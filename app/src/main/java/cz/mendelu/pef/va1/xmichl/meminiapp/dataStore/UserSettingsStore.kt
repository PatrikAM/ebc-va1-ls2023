package cz.mendelu.pef.va1.xmichl.meminiapp.dataStore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import cz.mendelu.pef.va1.xmichl.meminiapp.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserSettingsStore(private val context: Context) {
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("user_color")
        private val USER_COLOR_KEY = intPreferencesKey("user_color")
    }

    val getUserColor: Flow<Int> = context.dataStore.data.map { preferences ->
        preferences[USER_COLOR_KEY] ?: R.color.memini_default
    }

    suspend fun saveUserColor(color: Int) {
        context.dataStore.edit { preferences ->
            preferences[USER_COLOR_KEY] = color
        }
    }
}
