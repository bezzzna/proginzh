package com.example.yourplace.data.room.dao

import androidx.room.*
import com.example.yourplace.data.room.entity.Category


@Dao
abstract class CategoryDao {

    @Query("SELECT * FROM Category")
    abstract suspend fun getAllCategory():List<Category>

}