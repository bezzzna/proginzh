package com.example.yourplace.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.yourplace.data.room.dao.CategoryDao
import com.example.yourplace.data.room.dao.PointsDao
import com.example.yourplace.data.room.dao.SubCategoryDao
import com.example.yourplace.data.room.entity.Category
import com.example.yourplace.data.room.entity.Points
import com.example.yourplace.data.room.entity.SubCategory

@Database(entities = [Points::class, Category::class, SubCategory::class], version = 1)
abstract class MainDb:RoomDatabase() {
    //использование интерфейса
    abstract fun PointsDao(): PointsDao

    abstract fun categoryDao(): CategoryDao

    abstract fun subCategory(): SubCategoryDao

}