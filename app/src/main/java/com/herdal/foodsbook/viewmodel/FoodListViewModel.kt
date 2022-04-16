package com.herdal.foodsbook.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.herdal.foodsbook.model.Food
import com.herdal.foodsbook.service.FoodAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class FoodListViewModel : ViewModel() {
    val foods = MutableLiveData<List<Food>>()
    val errorMessage = MutableLiveData<Boolean>()
    val loadingBar = MutableLiveData<Boolean>()

    private val foodAPIService = FoodAPIService()
            private var disposable = CompositeDisposable()

    // get data from api. get data from room if a some time has not passed
    fun refreshData() {
        getDataFromApi()
    }

    private fun getDataFromApi() {
        loadingBar.value = true

        disposable.add(
            foodAPIService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<List<Food>>() {
                    override fun onSuccess(t: List<Food>) {

                    }

                    override fun onError(e: Throwable) {
                        errorMessage.value = true
                        loadingBar.value = false
                        e.printStackTrace()
                    }
                })
        )
    }

    private fun showFoods(foodList: List<Food>) {
        foods.value = foodList
        errorMessage.value = false
        loadingBar.value = false
    }
}