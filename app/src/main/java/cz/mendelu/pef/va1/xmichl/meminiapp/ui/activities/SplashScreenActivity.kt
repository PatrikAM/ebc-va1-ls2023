package cz.mendelu.pef.va1.xmichl.meminiapp.ui.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import cz.mendelu.pef.va1.xmichl.meminiapp.R
import cz.mendelu.pef.va1.xmichl.meminiapp.ui.activities.states.SplashScreenUiState
import org.koin.androidx.viewmodel.ext.android.viewModel

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {

    private val viewModel: SplashScreenActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        splashScreen.setKeepOnScreenCondition { true }

        lifecycleScope.launchWhenResumed {
            viewModel.splashScreenState.collect { value ->
                when (value) {
                    is SplashScreenUiState.Default -> {
                        viewModel.checkAppState()
                    }
                    SplashScreenUiState.ContinueToApp -> {
                        continueToAList()
                    }
//                    is SplashScreenUiState.RunForAFirstTime -> {
//                        runAppIntro()
//                    }
                }
            }
        }

    }

//    private fun runAppIntro(){
//        startActivity(AppIntroActivity.createIntent(this))
//        finish()
//    }

    private fun continueToAList(){
        startActivity(MainActivity.createIntent(this))
        finish()
    }
}
