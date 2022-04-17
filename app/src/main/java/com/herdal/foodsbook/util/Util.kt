package com.herdal.foodsbook.util

import android.content.Context
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.herdal.foodsbook.R

fun ImageView.downloadImage(url: String?, placeholder: CircularProgressDrawable) {
    val options = RequestOptions().placeholder(placeholder).error(R.mipmap.ic_launcher_round)
    Glide.with(context).setDefaultRequestOptions(options).load(url).into(this)
}

fun getPlaceHolder(context: Context) : CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 8f // width size
        centerRadius = 40f // radius size
        start()
    }
}
@BindingAdapter("android:downloadImage")
fun downloadImage(view: ImageView,url: String?) {
    view.downloadImage(url, getPlaceHolder(view.context))
}