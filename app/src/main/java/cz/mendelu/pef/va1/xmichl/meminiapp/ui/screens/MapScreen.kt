package cz.mendelu.pef.va1.xmichl.meminiapp.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import cz.mendelu.pef.va1.xmichl.meminiapp.R
import cz.mendelu.pef.va1.xmichl.meminiapp.models.Memory
import cz.mendelu.pef.va1.xmichl.meminiapp.navigation.Destination
import cz.mendelu.pef.va1.xmichl.meminiapp.navigation.INavigationRouter
import cz.mendelu.pef.va1.xmichl.meminiapp.ui.elements.AddEditMemoryFAB
import cz.mendelu.pef.va1.xmichl.meminiapp.ui.elements.memoryList.MemoryListUIState
import cz.mendelu.pef.va1.xmichl.meminiapp.ui.elements.memoryList.MemoryListViewModel
import cz.mendelu.pef.va1.xmichl.meminiapp.ui.elements.screenSkeletons.NavScreen
import org.koin.androidx.compose.getViewModel

@Composable
fun MapScreen(
    navigation: INavigationRouter,
    viewModel: MemoryListViewModel = getViewModel()
) {

    val memories = remember { mutableStateListOf<Memory>() }

    viewModel.memoryListUIState.value.let {
        when (it) {
            MemoryListUIState.Default -> {
                viewModel.loadMemories()
            }
            is MemoryListUIState.Success -> {
                memories.clear()
                memories.addAll(it.memories)
            }
            MemoryListUIState.Error -> TODO()
            MemoryListUIState.Loading -> TODO()
        }
    }

    NavScreen(
        appBarTitle = stringResource(R.string.map),
        destination = Destination.MapScreen,
        navigation = navigation,
        floatingActionButton = {
            AddEditMemoryFAB(navigation = navigation)
        },
        boxContent = true,
    ) {
        MapScreenContent(
            memories = memories,
            navigation = navigation,
            paddingValues = it
        )
    }
}

@Composable
fun MapScreenContent(
    memories: List<Memory>,
    navigation: INavigationRouter,
    paddingValues: PaddingValues
) {

    val latitude = 49.0
    val longitude = 16.0

    val mapUiSettings by remember {
        mutableStateOf(
            MapUiSettings(
                zoomControlsEnabled = false,
                mapToolbarEnabled = false
            )
        )
    }
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(latitude, longitude), 10f)
    }

//    Column(modifier = Modifier.fillMaxSize()) {


        Box(
            modifier = Modifier
                .fillMaxSize()
        //.padding(paddingValues)
        ) {
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState,
                uiSettings = mapUiSettings
            ) {
                memories.forEach { poi ->
                    if (poi.hasLocation()) {
                        Marker(
                            state = MarkerState(LatLng(poi.latitude!!, poi.longitude!!)),
                            onClick = {
                                navigation.navigateToMemoryDetailScreen(id = poi.id!!)
                                false
                            }
                        )
//                googleMap.addMarker(
//                    MarkerOptions()
//                        .position(LatLng(poi.latitude!!, poi.longitude!!))
//                        .title(poi.title)
//                )
                    }
                }
            }

//    Box(modifier = Modifier.fillMaxSize()) {
//        MapView(
//            //context = LocalContext.current,
//            //modifier = Modifier.fillMaxSize(),
//            //cameraState = cameraPositionState,
//            mapContent = {
//                // Add custom map content here (e.g., styling, overlays)
//                memories.forEach { marker ->
//                    if (marker.hasLocation()) {
//                        Marker(
//                            modifier = Modifier
//                                .zIndex(1f),
//                            markerOptions = marker
//                        )
//                    }
//                }
//            }
//        )
//    }
        }
    }
//}