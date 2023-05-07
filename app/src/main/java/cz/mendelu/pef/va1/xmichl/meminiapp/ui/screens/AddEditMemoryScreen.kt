package cz.mendelu.pef.va1.xmichl.meminiapp.ui.screens

import android.app.DatePickerDialog
import android.net.Uri
import android.util.Log
import android.widget.DatePicker
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import cz.mendelu.pef.va1.xmichl.meminiapp.R
import cz.mendelu.pef.va1.xmichl.meminiapp.extensions.round
import cz.mendelu.pef.va1.xmichl.meminiapp.models.Location
import cz.mendelu.pef.va1.xmichl.meminiapp.navigation.Destination
import cz.mendelu.pef.va1.xmichl.meminiapp.navigation.INavigationRouter
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
                    ?.remove<String>("location") // seems no to be working
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
        Column(
            modifier = Modifier.fillMaxSize(0.9f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var selectedImageUri by remember {
                mutableStateOf<Uri?>(null)
            }

//            val pickMedia = registerForActivityResult(PickVisualMedia()) { uri ->
//                // Callback is invoked after the user selects a media item or closes the
//                // photo picker.
//                if (uri != null) {
//                    Log.d("PhotoPicker", "Selected URI: $uri")
//                } else {
//                    Log.d("PhotoPicker", "No media selected")
//                }
//            }


            val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.GetContent(),// .PickVisualMedia(),
//                contract = ActivityResultContracts.PickVisualMedia(),
                onResult = { uri ->
                    selectedImageUri = uri
//                    Log.d("image123", uri?.toString()!!)
                }
            )

            Row {
                Box(
                    modifier = Modifier
                        .fillMaxSize(0.25f)
                        .padding(5.dp)
                ) {
                    Column {
                        Image(
                            painter =
                            if (selectedImageUri != null)
                                rememberAsyncImagePainter(model = Uri.parse(selectedImageUri.toString()))
                            else painterResource(id = R.drawable.photo_place_holder),
                            contentDescription = null,
                            modifier = Modifier.clickable {
                                actions.onPhotoPickerStart(0)
                                singlePhotoPickerLauncher.launch(
//                                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                                    "image/*"
                                )
                            }
                        )
                        IconButton(
                            onClick = { /*TODO*/ },
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = null
                            )
                        }
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxSize(0.33f)
                        .padding(5.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.photo_place_holder),
                        contentDescription = null
                    )
                }
                Box(
                    modifier = Modifier
                        .fillMaxSize(0.5f)
                        .padding(5.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.photo_place_holder),
                        contentDescription = null
                    )
                }
            }


            //Spacer(modifier = Modifier.height(20.dp))
            //Divider(thickness = 2.dp)
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
                { dialog: DatePicker, year: Int, month: Int, day: Int ->
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
                    if (Location(data.memory.latitude!!, data.memory.longitude).getNearestCity() != null)
                        "${Location(data.memory.latitude!!, data.memory.longitude).getNearestCity()}"
                    else
                        "${data.memory.latitude!!.round()}; ${data.memory.longitude!!.round()}"
                else "",
                label = "Location",
                leadingIcon = Icons.Default.LocationOn,
                onClick = {
                    navigation.navigateToMapScreen(
                        data.memory.latitude,
                        data.memory.longitude)
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
                label = "Description",
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
        // todo loading screen
    }

}