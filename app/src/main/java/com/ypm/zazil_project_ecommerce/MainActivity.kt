package com.ypm.zazil_project_ecommerce

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.ypm.zazil_project_ecommerce.ui.theme.Zazil_Project_EcommerceTheme
import com.ypm.zazil_project_ecommerce.view.Modifiers.customBackgrond

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /**
         * FUNCIÓN QUE LLAMA A LA SPLASH SCREEN
         **/
        installSplashScreen()
        enableEdgeToEdge()
        setContent {
            Zazil_Project_EcommerceTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ){
                        Greeting(
                            name = "Android",
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .customBackgrond(idColor = R.color.primary_500)
    ){

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Zazil_Project_EcommerceTheme {
        Greeting("Android")
    }
}