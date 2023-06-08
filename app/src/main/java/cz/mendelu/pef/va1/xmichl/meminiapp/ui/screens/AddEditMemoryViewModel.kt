package cz.mendelu.pef.va1.xmichl.meminiapp.ui.screens

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.documentfile.provider.DocumentFile
import cz.mendelu.pef.va1.xmichl.meminiapp.R
import cz.mendelu.pef.va1.xmichl.meminiapp.architecture.BaseViewModel
import cz.mendelu.pef.va1.xmichl.meminiapp.database.IMemoriesRepository
import kotlinx.coroutines.launch
import java.io.FileOutputStream

class AddEditMemoryViewModel(private val repository: IMemoriesRepository) : BaseViewModel(),
    AddEditMemoryActions {

    var memoryId: Long? = null
    var data: AddEditScreenData = AddEditScreenData()
    var dateEror: Int? = null
    var titleError: Int? = null
    var primaryPhotoError: Int? = null


    val addEditMemoryUIState: MutableState<AddEditScreenUIState> =
        mutableStateOf(AddEditScreenUIState.Loading)

    override fun saveMemory(context: Context) {
        if (data.memory.title.isEmpty()) {
            data.titleError = R.string.title_can_not_be_empty
            addEditMemoryUIState.value = AddEditScreenUIState.MemoryChanged
        }

        if (data.primaryPhotoPicked == null) {
            data.photoError = R.string.primary_photo_error
            addEditMemoryUIState.value = AddEditScreenUIState.MemoryChanged
        } else {
            if (data.primaryPhotoPicked != data.memory.primaryPhoto) {
                deleteImage(context, data.memory.primaryPhoto)
                data.memory.primaryPhoto = data.primaryPhotoPicked!!
            }

            if (data.secondaryPhotoPicked != data.memory.photo1) {
                if (data.memory.photo1 != null) {
                    deleteImage(context, data.memory.photo1!!)
                }
                data.memory.photo1 = data.secondaryPhotoPicked
            }


            if (data.ternaryPhotoPicked != data.memory.photo2) {
                if (data.memory.photo2 != null) {
                    deleteImage(context, data.memory.photo2!!)
                }
                data.memory.photo2 = data.ternaryPhotoPicked
            }

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
        data.memory.title = title
        addEditMemoryUIState.value = AddEditScreenUIState.MemoryChanged
    }

    override fun onDateChanged(date: Long) {
        data.memory.date = date
        addEditMemoryUIState.value = AddEditScreenUIState.MemoryChanged
    }

    override fun onPhotoPickerExit(uri: Uri, context: Context) {

        val input = context.contentResolver.openInputStream(uri) ?: return


        val documentFile = DocumentFile.fromSingleUri(context, uri)
        val originalName = documentFile?.name?.substringBeforeLast('.')
        val extension = documentFile?.name?.substringAfterLast('.')

        val fileName = "${originalName}_${System.currentTimeMillis()}.$extension"
        val outputFile = context.filesDir.resolve(fileName)
        input.copyTo(outputFile.outputStream())
        Log.d("file", "${outputFile.exists()}")
        input.close()

        when (data.photoIndex) {
            0 -> {
                data.primaryPhotoPicked = fileName
            }

            1 -> {
                data.secondaryPhotoPicked = fileName
            }

            2 -> {
                data.ternaryPhotoPicked = fileName
            }
        }
        addEditMemoryUIState.value = AddEditScreenUIState.MemoryChanged
    }

    fun deleteAllPhotoHolders(context: Context) {
        if (data.primaryPhotoPicked != null && data.primaryPhotoPicked != data.memory.primaryPhoto) {
            deleteImage(context, data.primaryPhotoPicked!!)
        }
        if (data.secondaryPhotoPicked != null && data.secondaryPhotoPicked != data.memory.photo1) {
            deleteImage(context, data.secondaryPhotoPicked!!)
        }
        if (data.ternaryPhotoPicked != null && data.ternaryPhotoPicked != data.memory.photo2) {
            deleteImage(context, data.ternaryPhotoPicked!!)
        }
    }

    private fun deleteImage(context: Context, imageName: String) {
        val file = context.filesDir.resolve(imageName)
        file.delete()
    }

    override fun onPhotoCleared(context: Context) {

        when (data.photoIndex) {
            0 -> {
                deleteImage(context, data.primaryPhotoPicked!!)
                data.primaryPhotoPicked = null
            }

            1 -> {
                deleteImage(context, data.secondaryPhotoPicked!!)
                data.secondaryPhotoPicked = null
            }

            2 -> {
                deleteImage(context, data.ternaryPhotoPicked!!)
                data.ternaryPhotoPicked = null
            }
        }
        addEditMemoryUIState.value = AddEditScreenUIState.MemoryChanged
    }

    override fun onPhotoPickerStart(index: Int) {
        data.photoIndex = index
    }

    override fun onLocationChanged(latitude: Double?, longitude: Double?) {
        if (latitude == null) {
            Log.d("onLocationChanged", "lat null")
        }

        if (longitude == null) {
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
                data.primaryPhotoPicked = data.memory.primaryPhoto
                data.secondaryPhotoPicked = data.memory.photo1
                data.ternaryPhotoPicked = data.memory.photo2
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
