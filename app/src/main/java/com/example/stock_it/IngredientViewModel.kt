package com.example.stock_it

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@OptIn(ExperimentalCoroutinesApi::class)
class IngredientViewModel (
    private val dao: IngredientDAO
) : ViewModel() {
    private val _sortType = MutableStateFlow(SortType.ITEM_NAME)
    private val _ingredients = _sortType
        .flatMapLatest { sortType ->
            when(sortType){
                SortType.ITEM_NAME -> dao.getNameItem()
                SortType.PANTRY_STAT -> dao.getAvailableItem()
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
    private val _state = MutableStateFlow(IngredientState())

    val state = combine(_state , _sortType, _ingredients) { state, sortType, ingredients ->
        state.copy(
            sortType = sortType,
            ingredient = ingredients
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), IngredientState())

    fun onEvent(event: IngredientEvent){
         when(event){
             is IngredientEvent.DeleteContact -> {
                 viewModelScope.launch{
                     dao.EraseItem(event.ingredient)
                 }

             }
             IngredientEvent.HideDialog -> {
                 _state.update { it.copy(
                     isAddingIngredient = false
                 )}
             }
             IngredientEvent.SaveIngredient -> {
                 val iteming = state.value.iteming
                 val pantry = state.value.pantry
                 val amounts = state.value.amounts
                 val measure = state.value.measure

                 if ( iteming.isBlank() || pantry == PantryStatus.NON|| amounts == 0 ||  measure.isBlank()){
                     return // no data to insert
                 }

                 val ingredient = Ingredient(
                     pantry = pantry,
                     iteming = iteming,
                     amounts = amounts,
                     measure = measure

                 )
                 viewModelScope.launch {
                     dao.InsertItem(ingredient)
                 }
                 _state.update { it.copy(
                     isAddingIngredient = false,
                     iteming = "",
                     amounts = 0,
                     pantry = PantryStatus.NON,
                     measure = ""

                 ) }

             }
             is IngredientEvent.Setamount -> {
                 _state.update { it.copy(
                     amounts =  event.amounts
                 ) }
             }
             is IngredientEvent.Setitemname -> {
                 _state.update { it.copy(
                     iteming =  event.iteming
                 ) }
             }
             is IngredientEvent.Setmeasure -> {
                 _state.update { it.copy(
                     measure =  event.measure
                 ) }
             }
             is IngredientEvent.Setpantry -> {
                 _state.update { it.copy(
                     pantry =  event.pantry
                 ) }
             }
             IngredientEvent.ShowDialog -> {
                 _state.update { it.copy(
                     isAddingIngredient =  true
                 ) }
             }
             is IngredientEvent.SortIngredients -> {
                 _sortType.value = event.sortType
             }
         }
    }
}