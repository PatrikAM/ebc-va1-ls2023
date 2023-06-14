package cz.mendelu.pef.va1.xmichl.meminiapp.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import cz.mendelu.pef.va1.xmichl.meminiapp.architecture.BaseActivity
import cz.mendelu.pef.va1.xmichl.meminiapp.navigation.Destination
import cz.mendelu.pef.va1.xmichl.meminiapp.navigation.NavGraph
import cz.mendelu.pef.va1.xmichl.meminiapp.ui.theme.MeminiAppTheme
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : BaseActivity<MainActivityViewModel>(MainActivityViewModel::class.java) {

    override val viewModel: MainActivityViewModel by viewModel()

    companion object {
        fun createIntent(context: AppCompatActivity): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        installSplashScreen()
        setContent {
            MeminiAppTheme {
                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
                NavGraph(startDestination = Destination.TimeLineScreen.route)
            }
        }
    }
}

