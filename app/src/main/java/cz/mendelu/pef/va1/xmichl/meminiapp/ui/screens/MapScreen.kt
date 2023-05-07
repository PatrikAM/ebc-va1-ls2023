package cz.mendelu.pef.va1.xmichl.meminiapp.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.GoogleMap.OnMarkerDragListener
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.maps.android.compose.*
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
        appBarTitle = "Map screen",
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
        .padding(paddingValues)) {
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

        OutlinedButton(
            onClick = onSaveClick,
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            Text(text = "Save location")
        }
    }


}
