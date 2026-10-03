package com.example.photo_share_no_ai.camera

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CloudQueue
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.example.photo_share_no_ai.ui.theme.Purple80
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import androidx.exifinterface.media.ExifInterface
import java.io.File

@ExperimentalPermissionsApi
@Composable
fun CameraScreen (modifier: Modifier = Modifier) {

    val cameraPermissionState : PermissionState = rememberPermissionState(android.Manifest.permission.CAMERA)


    if (cameraPermissionState.status.isGranted) {
        CameraImageCapture()
    }
    else {
        CameraPermission(
            permissionRequest = cameraPermissionState::launchPermissionRequest,
            showRationale = cameraPermissionState.status.shouldShowRationale,
            modifier = modifier.fillMaxSize()
        )
    }

    }


@Composable
fun CameraImageCapture() {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    //TODO: can implement viewModel later to keep controller on rotation
    val cameraController = remember { LifecycleCameraController(context) }

    // use DisposableEffect so not called on every recompose
    DisposableEffect(lifecycleOwner, cameraController) {
        // bind the lifecycleOwner to cameraController
        cameraController.bindToLifecycle(lifecycleOwner)
        onDispose {
            // unbind when CameraImageCapture composable out of sight
            cameraController.unbind()
        }
    }

    Box(
        modifier = Modifier
            .border(
                width = 16.dp,
                brush = SolidColor(Purple80),
                shape = RectangleShape
            )
            .fillMaxSize()
            .background(Purple80),
        contentAlignment = Alignment.BottomCenter
    ) {
        // 1. Camera Preview fills the background
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = { context ->
                PreviewView(context).apply {
                    setBackgroundColor(Purple80.toArgb())
                    scaleType = PreviewView.ScaleType.FILL_START
                }.also { previewView ->
                    // bind PreviewView to controller, so controller knows which surface to pass to
                    previewView.controller = cameraController
                }
            }
        )

        // 2. Floating action button overlaid on top at the bottom
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 32.dp),
            contentAlignment = Alignment.BottomCenter
        ) {

            TakePictureButton({
                cameraController.takePicture(

                    ContextCompat.getMainExecutor(context),


                )
            })
        }
    }
}


@Composable
fun TakePictureButton(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = { onClick() },
    ) {
        Icon(Icons.Outlined.CloudQueue, "Floating action button.")
    }
}


// TODO: fix so permission just this time doesn't auto direct to settings
@Composable
fun CameraPermission(
    permissionRequest : () -> Unit,
    showRationale : Boolean,
    modifier : Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (showRationale) {
            Text(text = "Please Grant Camera Permission")
            Button(onClick = permissionRequest) {
                Text(text = "Give Permission")
            }
        }
        else {
            val context = LocalContext.current

            val cameraPermissionSettingsIntent : Intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                .apply { data = Uri.fromParts(
                    "package",
                    context.packageName,
                    null
                ) }
            context.startActivity(cameraPermissionSettingsIntent)
        }
    }
}