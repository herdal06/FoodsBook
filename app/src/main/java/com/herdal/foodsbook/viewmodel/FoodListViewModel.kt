package com.herdal.foodsbook.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.herdal.foodsbook.model.Food

class FoodListViewModel : ViewModel() {
    val foods = MutableLiveData<List<Food>>()
    val errorMessage = MutableLiveData<Boolean>()
    val loadingBar = MutableLiveData<Boolean>()

    fun refreshData() {
        val apple = Food("apple","100","10","5","1","image")
        val strawberry = Food("strawberry","100","10","5","1","image")
        val watermelon = Food("watermelon","100","10","5","1","image")

        val foodList = arrayListOf<Food>(apple,strawberry,watermelon)
        foods.value = foodList
        errorMessage.value = false
        loadingBar.value = false
    }
}