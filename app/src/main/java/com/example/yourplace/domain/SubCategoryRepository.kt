package com.example.yourplace.domain


import com.example.yourplace.domain.models.ClassSubCategory

interface SubCategoryRepository {
    suspend fun getSubCategoryByCategoryId(categoryId:Int):List<ClassSubCategory>
}