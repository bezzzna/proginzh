package com.example.yourplace.domain

import com.example.yourplace.data.room.entity.Category

interface CategoryRepository {
    suspend fun getCategory():List<Category>
}