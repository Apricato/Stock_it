package com.example.stock_it

import androidx.annotation.DrawableRes

sealed class BottomNavItem(
    val route: String,
    val title: String,
    @DrawableRes val icon: Int
) {
    object Inventory : BottomNavItem("inventory", "Inventario", R.drawable.ic_inventory)
    object ShoppingList : BottomNavItem("shopping_list", "Lista de Compras", R.drawable.ic_shopping_list)
    object Suppliers : BottomNavItem("suppliers", "Proveedores", R.drawable.ic_suppliers)
}
