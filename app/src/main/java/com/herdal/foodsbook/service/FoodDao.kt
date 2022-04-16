package com.herdal.foodsbook.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.herdal.foodsbook.model.Food

@Dao
interface FoodDao {

    @Insert // INSERT INTO
    suspend fun insertAll(vararg food: Food) : List<Long> // return uids
    @Query("SELECT * FROM food")
    suspend fun getAllFood() : List<Food>
    @Query("SELECT * FROM food WHERE uid = :foodId")
    suspend fun getFood(foodId: Int) : Food
    @Query("DELETE FROM food")
    suspend fun deleteAllFood()
    @Query("DELETE FROM food WHERE uid = :foodId")
    suspend fun deleteFood(foodId: Int)
}