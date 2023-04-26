package cz.mendelu.pef.va1.xmichl.meminiapp

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
import cz.mendelu.pef.va1.xmichl.meminiapp.navigation.Destination
import cz.mendelu.pef.va1.xmichl.meminiapp.navigation.NavGraph
import cz.mendelu.pef.va1.xmichl.meminiapp.ui.screens.SearchScreen
import cz.mendelu.pef.va1.xmichl.meminiapp.ui.screens.SplashScreen
import cz.mendelu.pef.va1.xmichl.meminiapp.ui.screens.TimeLineScreen
import cz.mendelu.pef.va1.xmichl.meminiapp.ui.screens.TodayScreen
import cz.mendelu.pef.va1.xmichl.meminiapp.ui.theme.MeminiAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MeminiAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //Greeting("Android")
                    //TimeLineScreen()
                    //TodayScreen()
                    //SearchScreen()
                    //SplashScreen()
                    NavGraph(startDestination = Destination.TimeLineScreen.route)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
    //MemoryRow()
}
