package com.example.yourplace.di

import android.content.Context
import androidx.room.Room
import com.example.yourplace.data.CategoryRepositoryImpl
import com.example.yourplace.data.PointsRepositoryImpl
import com.example.yourplace.data.SubCategoryRepositoryImpl
import com.example.yourplace.data.room.MainDb

object Depenseties {
    private lateinit var context: Context
    fun init(context:Context){
        Depenseties.context = context
    }


    //получение базы данных
    private val db by lazy{
        Room.databaseBuilder(
            context, MainDb::class.java,
            "YourPlace.db")
            //.createFromAsset("database/YourPlace_db.db")
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