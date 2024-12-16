package com.example.stock_it

sealed interface IngredientEvent {
    object SaveIngredient: IngredientEvent
    data class Setitemname(val iteming:String):IngredientEvent
    data class Setpantry(val pantry: PantryStatus):IngredientEvent
    data class Setamount(val amounts:Int):IngredientEvent
    data class Setmeasure(val measure:String):IngredientEvent


    object ShowDialog: IngredientEvent
    object HideDialog: IngredientEvent
    data class SortIngredients(val sortType : SortType):IngredientEvent
    data class DeleteContact(val ingredient: Ingredient):IngredientEvent
}