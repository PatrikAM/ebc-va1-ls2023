package cz.mendelu.pef.va1.xmichl.meminiapp.ui.screens

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale
import androidx.core.app.ComponentActivity
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.GoogleMap.OnMarkerDragListener
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.tasks.Tasks
import com.google.maps.android.compose.*
import cz.mendelu.pef.va1.xmichl.meminiapp.MeminiApp
import cz.mendelu.pef.va1.xmichl.meminiapp.R
import cz.mendelu.pef.va1.xmichl.meminiapp.models.Location
import cz.mendelu.pef.va1.xmichl.meminiapp.navigation.Destination
import cz.mendelu.pef.va1.xmichl.meminiapp.navigation.INavigationRouter
import cz.mendelu.pef.va1.xmichl.meminiapp.ui.elements.screenSkeletons.NavScreen
import org.koin.androidx.compose.getViewModel

@Composable
fun MapScreen(
    navigation: INavigationRouter,
    viewModel: MapScreenViewModel = getViewModel(),
    latitude: Double?,
    longitude: Double?
) {

    NavScreen(
        appBarTitle = stringResource(R.string.map_screen),
        boxContent = true,
        onBackClick = { navigation.returnBack() },
        backArrowNeeded = true,
        destination = Destination.MapScreen,
        navigation = navigation
    )
    {
        MapScreenContent(
            paddingValues = it,
            latitude = latitude ?: 49.0,
            longitude = longitude ?: 16.0,
            actions = viewModel,
            onSaveClick = {
                if (viewModel.locationChanged) {
                    navigation.returnFromMap(viewModel.latitude!!, viewModel.longitude!!)
                } else {
                    navigation.returnBack()
                }
            }
        )
    }

}

@Composable
fun MapScreenContent(
    paddingValues: PaddingValues,
    latitude: Double,
    longitude: Double,
    actions: MapScreenActions,
    onSaveClick: () -> Unit
){

    getCurrentLocation()
//    Log.d("location", getCurrentLocation().toString())

    val mapUiSettings by remember { mutableStateOf(
        MapUiSettings(
            zoomControlsEnabled = false,
            mapToolbarEnabled = false)
    ) }
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(latitude, longitude), 10f)
    }

    Box(modifier = Modifier
        .fillMaxSize()
//        .padding(paddingValues)
    ) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            uiSettings = mapUiSettings
        ) {

            MapEffect { map ->
                map.setOnMarkerDragListener(object : OnMarkerDragListener{
                    override fun onMarkerDrag(p0: Marker) {

                    }

                    override fun onMarkerDragEnd(p0: Marker) {
                        actions.onLocationChanged(p0.position.latitude, p0.position.longitude)

                    }

                    override fun onMarkerDragStart(p0: Marker) {

                    }
                })

            }

            Marker(
                state = MarkerState(LatLng(latitude, longitude)),
                draggable = true
            )

        }

        Button(
            onClick = onSaveClick,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(5.dp)
        ) {
            Text(text = "Save location")
        }
    }
}

@Composable
fun getCurrentLocation(): Location? {
    var location by remember { mutableStateOf<Location?>(null) }
    val fusedLocationClient: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(
        MeminiApp.appContext)

    LaunchedEffect(true) {
        try {
            if (ActivityCompat.checkSelfPermission(
                    MeminiApp.appContext,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    MeminiApp.appContext,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityResultContracts.RequestPermission()

                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
            }
            val locationResult = Tasks.await(fusedLocationClient.lastLocation)
            location = Location(
                locationResult.latitude,
                locationResult.longitude
            )
        } catch (e: Exception) {
            // Handle exception
        }
    }

    return location
}
