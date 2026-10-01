package com.example.photo_share_no_ai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.foundation.layout.Column

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.photo_share_no_ai.ui.theme.Photo_share_no_aiTheme
import androidx.compose.material3.*
//import androidx.compose.material3.ButtonDefaults

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Photo_share_no_aiTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                }
            }
        }
    }
}


@Composable
fun CreateCameraButton(onClick: () -> Unit = {}) {
    Button(onClick = { onClick() }) {
        Text("Create Camera")
    }
}

@Composable
fun JoinCameraButton(onClick: () -> Unit = {}) {
    FilledTonalButton(onClick = { onClick() }) {
        Text("Join Camera")
    }
}

@Composable
fun ViewPreviousCamerasButton(onClick: () -> Unit = {}) {
    OutlinedButton(onClick = { onClick() }) {
        Text("View Previous Cameras")
    }
}

@Composable
fun ViewGalleryButton(onClick: () -> Unit = {}) {
    TextButton(onClick = { onClick() }) {
        Text("View Gallery")
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Photo_share_no_aiTheme {
        Column {
            CreateCameraButton()
            JoinCameraButton()
            ViewPreviousCamerasButton()
            ViewGalleryButton()

        }

    }
}