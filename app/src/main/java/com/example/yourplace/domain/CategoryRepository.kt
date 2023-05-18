package com.example.yourplace.domain

import com.example.yourplace.domain.models.ClassCategory

interface CategoryRepository {
    suspend fun getCategory():List<ClassCategory>
}