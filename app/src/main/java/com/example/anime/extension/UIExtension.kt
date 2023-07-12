package com.example.anime.extension

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.anime.R

infix fun ImageView.loadImage(url: Any?): Target<Drawable> = let {
    val requestOptions = RequestOptions().format(DecodeFormat.PREFER_ARGB_8888)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .placeholder(R.color.white)
        .error(R.color.white)
    Glide.with(this.context).setDefaultRequestOptions(requestOptions).load(url).into(this)
}

fun Context.toast(resourceId: Int) = toast(getString(resourceId))

fun Context.toast(message: CharSequence?) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()