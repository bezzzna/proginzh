package com.example.yourplace

import android.app.Application
import com.example.yourplace.di.Depenseties

class App:Application() {

    //создание депенсетис
    override fun onCreate() {
        super.onCreate()
        Depenseties.init(this)
    }
}