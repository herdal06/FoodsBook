package com.herdal.foodsbook.service

import com.herdal.foodsbook.model.Food
import io.reactivex.Single
import retrofit2.http.GET

interface FoodAPI {

    // BASE_URL -> https://raw.githubusercontent.com/
    // atilsamancioglu/BTK20-JSONVeriSeti/master/besinler.json
    @GET("atilsamancioglu/BTK20-JSONVeriSeti/master/besinler.json")
    fun getFood() : Single<List<Food>>
}