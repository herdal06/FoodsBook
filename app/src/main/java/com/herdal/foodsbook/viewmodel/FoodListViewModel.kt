package com.herdal.foodsbook.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.herdal.foodsbook.model.Food
import com.herdal.foodsbook.service.FoodAPIService
import com.herdal.foodsbook.service.FoodDatabase
import com.herdal.foodsbook.util.SpecialSharedPreferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class FoodListViewModel(application: Application) : BaseViewModel(application) {
    val foods = MutableLiveData<List<Food>>()
    val errorMessage = MutableLiveData<Boolean>()
    val loadingBar = MutableLiveData<Boolean>()

    private val foodAPIService = FoodAPIService()
    private var disposable = CompositeDisposable()
    private val specialSharedPreferences = SpecialSharedPreferences(getApplication())

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
                    override fun onSuccess(foodList: List<Food>) {
                        saveToSQLite(foodList)
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

    private fun saveToSQLite(foodList: List<Food>) {
        launch {
            val dao = FoodDatabase(getApplication()).foodDao()
            dao.deleteAllFood() // clear db
            val uidList = dao.insertAll(*foodList.toTypedArray())
            var i = 0
            while(i < foodList.size) {
                foodList[i].uid = uidList[i].toInt()
                i += 1
            }
            showFoods(foodList)
        }
        specialSharedPreferences.saveTime(System.nanoTime())

    }
}