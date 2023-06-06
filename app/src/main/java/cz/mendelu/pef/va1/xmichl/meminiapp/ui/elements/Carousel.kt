import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import cz.mendelu.pef.va1.xmichl.meminiapp.ui.elements.SquareImageBox
import java.io.File

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Carousel(items: List<String>) {
    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) {
        items.count()
    }

    HorizontalPager(
        state = pagerState
    ) { page ->
        // Content of each page
        val item = items[page]
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                //.align(Alignment.Center)
                //.height() // Adjust the height as desired
        ) {
            val imageFile = File(LocalContext.current.filesDir, item)

            AsyncImage(
                model = imageFile.absolutePath,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
            )
        }
    }
}
