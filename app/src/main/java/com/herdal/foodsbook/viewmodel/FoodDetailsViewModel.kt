package com.herdal.foodsbook.viewmodel

import android.widget.ImageView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.herdal.foodsbook.model.Food

class FoodDetailsViewModel : ViewModel() {
    val foodLiveData = MutableLiveData<Food>()

    fun getRoomData() {
        val apple = Food("apple","100","10","5","1","image")
        foodLiveData.value = apple
    }
}