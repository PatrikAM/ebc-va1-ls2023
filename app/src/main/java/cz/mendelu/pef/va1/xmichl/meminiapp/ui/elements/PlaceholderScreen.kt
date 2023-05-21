package cz.mendelu.pef.va1.xmichl.meminiapp.ui.elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cz.mendelu.pef.va1.xmichl.meminiapp.ui.theme.basicMargin

@Composable
fun PlaceHolderScreen(
    image: Int?,
    text: String?,
    subtext: String?
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
                .padding(basicMargin())
        ) {
            if (image != null) {
                Image(
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier.width(300.dp),
                    painter = painterResource(id = image),
                    contentDescription = null
                )
            }
            Spacer(modifier = Modifier.height(basicMargin()))
            if (text != null) {
                Text(
                    text = text,
//                    style = basicText(),
                    textAlign = TextAlign.Center,
//                    color = getBasicTextColor()
                )
            }
            if (subtext != null) {
                Text(
                    text = subtext,
//                    style = basicText(),
                    textAlign = TextAlign.Center,
//                    color = getBasicTextColor()
                )
            }
        }
    }
}
