package com.example.yourplace.domain

import com.example.yourplace.data.room.entity.SubCategory

interface SubCategoryRepository {
    suspend fun getSubCategory():List<SubCategory>
}