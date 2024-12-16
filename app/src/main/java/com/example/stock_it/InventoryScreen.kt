package com.example.stock_it

import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.foundation.clickable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.stock_it.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InventoryScreen(
    state: IngredientState,  // State containing the ingredients, sort type, and dialog visibility
    onEvent: (IngredientEvent) -> Unit  // Event handler to trigger actions
) {
    Box(modifier = Modifier.fillMaxSize()) {

        // LazyColumn to show the list of ingredients
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                // Row for sorting options
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    SortType.values().forEach { sortType ->
                        Row(
                            modifier = Modifier
                                .clickable {
                                    onEvent(IngredientEvent.SortIngredients(sortType))  // Trigger sort
                                },
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = state.sortType == sortType,
                                onClick = {
                                    onEvent(IngredientEvent.SortIngredients(sortType))  // Change sort
                                }
                            )
                            Text(text = sortType.name)
                        }
                    }
                }
            }

            // Display the list of ingredients
            items(state.ingredient) { ingredient ->
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        // Display the ingredient's name, amount, and measure
                        Text(
                            text = "${ingredient.iteming} ",
                            fontSize = 20.sp
                        )
                        Text(
                            text = "${ingredient.amounts} ${ingredient.measure} ",
                            fontSize = 12.sp
                        )
                        Text(
                            text = ingredient.pantry.name,
                            fontSize = 12.sp
                        )
                    }
                    // Delete button for each ingredient
                    IconButton(onClick = {
                        onEvent(IngredientEvent.DeleteContact(ingredient))  // Trigger delete
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Delete,
                            contentDescription = "Delete ingredient"
                        )
                    }
                }
            }
        }

        // Floating Action Button (FAB)
        FloatingActionButton(
            onClick = {
                onEvent(IngredientEvent.ShowDialog)  // Show dialog when FAB is clicked
            },
            shape = CircleShape,
            containerColor = Color(0xFFA3D9A5), // Color for the FAB background
            contentColor = Color.White, // Color for the FAB icon
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)  // Padding to keep it from overlapping with the screen edges
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "Add Item",
                modifier = Modifier.size(36.dp)  // Set size for the icon
            )
        }

        // Show the Add Ingredient Dialog if isAddingIngredient is true
        if (state.isAddingIngredient) {
            AddIngredientDialog(state = state, onEvent = onEvent)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewInventoryScreen() {
    // Sample state for preview
    val exampleState = IngredientState(
        ingredient = listOf(
            Ingredient(45, "Remolacha", PantryStatus.AVAILABLE, 24, "unidades"),
            Ingredient(78, "Vino", PantryStatus.OVER_SUPPLY, 4, "botellas")
        ),
        isAddingIngredient = false,
        sortType = SortType.ITEM_NAME
    )

    // Example of event handling (does nothing in preview)
    val exampleOnEvent: (IngredientEvent) -> Unit = {}

    InventoryScreen(state = exampleState, onEvent = exampleOnEvent)
}
