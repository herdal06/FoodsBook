package com.herdal.foodsbook.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Food(
    @ColumnInfo(name = "isim")
    @SerializedName("isim")
    val name: String?,
    @ColumnInfo(name = "kalori")
    @SerializedName("kalori")
    val calorie: String?,
    @ColumnInfo(name = "karbonhidrat")
    @SerializedName("karbonhidrat")
    val carbohydrate: String?,
    @ColumnInfo(name = "protein")
    @SerializedName("protein")
    val protein: String?,
    @ColumnInfo(name = "yag")
    @SerializedName("yag")
    val fat: String?,
    @ColumnInfo(name = "gorsel")
    @SerializedName("gorsel")
    val image: String?
    ) {
    @PrimaryKey(autoGenerate = true)
    val uid: Int = 0
}