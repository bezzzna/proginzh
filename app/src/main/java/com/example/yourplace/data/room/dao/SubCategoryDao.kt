package com.example.yourplace.data.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.yourplace.data.room.entity.SubCategory

@Dao
abstract class SubCategoryDao {
    @Query("SELECT * FROM SubCategory WHERE idCategory = :categoryId")
    abstract suspend fun getAllSubCategoryByCategoryId(categoryId: Int):List<SubCategory>
}