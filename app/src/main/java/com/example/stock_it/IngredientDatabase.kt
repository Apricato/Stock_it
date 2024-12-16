package com.example.stock_it

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [Ingredient::class],
    version = 1
)

abstract class IngredientDatabase : RoomDatabase() {
    abstract  val dao: IngredientDAO
}