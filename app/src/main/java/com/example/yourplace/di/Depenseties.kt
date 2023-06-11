package com.example.yourplace.di

import android.content.Context
import androidx.room.Room
import com.example.yourplace.data.CategoryRepositoryImpl
import com.example.yourplace.data.PointsRepositoryImpl
import com.example.yourplace.data.SubCategoryRepositoryImpl
import com.example.yourplace.data.room.MainDb
import com.yandex.mapkit.geometry.Point

object Depenseties {
    lateinit var context: Context

    fun init(context:Context){
        Depenseties.context = context
    }


    //получение базы данных
    private val db by lazy{
        Room.databaseBuilder(
            context, MainDb::class.java,
            "YourPlace.db")
            .createFromAsset("database/YourPlace.db")
            .build()
    }

    val pointRepository by lazy {
        PointsRepositoryImpl(db.PointsDao())
    }
    val subCategoryRepository by lazy {
        SubCategoryRepositoryImpl(db.subCategoryDao())
    }

    val categoryRepository by lazy{
        CategoryRepositoryImpl(db.categoryDao())
    }



}