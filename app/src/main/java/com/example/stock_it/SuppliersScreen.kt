package com.example.stock_it

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuppliersScreen() {
    // Use Box for layering content and FAB
    Box(modifier = Modifier.fillMaxSize()) {
        // Main content
        Column(Modifier.padding(16.dp)) {
            // Title row with icon and text
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.box), // Replace with correct resource ID
                    contentDescription = "Icono de Proveedores",
                    modifier = Modifier.size(46.dp),
                    tint = Color.Unspecified
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Tus proveedores",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 36.sp
                    )
                )
            }
            Spacer(modifier = Modifier.height(8.dp))

            // Scrollable list of suppliers
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(listOf("Proveedor A", "Proveedor B")) { supplier ->
                    SupplierCard(supplierName = supplier)
                }
            }
        }

        // Floating Action Button positioned on top
        FloatingActionButton(
            onClick = { /* Add supplier logic */ },
            shape = CircleShape,
            containerColor = Color(0xFFA3D9A5),
            contentColor = Color.White,
            modifier = Modifier
                .align(Alignment.BottomEnd) // Position at the bottom-right
                .padding(16.dp) // Avoid overlapping with navigation bar
                .size(72.dp) // Size of the FAB
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "Add Supplier",
                modifier = Modifier.size(36.dp) // Bigger icon
            )
        }
    }
}

// Compssable de proveedores
@Composable
fun SupplierCard(supplierName: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.elevatedCardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = supplierName, modifier = Modifier.weight(1f))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSuppliersScreen() {
    SuppliersScreen()
}
