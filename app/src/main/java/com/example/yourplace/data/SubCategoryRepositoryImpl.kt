package com.example.yourplace.data

import com.example.yourplace.domain.SubCategoryRepository
import com.example.yourplace.data.room.dao.SubCategoryDao
import com.example.yourplace.data.room.entity.SubCategory

class SubCategoryRepositoryImpl(private val subCategoryDao: SubCategoryDao): SubCategoryRepository {
    override suspend fun getSubCategory():List<SubCategory> {
        return subCategoryDao.getAllSubCategory()
    }
}