package com.innovacode.cakelab.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColors = lightColorScheme(
    primary = RosaViejo,
    secondary = RosaPastel,
    background = Crema,
    surface = Blanco,
    onPrimary = Blanco,
    onSecondary = CafeSuave,
    onBackground = GrisTexto,
    onSurface = GrisTexto
)

@Composable
fun CakeLabTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColors,
        typography = Typography,
        content = content
    )
}