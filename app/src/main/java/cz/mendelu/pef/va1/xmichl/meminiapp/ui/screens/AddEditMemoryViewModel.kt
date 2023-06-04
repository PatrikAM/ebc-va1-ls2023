package cz.mendelu.pef.va1.xmichl.meminiapp.ui.screens

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import cz.mendelu.pef.va1.xmichl.meminiapp.MeminiApp
import cz.mendelu.pef.va1.xmichl.meminiapp.architecture.BaseViewModel
import cz.mendelu.pef.va1.xmichl.meminiapp.database.IMemoriesRepository
import kotlinx.coroutines.launch

class AddEditMemoryViewModel(private val repository: IMemoriesRepository) : BaseViewModel(),
    AddEditMemoryActions {

    var memoryId: Long? = null
    var data: AddEditScreenData = AddEditScreenData()
    var dateEror: Int? = null
    var titleError: Int? = null
    var primaryPhotoError: Int? = null


    val addEditMemoryUIState: MutableState<AddEditScreenUIState> =
        mutableStateOf(AddEditScreenUIState.Loading)

    override fun saveMemory() {
        if (data.memory.title.isEmpty()) {
            // error
            // TODO check date and primary photo also
            addEditMemoryUIState.value = AddEditScreenUIState.MemoryChanged
        } else {
            launch {
                if (memoryId == null) {
                    val id = repository.insert(data.memory)
                    if (id > 0) {
                        addEditMemoryUIState.value = AddEditScreenUIState.MemorySaved
                    } else {
                        // error
                    }
                } else {
                    repository.update(data.memory)
                    addEditMemoryUIState.value = AddEditScreenUIState.MemorySaved
                }
            }
        }
    }

    override fun onTitleChanged(title: String) {
        Log.d("aaaa", "ddddd")
        data.memory.title = title
        addEditMemoryUIState.value = AddEditScreenUIState.MemoryChanged
    }

    override fun onDateChanged(date: Long) {
        data.memory.date = date
        addEditMemoryUIState.value = AddEditScreenUIState.MemoryChanged
    }

    override fun onPhotoPickerExit(uri: Uri?) {

        when (data.photoIndex) {
            0 -> {
                //            data.memory.primaryPhoto =
            }
            1 -> {
                //            data.memory.primaryPhoto =
            }
            2 -> {
                //            data.memory.primaryPhoto =
            }
        }
        addEditMemoryUIState.value = AddEditScreenUIState.MemoryChanged
    }

    override fun onPhotoPickerStart(index: Int) {
        data.photoIndex = index
    }

    override fun onLocationChanged(latitude: Double?, longitude: Double?) {
        if (latitude == null)  {
            Log.d("onLocationChanged", "lat null")
        }

        if (longitude == null)  {
            Log.d("onLocationChanged", "lon null")
        }
        data.memory.latitude = latitude
        data.memory.longitude = longitude
        Log.d("hasLocation", data.memory.hasLocation().toString())
        addEditMemoryUIState.value = AddEditScreenUIState.MemoryChanged
    }

    fun initMemory() {
        if (memoryId != null) {
            launch {
                data.memory = repository.getMemoryById(memoryId!!)
                data.loading = false
                addEditMemoryUIState.value = AddEditScreenUIState.MemoryChanged
            }
        } else {
            data.loading = false
            addEditMemoryUIState.value = AddEditScreenUIState.MemoryChanged
        }
    }

    override fun onDescriptionChanged(desc: String?) {
        data.memory.description = desc
        addEditMemoryUIState.value = AddEditScreenUIState.MemoryChanged
    }
}
