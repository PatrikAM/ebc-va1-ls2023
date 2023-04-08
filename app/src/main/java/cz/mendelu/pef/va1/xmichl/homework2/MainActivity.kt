package cz.mendelu.pef.va1.xmichl.homework2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import cz.mendelu.pef.va1.xmichl.homework2.navigation.NavGraph
import cz.mendelu.pef.va1.xmichl.homework2.navigation.Destination
import cz.mendelu.pef.va1.xmichl.homework2.ui.theme.Homework2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Homework2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavGraph(startDestination = Destination.ContactListScreen.route)
                    //NewContactScreen()
                    //ContactListScreen()
                }
            }
        }
    }
}
