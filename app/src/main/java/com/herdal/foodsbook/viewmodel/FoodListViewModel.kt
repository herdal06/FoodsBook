package com.herdal.foodsbook.viewmodel

import android.app.Application
import android.widget.Toast
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
    private var updateTime = 10 * 60 * 1000 * 1000 * 1000L // nano time. 10 minute

    private val foodAPIService = FoodAPIService()
    private var disposable = CompositeDisposable()
    private val specialSharedPreferences = SpecialSharedPreferences(getApplication())

    // get data from api. get data from room if a some time has not passed
    fun refreshData() {
        val saveTime = specialSharedPreferences.getTime()
        if(saveTime != null && saveTime != 0L && System.nanoTime() - saveTime <  updateTime) {
            // get from sqlite
            getDataFromSqlite()
        }
        else {
            getDataFromApi()
        }
    }

    fun refreshFromInternet() {
        getDataFromApi()
    }

    private fun getDataFromSqlite() {
        loadingBar.value = true
        launch {
            val foodList = FoodDatabase(getApplication()).foodDao().getAllFood()
            showFoods(foodList)
            Toast.makeText(getApplication(),"data came from room",Toast.LENGTH_LONG).show()
        }
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
                        Toast.makeText(getApplication(),"data came from api",Toast.LENGTH_LONG).show()
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