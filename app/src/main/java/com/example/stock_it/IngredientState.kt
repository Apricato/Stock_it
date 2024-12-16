package com.example.stock_it

data class IngredientState(
    val ingredient: List<Ingredient> = emptyList(),
    val  iteming : String = "",
    val  pantry  : PantryStatus = PantryStatus.NON,
    val  amounts : Int = 0,
    val measure : String ="",
    val isAddingIngredient: Boolean = false,
    val sortType: SortType = SortType.ITEM_NAME

    )
