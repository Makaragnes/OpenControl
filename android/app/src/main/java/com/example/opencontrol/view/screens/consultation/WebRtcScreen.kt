package com.example.opencontrol.view.screens.consultation

import android.Manifest
import android.Manifest.*
import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.PackageManager
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.example.opencontrol.view.items.TransientTopBar
import com.example.opencontrol.webrtcmodule.ui.screens.stage.StageScreen
import com.example.opencontrol.webrtcmodule.ui.screens.video.VideoCallScreen
import com.example.opencontrol.webrtcmodule.webrtc.SignalingClient
import com.example.opencontrol.webrtcmodule.webrtc.peer.StreamPeerConnectionFactory
import com.example.opencontrol.webrtcmodule.webrtc.sessions.LocalWebRtcSessionManager
import com.example.opencontrol.webrtcmodule.webrtc.sessions.WebRtcSessionManager
import com.example.opencontrol.webrtcmodule.webrtc.sessions.WebRtcSessionManagerImpl

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun WebRtcScreen(navController: NavController){

    val activity = LocalContext.current as Activity
    val window = activity.window

    val context = LocalContext.current

//    val launcher = rememberLauncherForActivityResult(
//        ActivityResultContracts.RequestPermission()
//    ) { isGranted: Boolean ->
//        if (isGranted) {
//            // Permission Accepted: Do something
//            Log.d("ExampleScreen","PERMISSION GRANTED")
//
//        } else {
//            // Permission Denied: Do something
//            Log.d("ExampleScreen","PERMISSION DENIED")
//        }
//    }

//    when (PackageManager.PERMISSION_GRANTED) {
//        ContextCompat.checkSelfPermission(
//            context,
//            Manifest.permission.READ_EXTERNAL_STORAGE
//        ) -> {
//            // Some works that require permission
//            Log.d("ExampleScreen","Code requires permission")
//        }
//        else -> {
//            // Asking for permission
//            launcher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
//        }
//
//    }

    //requestPermissions(arrayOf(permission.CAMERA, permission.RECORD_AUDIO), 0)

    val sessionManager: WebRtcSessionManager = WebRtcSessionManagerImpl(
        context = context,
        signalingClient = SignalingClient(),
        peerConnectionFactory = StreamPeerConnectionFactory(context)
    )

    Scaffold(
        topBar = {
            TransientTopBar(navController = navController, "Совещание скоро начнется")
        }
    ) {



        CompositionLocalProvider(LocalWebRtcSessionManager provides sessionManager) {
            // A surface container using the 'background' color from the theme
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background
            ) {
                var onCallScreen by remember { mutableStateOf(false) }
                val state by sessionManager.signalingClient.sessionStateFlow.collectAsState()

                if (!onCallScreen) {
                    StageScreen(state = state) { onCallScreen = true }
                } else {
                    VideoCallScreen()
                }
            }
        }
    }

}