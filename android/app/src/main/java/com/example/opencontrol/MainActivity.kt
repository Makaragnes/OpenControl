package com.example.opencontrol

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
import androidx.core.view.WindowCompat
import com.example.opencontrol.ui.theme.OpenControlTheme
import com.example.opencontrol.view.navigation.OpenControlNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            OpenControlTheme {
                OpenControlNavHost()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    OpenControlTheme {

    }
}