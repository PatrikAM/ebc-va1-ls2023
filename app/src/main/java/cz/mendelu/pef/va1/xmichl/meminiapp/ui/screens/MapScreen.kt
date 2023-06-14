package cz.mendelu.pef.va1.xmichl.meminiapp.ui.screens

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.BitmapDescriptorFactory
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

//    LocationPermission()

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(latitude, longitude), 10f)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
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
                        },
                        icon =
                        BitmapDescriptorFactory
                            .defaultMarker(
                                BitmapDescriptorFactory.HUE_AZURE
                            )
                    )
                }
            }
        }
    }
}

@SuppressLint("PermissionLaunchedDuringComposition")
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun LocationPermission() {
    val perm: String = android.Manifest.permission.ACCESS_FINE_LOCATION
//    if (Build.VERSION.SDK_INT > 32) {
//        perm = android.Manifest.permission.READ_MEDIA_IMAGES
//    }
    val permissionState =
        rememberPermissionState(permission = perm)
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
}

//@Composable
//fun getUserLocation(context: Context): LatLng? {
//
//    // The Fused Location Provider provides access to location APIs.
//    var locationProvider = LocationServices.getFusedLocationProviderClient(context)
//    var locationCallback: LocationCallback
//    var currentUserLocation by remember { mutableStateOf(LatLng(0.0, 0.0)) }
//
//    DisposableEffect(key1 = locationProvider) {
//        locationCallback = object : LocationCallback() {
//            //1
//            override fun onLocationResult(result: LocationResult) {
//                /**
//                 * Option 1
//                 * This option returns the locations computed, ordered from oldest to newest.
//                 * */
//                for (location in result.locations) {
//                    // Update data class with location data
//                    currentUserLocation = LatLng(location.latitude, location.longitude)
//                    //Log.d(LOCATION_TAG, "${location.latitude},${location.longitude}")
//                }
//
//
//                /**
//                 * Option 2
//                 * This option returns the most recent historical location currently available.
//                 * Will return null if no historical location is available
//                 * */
//                locationProvider.lastLocation
//                    .addOnSuccessListener { location ->
//                        location?.let {
//                            val lat = location.latitude
//                            val long = location.longitude
//                            // Update data class with location data
//                            currentUserLocation = LatLng(latitude = lat, longitude = long)
//                        }
//                    }
//            }
//        }
//
//        onDispose {
//            stopLocationUpdate()
//        }
//    }
//    return currentUserLocation
//}
