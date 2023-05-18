package com.example.yourplace.data

import com.example.yourplace.domain.CategoryRepository
import com.example.yourplace.data.room.dao.CategoryDao
import com.example.yourplace.data.room.entity.Category

class CategoryRepositoryImpl(private val categoryDao: CategoryDao): CategoryRepository {
    override suspend fun getCategory():List<Category> {
        return categoryDao.getAllCategory()
    }
}