package com.example.stock_it

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Upsert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface IngredientDAO {

    @Upsert
    suspend fun InsertItem(ingredient: Ingredient)

    @Delete
    suspend fun EraseItem(ingredient: Ingredient)

    @Query("SELECT * FROM ingredient ORDER BY pantry ASC")
    fun getAvailableItem(): Flow<List<Ingredient>>

    @Query("SELECT * FROM ingredient ORDER BY iteming ASC ")
    fun getNameItem(): Flow<List<Ingredient>>
}