package com.example.stock_it

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.clickable
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.text.TextStyle
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.foundation.interaction.MutableInteractionSource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddIngredientDialog(
    state: IngredientState,  // State passed from the screen
    onEvent: (IngredientEvent) -> Unit  // Event handler to save or cancel
) {
    // State for managing the dropdown menu visibility and selected pantry
    var expanded by remember { mutableStateOf(false) }
    var selectedPantry by remember { mutableStateOf(state.pantry) }

    // Creating a MutableInteractionSource for clickable
    val interactionSource = remember { MutableInteractionSource() }

    // Dialog content for adding a new ingredient
    AlertDialog(
        onDismissRequest = { onEvent(IngredientEvent.HideDialog) },
        title = { Text(text = "Add Ingredient") },
        text = {
            Column {
                // Input fields for ingredient name, amount, and measure
                TextField(
                    value = state.iteming,
                    onValueChange = { newItem -> onEvent(IngredientEvent.Setitemname(newItem)) },
                    label = { Text("Ingredient Name") }
                )
                TextField(
                    value = state.amounts.toString(),
                    onValueChange = { newAmount -> onEvent(IngredientEvent.Setamount(newAmount.toInt())) },
                    label = { Text("Amount") }
                )
                TextField(
                    value = state.measure,
                    onValueChange = { newMeasure -> onEvent(IngredientEvent.Setmeasure(newMeasure)) },
                    label = { Text("Measure") }
                )

                // Pantry selection dropdown
                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { expanded = !expanded }
                ) {
                    TextField(
                        value = selectedPantry.name,
                        onValueChange = {},
                        label = { Text("Pantry Status") },
                        readOnly = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable(
                                interactionSource = interactionSource,
                                indication = null
                            ) { expanded = !expanded },  // This handles the click event
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(
                                expanded = expanded
                            )
                        }
                    )

                    // Dropdown menu for pantry options
                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        PantryStatus.values().forEach { pantryStatus ->
                            DropdownMenuItem(
                                onClick = {
                                    // Update selected pantry and trigger event
                                    selectedPantry = pantryStatus
                                    onEvent(IngredientEvent.Setpantry(pantryStatus)) // Trigger pantry update event
                                    expanded = false // Close the dropdown
                                }
                            ) {
                                Text(text = pantryStatus.name)
                            }
                        }
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = {
                onEvent(IngredientEvent.SaveIngredient)  // Trigger save event
            }) {
                Text("Save")
            }
        },
        dismissButton = {
            TextButton(onClick = {
                onEvent(IngredientEvent.HideDialog)  // Close the dialog
            }) {
                Text("Cancel")
            }
        }
    )
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
