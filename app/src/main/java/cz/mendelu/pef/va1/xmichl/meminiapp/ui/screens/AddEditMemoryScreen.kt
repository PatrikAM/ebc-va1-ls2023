package cz.mendelu.pef.va1.xmichl.meminiapp.ui.screens

import android.app.DatePickerDialog
import android.net.Uri
import android.util.Log
import android.widget.DatePicker
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import cz.mendelu.pef.va1.xmichl.meminiapp.MeminiApp
import cz.mendelu.pef.va1.xmichl.meminiapp.R
import cz.mendelu.pef.va1.xmichl.meminiapp.extensions.round
import cz.mendelu.pef.va1.xmichl.meminiapp.models.Location
import cz.mendelu.pef.va1.xmichl.meminiapp.navigation.Destination
import cz.mendelu.pef.va1.xmichl.meminiapp.navigation.INavigationRouter
import cz.mendelu.pef.va1.xmichl.meminiapp.ui.elements.ImagePickerButton
import cz.mendelu.pef.va1.xmichl.meminiapp.ui.elements.InfoElement
import cz.mendelu.pef.va1.xmichl.meminiapp.ui.elements.MyTextfield
import cz.mendelu.pef.va1.xmichl.meminiapp.ui.elements.screenSkeletons.NavScreen
import cz.mendelu.pef.va1.xmichl.meminiapp.utils.DateUtils
import org.koin.androidx.compose.getViewModel
import java.util.*

