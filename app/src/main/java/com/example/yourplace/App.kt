package com.example.yourplace

import android.app.Application
import com.example.yourplace.di.Depenseties
import com.yandex.mapkit.MapKitFactory

class App:Application() {

    //создание депенсетис
    override fun onCreate() {
        super.onCreate()
        Depenseties.init(this)
        MapKitFactory.setApiKey("a6a574c4-571b-42d1-9e93-62c8772ce8ef")
        MapKitFactory.initialize(this)
    }
}