package com.example.photo_share_no_ai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.photo_share_no_ai.ui.theme.Photo_share_no_aiTheme
import com.example.photo_share_no_ai.ui.PhotoApp
import com.google.accompanist.permissions.ExperimentalPermissionsApi

@ExperimentalPermissionsApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            Photo_share_no_aiTheme {
                PhotoApp()
            }
        }
    }
}




