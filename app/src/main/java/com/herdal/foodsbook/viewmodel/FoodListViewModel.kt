package com.herdal.foodsbook.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.herdal.foodsbook.model.Food

class FoodListViewModel : ViewModel() {
    val foods = MutableLiveData<List<Food>>()
    val errorMessage = MutableLiveData<Boolean>()
    val loadingBar = MutableLiveData<Boolean>()
}