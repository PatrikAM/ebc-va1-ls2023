package cz.mendelu.pef.va1.xmichl.meminiapp.ui.elements

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import cz.mendelu.pef.va1.xmichl.meminiapp.R
import java.io.File

@Composable
fun ImagePickerButton(
    size: Float,
    imageName: String?,
    onClick: () -> Unit,
    onClear: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize(size)
            .padding(5.dp),
        contentAlignment = Alignment.Center
    ) {
        if (imageName != null) {
            val imageFile = File(LocalContext.current.filesDir, imageName)
            Log.d("imageFile", imageFile.absolutePath)
            Image(
                rememberAsyncImagePainter(imageFile),
                contentDescription = "...",
            )
        } else {
            Image(
                painter = painterResource(id = R.drawable.undraw_polaroid),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.Center)
                    .graphicsLayer(alpha = 0.5f)
            )
        }
        Box(
            modifier = Modifier
                .size(64.dp)
                .border(2.dp, Color.White, CircleShape)
        ) {
            IconButton(
                onClick = if (imageName == null) onClick else onClear,
                modifier = Modifier.align(Alignment.Center)
            ) {
                Icon(
                    imageVector = if (imageName == null) Icons.Default.Add else Icons.Default.Clear,
                    contentDescription = null,
                )
            }
        }
    }
}