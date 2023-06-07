package cz.mendelu.pef.va1.xmichl.meminiapp.ui.elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cz.mendelu.pef.va1.xmichl.meminiapp.R

@Composable
fun Loading() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
            //.background(Color.LightGray),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            //color = Color.Black,
            strokeWidth = 2.dp
        )
    }
}

@Composable
fun LoadingScreen(
    line1: String = "",
    line2: String = ""
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxHeight(),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Spacer(modifier = Modifier.height(150.dp))
            Loading()
            Text(text = line1)
            Text(text = line2)
        }
    }
}