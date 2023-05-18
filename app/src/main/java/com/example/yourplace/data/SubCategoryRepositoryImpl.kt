package com.example.yourplace.data

import com.example.yourplace.domain.SubCategoryRepository
import com.example.yourplace.data.room.dao.SubCategoryDao
import com.example.yourplace.data.room.entity.SubCategory
import com.example.yourplace.domain.models.ClassSubCategory

class SubCategoryRepositoryImpl(private val subCategoryDao: SubCategoryDao): SubCategoryRepository {

    private fun subCategoryToClassSubCategory(subCategory: SubCategory):ClassSubCategory{
        return ClassSubCategory(
            id = subCategory.id,
            name = subCategory.name,
            idCategory = subCategory.idCategory
        )

    }

    override suspend fun getSubCategoryByCategoryId(categoryId: Int): List<ClassSubCategory> {
        return subCategoryDao.getAllSubCategoryByCategoryId(categoryId).map{
            subCategoryToClassSubCategory(it)
        }
    }
}