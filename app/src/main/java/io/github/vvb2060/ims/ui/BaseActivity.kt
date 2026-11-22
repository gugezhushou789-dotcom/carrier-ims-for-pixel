package io.github.vvb2060.ims.ui

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import io.github.vvb2060.ims.ui.theme.TurbolImsTheme

abstract class BaseActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // Android 10 fix: enableEdgeToEdge() ends up in
        // WindowCompat.setDecorFitsSystemWindows(), which calls
        // Window.setDecorFitsSystemWindows() - an API 30+ method.
        // On API < 30 the app crashes at launch with NoSuchMethodError,
        // so use the legacy systemUiVisibility flags instead.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            enableEdgeToEdge()
        } else {
            @Suppress("DEPRECATION")
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION)
        }
        super.onCreate(savedInstanceState)
        setContent {
            TurbolImsTheme {
                Content()
            }
        }
    }

    @Composable
    abstract fun Content()
}
