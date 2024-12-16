package com.example.stock_it.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.example.stock_it.R


private val DarkColorScheme = darkColorScheme(
    primary = GreenPrimary,
    secondary = GreenSecondary,
    background = SurfaceColor, // Más suave para fondos oscuros
    surface = SurfaceColor,
    onPrimary = BackgroundColor, // Texto sobre botones primarios
    onSecondary = BackgroundColor, // Texto sobre botones secundarios
    onBackground = BackgroundColor,
    onSurface = BackgroundColor
)


private val LightColorScheme = lightColorScheme(
    primary = GreenPrimary,
    secondary = GreenSecondary,
    background = BackgroundColor,
    surface = SurfaceColor,
    onPrimary = PrimaryTextColor, // Texto sobre botones primarios
    onSecondary = SecondaryTextColor, // Texto sobre botones secundarios
    onBackground = PrimaryTextColor,
    onSurface = PrimaryTextColor
)

@Composable
fun Stock_itTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false, // Colores dinámicos (solo en Android 12+)
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
