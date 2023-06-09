package cz.mendelu.pef.va1.xmichl.meminiapp.ui.elements

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cz.mendelu.pef.va1.xmichl.meminiapp.R
import cz.mendelu.pef.va1.xmichl.meminiapp.dataStore.UserSettingsStore

@Composable
fun MyButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    //val context = LocalContext.current
    //val keyboardController = LocalSoftwareKeyboardController.current
    //val tokenText = store.getAccessToken.collectAsState(initial = "")

//    val color = remember {
//        mutableIntStateOf(0)
//    }
    val store = UserSettingsStore(LocalContext.current)
    val theColor = store.getUserColor.collectAsState(initial = R.color.memini_default)


    Button(
        onClick = onClick,
        modifier = modifier
            .padding(horizontal = 16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(id = theColor.value),
            contentColor = Color.White
        ),
    ) {
        Text(
            text = text,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
    }
}
