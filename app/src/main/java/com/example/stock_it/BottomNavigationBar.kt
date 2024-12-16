package com.example.stock_it


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.draw.clip
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.stock_it.ui.theme.GreenPrimary

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        BottomNavItem.Inventory,
        BottomNavItem.ShoppingList,
        BottomNavItem.Suppliers
    )

    NavigationBar(
        modifier = Modifier
            .padding(16.dp) // Add padding to make the NavBar smaller and separate it from edges
            .clip(RoundedCornerShape(40.dp)) // Fully rounded corners for all sides
            .background(GreenPrimary), // Set background color
        containerColor = Color.Transparent // Use a transparent container to apply custom background
    ) {
        val navBackStackEntry = navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry.value?.destination?.route

        items.forEach { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = null,
                        tint = Color.White,

                        modifier = Modifier.size(32.dp)
                    )
                },
                label = { Text(text = item.title) },
                selected = currentRoute == item.route,
                onClick = {
                    if (currentRoute != item.route) {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                } ,
                alwaysShowLabel = false
            )
        }
    }
}
