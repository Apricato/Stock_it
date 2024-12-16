package com.example.stock_it.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.Font
import androidx.compose.material3.Typography // Asegúrate de importar de material3
import com.example.stock_it.R

// Declarar la fuente personalizada (Konkhemer en este caso)
val KonkhemerFont = FontFamily(
    Font(R.font.konkhmersleokchher)  // Asegúrate de que el archivo .ttf esté en res/font/
)

// Aquí modificamos el objeto Typography predefinido para personalizar la tipografía
val Typography = Typography(
    titleLarge = TextStyle(
        fontFamily = KonkhemerFont,  // Usamos Konkhemer para los títulos
        fontWeight = FontWeight.Normal,
        fontSize = 36.sp // Tamaño para los títulos
    ),
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default, // Puedes poner la fuente predeterminada o una personalizada para el cuerpo
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp // Tamaño de fuente para el cuerpo del texto
    ),
)
