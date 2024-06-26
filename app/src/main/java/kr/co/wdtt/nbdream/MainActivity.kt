package kr.co.wdtt.nbdream

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import dagger.hilt.android.AndroidEntryPoint
import kr.co.common.ContextManager
import kr.co.common.util.FileUtil
import kr.co.ui.theme.NBDreamTheme
import kr.co.wdtt.nbdream.ui.DreamApp

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

        super.onCreate(savedInstanceState)

        ContextManager.setContext(this)
        FileUtil.initialize(this)

        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                scrim = Color.TRANSPARENT,
                darkScrim = Color.TRANSPARENT
            )
        )
        setContent {
            NBDreamTheme {
                DreamApp()
            }
        }
    }
}