@Composable
fun AddEditMemoryScreen(
    navigation: INavigationRouter,
    viewModel: AddEditMemoryViewModel = getViewModel(),
    id: Long? = null //TODO change this to be mandatory
) {

    viewModel.memoryId = id


    navigation.getNavController().currentBackStackEntry?.let {
        if (navigation.getNavController().currentBackStackEntry!!.savedStateHandle.contains("location")) {
            val mapScreenResult = navigation
                .getNavController()
                .currentBackStackEntry
                ?.savedStateHandle
                ?.getLiveData<String>("location")
                ?.observeAsState()

            mapScreenResult?.value?.let {
                val moshi: Moshi = Moshi.Builder().build()
                val jsonAdapter: JsonAdapter<Location> =
                    moshi.adapter(Location::class.java)
                val location: Location? = jsonAdapter.fromJson(it)
                if (location != null) {
                    viewModel.onLocationChanged(location.latitude, location.longitude)
                }

                navigation.getNavController()
                    .currentBackStackEntry
                    ?.savedStateHandle
                    ?.remove<String>("location")
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
            AddEditScreenUIState.Loading -> TODO()
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

    NavScreen(
        appBarTitle = "Add Edit", //TODO: memory title
        destination = Destination.AddEditMemoryScreen, //TODO: prev or prevprev destination
        navigation = navigation,
        backArrowNeeded = true,
        onBackClick = {
            navigation.returnBack()
        }
    ) {
        AddEditScreenContent(
            actions = viewModel,
            data = data,
            navigation = navigation
        )
    }
}

@Composable
fun AddEditScreenContent(
    actions: AddEditMemoryActions,
    data: AddEditScreenData,
    navigation: INavigationRouter
) {
    if (!data.loading) {
        val context = MeminiApp.appContext
        val permissionState = remember { mutableStateOf(false) }

        val permissionsRequester =
            rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
                permissionState.value = isGranted
            }

        if (permissionState.value) {

            Column(
                modifier = Modifier.fillMaxSize(0.9f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                var selectedImageUri by remember {
                    mutableStateOf<Uri?>(null)
                }

                val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.GetContent(),// .PickVisualMedia(),
//                contract = ActivityResultContracts.PickVisualMedia(),
                    onResult = { uri ->
                        selectedImageUri = uri
                        Log.d("image123", uri?.toString()!!)
                    }
                )

                Row {
//                Box(
//                    modifier = Modifier
//                        .fillMaxSize(0.25f)
//                        .padding(5.dp)
//                ) {
//                    Column {
//                        Image(
//                            painter =
//                            if (selectedImageUri != null)
//                                rememberAsyncImagePainter(model = Uri.parse(selectedImageUri.toString()))
//                            else painterResource(id = R.drawable.photo_place_holder),
//                            contentDescription = null,
//                            modifier = Modifier.clickable {
//                                actions.onPhotoPickerStart(0)
//                                singlePhotoPickerLauncher.launch(
////                                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
//                                    "image/*"
//                                )
//                            }
//                        )
//                        IconButton(
//                            onClick = { /*TODO*/ },
//                            modifier = Modifier.align(Alignment.CenterHorizontally)
//                        ) {
//                            Icon(
//                                imageVector = Icons.Default.Clear,
//                                contentDescription = null
//                            )
//                        }
//                    }
//                }
//                Box(
//                    modifier = Modifier
//                        .fillMaxSize(0.33f)
//                        .padding(5.dp),
//                    contentAlignment = Alignment.Center
//                ) {
//                    Image(
//                        painter = painterResource(id = R.drawable.undraw_polaroid),
//                        contentDescription = null,
//                        modifier = Modifier
//                            .align(Alignment.Center)
//                            .graphicsLayer(alpha = 0.5f)
//                    )
//                    Box(
//                        modifier = Modifier
//                            .size(64.dp)
//                            .border(2.dp, Color.White, CircleShape)
//                    ) {
//                        IconButton(
//                            onClick = { /*TODO*/ },
//                            modifier = Modifier.align(Alignment.Center)
//                        ) {
//                            Icon(
//                                imageVector = Icons.Default.Clear,
//                                contentDescription = null,
//                            )
//                        }
//                    }
//                }
                    ImagePickerButton(size = 0.25f) {
                        actions.onPhotoPickerStart(0)
                        singlePhotoPickerLauncher.launch(
//                                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                            "image/*"
                        )
                    }

                    ImagePickerButton(size = 0.33f) {
                        actions.onPhotoPickerStart(0)
                        singlePhotoPickerLauncher.launch(
//                                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                            "image/*"
                        )
                    }

                    ImagePickerButton(size = 0.5f) {
                        actions.onPhotoPickerStart(0)
                        singlePhotoPickerLauncher.launch(
//                                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                            "image/*"
                        )
                    }
                }

                Spacer(modifier = Modifier.height(50.dp))
                MyTextfield(
                    value = data.memory.title,
                    onValueChange = {
                        actions.onTitleChanged(it)
                    },
                    leadingIcon = Icons.Default.Title,
                    label = "Title",
                    onClearClick = {
                        actions.onTitleChanged("")
                    }
                )

                val calendar = Calendar.getInstance()
                data.memory.date.let {
                    calendar.timeInMillis = it
                }

                val y = calendar.get(Calendar.YEAR)
                val m = calendar.get(Calendar.MONTH)
                val d = calendar.get(Calendar.DAY_OF_MONTH)

                val datePickerDialog = DatePickerDialog(
                    LocalContext.current,
                    { _: DatePicker, year: Int, month: Int, day: Int ->
                        actions.onDateChanged(DateUtils.getUnixTime(year, month, day))
                    },
                    y,
                    m,
                    d

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


                InfoElement(
                    value =
                    if (data.memory.hasLocation())
                        if (Location(
                                data.memory.latitude!!,
                                data.memory.longitude
                            ).getNearestCity() != null
                        )
                            "${
                                Location(
                                    data.memory.latitude!!,
                                    data.memory.longitude
                                ).getNearestCity()
                            }"
                        else
                            "${data.memory.latitude!!.round()}; ${data.memory.longitude!!.round()}"
                    else "",
                    label = "Location (optional)",
                    leadingIcon = Icons.Default.LocationOn,
                    onClick = {
                        navigation.navigateToMapScreen(
                            data.memory.latitude,
                            data.memory.longitude
                        )
                    },
                    onClearClick = {
                        actions.onLocationChanged(null, null)
                    }
                )

                MyTextfield(
                    value = if (data.memory.description != null)
                        data.memory.description!! else "",
                    onValueChange = {
                        actions.onDescriptionChanged(it)
                    },
                    leadingIcon = Icons.Default.Notes,
                    label = "Description (optional)",
                    onClearClick = {
//                    data.memory.description = null
                        actions.onDescriptionChanged(null)
                    }
                )

                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = { actions.saveMemory() }
                ) {
                    Text(text = "Save")
                }
            }
        } else {
            permissionsRequester.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)
        }
    } else {
        // todo loading screen
    }
}

//@OptIn(ExperimentalPermissionsApi::class)
//@Composable
//private fun FeatureThatRequiresCameraPermission() {
//
//    // Camera permission state
//    val cameraPermissionState = rememberPermissionState(
//        android.Manifest.permission.CAMERA
//    )
//
//    if (cameraPermissionState.status.isGranted) {
//        Text("Camera permission Granted")
//    } else {
//        Column {
//            val textToShow = if (cameraPermissionState.status.shouldShowRationale) {
//                // If the user has denied the permission but the rationale can be shown,
//                // then gently explain why the app requires this permission
//                "The camera is important for this app. Please grant the permission."
//            } else {
//                // If it's the first time the user lands on this feature, or the user
//                // doesn't want to be asked again for this permission, explain that the
//                // permission is required
//                "Camera permission required for this feature to be available. " +
//                        "Please grant the permission"
//            }
//            Text(textToShow)
//            Button(onClick = { cameraPermissionState.launchPermissionRequest() }) {
//                Text("Request permission")
//            }
//        }
//    }
//}
