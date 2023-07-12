package com.example.anime.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.anime.extension.toast
import com.google.gson.Gson
import com.google.gson.GsonBuilder

abstract class BaseActivity: AppCompatActivity() {

    lateinit var mGson: Gson

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mGson = GsonBuilder().create()
    }

    fun uiShowError(message: String?) {
        toast(message)
    }
}