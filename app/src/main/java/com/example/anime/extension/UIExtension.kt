package com.example.anime.extension

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.anime.R

class UIExtension {
    companion object {
        @BindingAdapter("loadImageFormUrl")
        @JvmStatic
        fun loadImageFromUrl(imageView: ImageView, imageUrl: String?) {
            imageView.loadImage(imageUrl)
        }
    }
}

fun View.show() = let { this.visibility = View.VISIBLE }

fun View.hide() = let { this.visibility = View.GONE }

infix fun ImageView.loadImage(url: Any?): Target<Drawable> = let {
    val requestOptions = RequestOptions().format(DecodeFormat.PREFER_ARGB_8888)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .placeholder(R.color.white)
        .error(R.color.white)
    Glide.with(this.context).setDefaultRequestOptions(requestOptions).load(url).into(this)
}

inline fun <reified T: Activity> Activity.navigate(func: Intent.() -> Unit) {
    val intent = Intent(this, T::class.java)
    intent.func()
    startActivity(intent)
}

fun Context.toast(message: CharSequence?) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()