package cz.mendelu.pef.va1.xmichl.meminiapp.ui.screens

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Build
import android.widget.DatePicker
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notes
import androidx.compose.material.icons.filled.Title
import androidx.compose.material.icons.filled.Today
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import cz.mendelu.pef.va1.xmichl.meminiapp.R
import cz.mendelu.pef.va1.xmichl.meminiapp.extensions.round
import cz.mendelu.pef.va1.xmichl.meminiapp.models.Location
import cz.mendelu.pef.va1.xmichl.meminiapp.navigation.INavigationRouter
import cz.mendelu.pef.va1.xmichl.meminiapp.ui.elements.ImagePickerButton
import cz.mendelu.pef.va1.xmichl.meminiapp.ui.elements.InfoElement
import cz.mendelu.pef.va1.xmichl.meminiapp.ui.elements.Loading
import cz.mendelu.pef.va1.xmichl.meminiapp.ui.elements.LoadingScreen
import cz.mendelu.pef.va1.xmichl.meminiapp.ui.elements.MyButton
import cz.mendelu.pef.va1.xmichl.meminiapp.ui.elements.MyTextfield
import cz.mendelu.pef.va1.xmichl.meminiapp.ui.elements.screenSkeletons.BackArrowScreen
import cz.mendelu.pef.va1.xmichl.meminiapp.utils.DateUtils
import org.koin.androidx.compose.getViewModel
import java.util.*

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun AddEditMemoryScreen(
    navigation: INavigationRouter,
    viewModel: AddEditMemoryViewModel = getViewModel(),
    id: Long? = null
) {

    viewModel.memoryId = id

    navigation.getNavController().currentBackStackEntry?.let {
        if (navigation.getNavController().currentBackStackEntry!!.savedStateHandle.contains("location")) {
            val mapScreenResult =
                navigation.getNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<String>(
                    "location"
                )?.observeAsState()

            mapScreenResult?.value?.let {
                val moshi: Moshi = Moshi.Builder().build()
                val jsonAdapter: JsonAdapter<Location> = moshi.adapter(Location::class.java)
                val location: Location? = jsonAdapter.fromJson(it)
                if (location != null) {
                    viewModel.onLocationChanged(location.latitude, location.longitude)
                }

                navigation.getNavController().currentBackStackEntry?.savedStateHandle?.remove<String>(
                    "location"
                )
            }
        }
    }


    var data: AddEditScreenData by remember {
        mutableStateOf(viewModel.data)
    }

    viewModel.addEditMemoryUIState.value.let {
        when (it) {
            AddEditScreenUIState.Loading -> {
                viewModel.initMemory()
            }

            AddEditScreenUIState.Default -> {}
            AddEditScreenUIState.MemoryChanged -> {
                data = viewModel.data
                viewModel.addEditMemoryUIState.value = AddEditScreenUIState.Default
            }

            AddEditScreenUIState.MemorySaved -> {
                LaunchedEffect(it) {
                    navigation.returnBack()
                }
            }
        }
    }
    val context_ = LocalContext.current
    BackArrowScreen(appBarTitle = stringResource(R.string.Add_Edit), //TODO: memory title
        //destination = Destination.AddEditMemoryScreen, //TODO: prev or prevprev destination
        //navigation = navigation,
        //backArrowNeeded = true,
        //fullScreenContent = true,
        onBackClick = {
            viewModel.deleteAllPhotoHolders(context_)
            navigation.returnBack()
        }) {
        AddEditScreenContent(
            actions = viewModel, data = data, navigation = navigation
        )
    }
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun AddEditScreenContent(
    actions: AddEditMemoryActions, data: AddEditScreenData, navigation: INavigationRouter
) {

    if (!data.loading) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Column(
                modifier = Modifier.fillMaxSize(0.9f),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                val context_ = LocalContext.current
                val singlePhotoPickerLauncher =
                    rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent(),// .PickVisualMedia(),
                        onResult = { uri ->
                            uri?.let {
                                actions.onPhotoPickerExit(uri, context_)
                            }
                        })

                Row {

                    SinglePermission()

                    ImagePickerButton(imageName = data.primaryPhotoPicked, size = 0.25f, onClick = {
                        actions.onPhotoPickerStart(0)
                        singlePhotoPickerLauncher.launch(
                            "image/*"
                        )
                    }, onClear = {
                        actions.onPhotoPickerStart(0)
                        actions.onPhotoCleared(context_)
                    })

                    ImagePickerButton(
                        imageName = data.secondaryPhotoPicked,
                        size = 0.33f,
                        onClick = {
                            actions.onPhotoPickerStart(1)
                            singlePhotoPickerLauncher.launch(
                                "image/*"
                            )
                        },
                        onClear = {
                            actions.onPhotoPickerStart(1)
                            actions.onPhotoCleared(context_)
                        })

                    ImagePickerButton(imageName = data.ternaryPhotoPicked, size = 0.5f, onClick = {
                        actions.onPhotoPickerStart(2)
                        singlePhotoPickerLauncher.launch(
                            "image/*"
                        )
                    }, onClear = {
                        actions.onPhotoPickerStart(2)
                        actions.onPhotoCleared(context_)
                    })
                }

                data.photoError?.let {
                    if (data.primaryPhotoPicked == null) {
                        Text(text = stringResource(id = it), color = Color.Red)
                    }
                }

                Spacer(modifier = Modifier.height(50.dp))
                MyTextfield(value = data.memory.title,
                    onValueChange = {
                        actions.onTitleChanged(it)
                    },
                    charLimit = 25,
                    textError = data.titleError,
                    singleLine = true,
                    leadingIcon = Icons.Default.Title,
                    label = stringResource(R.string.title),
                    onClearClick = {
                        actions.onTitleChanged("")
                    })

                val calendar = Calendar.getInstance()
                data.memory.date.let {
                    calendar.timeInMillis = it
                }

                val y = calendar.get(Calendar.YEAR)
                val m = calendar.get(Calendar.MONTH)
                val d = calendar.get(Calendar.DAY_OF_MONTH)

                val datePickerDialog = DatePickerDialog(
                    LocalContext.current, { _: DatePicker, year: Int, month: Int, day: Int ->
                        actions.onDateChanged(DateUtils.getUnixTime(year, month, day))
                    }, y, m, d

                )

                InfoElement(
                    value = DateUtils.getDateString(data.memory.date),
                    label = stringResource(R.string.date),
                    leadingIcon = Icons.Default.Today,
                    onClick = {
                        datePickerDialog.show()
                    },
                    showClearIcon = false
                )


                InfoElement(value = if (data.memory.hasLocation()) if (Location(
                        data.memory.latitude!!, data.memory.longitude
                    ).getNearestCity() != null
                ) "${
                    Location(
                        data.memory.latitude!!, data.memory.longitude
                    ).getNearestCity()
                }"
                else "${data.memory.latitude!!.round()}; ${data.memory.longitude!!.round()}"
                else "",
                    label = stringResource(R.string.location) + " (" + stringResource(R.string.optional) + ")",
                    leadingIcon = Icons.Default.LocationOn,
                    onClick = {
                        navigation.navigateToMapPickerScreen(
                            data.memory.latitude, data.memory.longitude
                        )
                    },
                    onClearClick = {
                        actions.onLocationChanged(null, null)
                    })

                MyTextfield(value = if (data.memory.description != null) data.memory.description!! else "",
                    onValueChange = {
                        actions.onDescriptionChanged(it)
                    },
                    leadingIcon = Icons.Default.Notes,
                    label = stringResource(R.string.description) + " (" + stringResource(R.string.optional) + ")",
                    onClearClick = {
//                    data.memory.description = null
                        actions.onDescriptionChanged(null)
                    })

                Spacer(modifier = Modifier.height(20.dp))

                MyButton(
                    text = stringResource(R.string.save),
                    onClick = { actions.saveMemory(context_) }
                )
            }
        }
//        } else {
//            permissionsRequester.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)
//        }
    } else {
        LoadingScreen(
            line1 = stringResource(R.string.loading_memory),
            line2 = stringResource(R.string.please_wait)
        )
        // todo loading screen
    }
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@SuppressLint("PermissionLaunchedDuringComposition")
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun SinglePermission() {
    val permissionState =
        rememberPermissionState(permission = android.Manifest.permission.READ_MEDIA_IMAGES)
    val lifecycleOwner = LocalLifecycleOwner.current

    DisposableEffect(key1 = lifecycleOwner, effect = {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_START -> {
                    permissionState.launchPermissionRequest()
                }

                else -> {

                }
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    })

    when {
        !permissionState.status.isGranted && !permissionState.status.shouldShowRationale -> {
            Text(text = "Permission fully denied. Go to settings to enable")
        }
    }
}
