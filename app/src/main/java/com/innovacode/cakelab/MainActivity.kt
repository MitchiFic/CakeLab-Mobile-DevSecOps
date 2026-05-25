package com.innovacode.cakelab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.innovacode.cakelab.navigation.AppNavigation
import com.innovacode.cakelab.ui.theme.CakeLabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CakeLabTheme {
                AppNavigation()
            }
        }
    }
}