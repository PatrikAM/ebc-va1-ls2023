package cz.mendelu.pef.va1.xmichl.meminiapp.ui.screens

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.animation.AnticipateInterpolator
import androidx.activity.ComponentActivity
import androidx.annotation.RequiresApi
import androidx.core.animation.doOnEnd
import cz.mendelu.pef.va1.xmichl.meminiapp.ui.activities.MainActivity

@SuppressLint("CustomSplashScreen")
class SplashScreenOld: ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // ...

        // Add a callback that's called when the splash screen is animating to the
        // app content.
        splashScreen.setOnExitAnimationListener { splashScreenView ->
            // Create your custom animation.
            val slideUp = ObjectAnimator.ofFloat(
                splashScreenView,
                View.TRANSLATION_Y,
                0f,
                -splashScreenView.height.toFloat()
            )
            slideUp.interpolator = AnticipateInterpolator()
            slideUp.duration = 200L

            // Call SplashScreenView.remove at the end of your custom animation.
            slideUp.doOnEnd { splashScreenView.remove() }

            // Run your animation.
            slideUp.start()
            val intent = Intent(this@SplashScreenOld, MainActivity::class.java)
            startActivity(intent)
            finish()
//        super.onCreate(savedInstanceState)
//        lifecycleScope.launchWhenCreated {
//            delay(3000)
//
//            val intent = Intent(this@SplashScreen, MainActivity::class.java)
//            startActivity(intent)
//            finish()
        }
    }

}