package com.example.photo_share_no_ai.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.photo_share_no_ai.R
import com.example.photo_share_no_ai.camera.CameraScreen
import com.example.photo_share_no_ai.ui.theme.Photo_share_no_aiTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi

enum class Screen(@StringRes val title: Int){
    HOME(title = R.string.home),
    CAMERA(title = R.string.camera)
}

@ExperimentalPermissionsApi
@Composable
fun PhotoApp (
    navController: NavHostController = rememberNavController()
){
    NavHost(
        navController = navController,
        startDestination = Screen.HOME.name,
        modifier = Modifier
    ) {
        composable(route = Screen.HOME.name) {
            HomeScreen(
                onCreateCameraClick = {
                    navController.navigate(Screen.CAMERA.name)
                }
            )
        }
        composable(route = Screen.CAMERA.name) {
            CameraScreen()
        }
    }
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onCreateCameraClick: () -> Unit = {}
) {
    Column (
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        CreateCameraButton(onCreateCameraClick)
        JoinCameraButton()
        ViewPreviousCamerasButton()
        ViewGalleryButton()
    }
}

@Composable
fun CreateCameraButton(onCreateCameraClick: () -> Unit = {}) {
    Button(onClick = onCreateCameraClick ) {
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
fun HomeScreenPreview() {
    Photo_share_no_aiTheme {
        Column {
            CreateCameraButton()
            JoinCameraButton()
            ViewPreviousCamerasButton()
            ViewGalleryButton()
        }
    }
}
