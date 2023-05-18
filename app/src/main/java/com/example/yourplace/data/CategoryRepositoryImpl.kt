package com.example.yourplace.data

import com.example.yourplace.domain.CategoryRepository
import com.example.yourplace.data.room.dao.CategoryDao
import com.example.yourplace.data.room.entity.Category
import com.example.yourplace.domain.models.ClassCategory

class CategoryRepositoryImpl(private val categoryDao: CategoryDao): CategoryRepository {
    override suspend fun getCategory():List<ClassCategory> {
        return categoryDao.getAllCategory().map{
            categoryToClassCategory(it)

        }
    }

    private fun categoryToClassCategory(category: Category):ClassCategory{
        return ClassCategory(
            id = category.id,
            name = category.name
        )

    }
}