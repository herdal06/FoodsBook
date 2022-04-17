package com.herdal.foodsbook.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.herdal.foodsbook.model.Food
import com.herdal.foodsbook.service.FoodDatabase
import kotlinx.coroutines.launch

class FoodDetailsViewModel(application: Application) : BaseViewModel(application) {
    val foodLiveData = MutableLiveData<Food>()

    fun getRoomData(uid: Int) {
        launch {
            val dao = FoodDatabase(getApplication()).foodDao()
            val food = dao.getFood(uid)
            foodLiveData.value = food
        }
    }
}