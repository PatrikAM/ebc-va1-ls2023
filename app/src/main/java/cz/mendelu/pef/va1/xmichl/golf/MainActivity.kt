package cz.mendelu.pef.va1.xmichl.golf

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import cz.mendelu.pef.va1.xmichl.golf.navigation.Destination
import cz.mendelu.pef.va1.xmichl.golf.navigation.NavGraph
import cz.mendelu.pef.va1.xmichl.golf.ui.screens.AddScreen
import cz.mendelu.pef.va1.xmichl.golf.ui.screens.MainScreen
import cz.mendelu.pef.va1.xmichl.golf.ui.theme.GolfTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GolfTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    NavGraph(startDestination = Destination.MainScreen.route)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GolfTheme {
        Greeting("Android")
    }
}