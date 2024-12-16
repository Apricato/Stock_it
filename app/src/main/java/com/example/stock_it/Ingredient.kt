package com.example.stock_it

import androidx.room.PrimaryKey
import androidx.room.Entity


@Entity
data class Ingredient(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val  iteming : String,
    val  pantry : PantryStatus,
    val  amounts : Int,
    val  measure : String

)
enum class PantryStatus {
    LOW,
    OVER_SUPPLY,
    RAN_OUT,
    AVAILABLE,
    NON
}


