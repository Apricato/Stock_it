package com.example.stock_it

import android.content.Intent
import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stock_it.ui.theme.GreenSecondary
import com.example.stock_it.R
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.TextStyle
import androidx.compose.foundation.border
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext

val CustomFont = FontFamily(
    Font(R.font.konkhmersleokchher) // Cambia esto por el nombre real del archivo de tu fuente
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingListScreen() {
    val context = LocalContext.current // Get the current context to use with intents

    Column(Modifier.padding(16.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.note), // Use the note.jpg image
                contentDescription = "Note Icon",
                modifier = Modifier
                    .size(49.dp) // Adjust the size of the image as needed
                    .padding(end = 8.dp) // Space between image and text
            )
            Text(
                text = "Lista de compras",
                style = TextStyle(
                    fontFamily = CustomFont,
                    fontWeight = FontWeight.Bold,
                    fontSize = 36.sp // Adjust the size as needed
                )
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text("Restaurante") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text("Nombre del staff") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { /* Generate List */ },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = GreenSecondary, // Button color
                contentColor = Color.White // Text color inside the button
            )
        ) {
            Text("Generar lista")
        }
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Lista generada:\n- Aguacate Hass\n- Lechuga orejona\n...",
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, GreenSecondary)
                .padding(8.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Share Button
        Button(
            onClick = {
                // Create an implicit Intent to share text
                val shareIntent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, "Share this.")
                    type = "text/plain"
                }

                // Use Intent.createChooser to let the system show share options
                context.startActivity(Intent.createChooser(shareIntent, "Share via"))
            },
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = GreenSecondary, // Button color
                contentColor = Color.White // Text color inside the button
            )
        ) {
            Text("Compartir")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewShoppingListScreen() {
    ShoppingListScreen()
}
