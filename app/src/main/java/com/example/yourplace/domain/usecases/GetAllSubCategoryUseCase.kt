package com.example.yourplace.domain.usecases

import com.example.yourplace.domain.SubCategoryRepository
import com.example.yourplace.domain.models.ClassSubCategory

class GetAllSubCategoryUseCase(private val subCategoryRepository: SubCategoryRepository) {

    suspend fun execute(idCategory: Int):List<ClassSubCategory>{
        return subCategoryRepository.getSubCategoryByCategoryId(idCategory)
    }

}