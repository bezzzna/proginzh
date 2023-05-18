package com.example.yourplace.domain.usecases

import com.example.yourplace.domain.CategoryRepository
import com.example.yourplace.domain.models.ClassCategory

class GetAllCategoryUseCase(private val categoryRepository: CategoryRepository) {

    suspend fun execute():List<ClassCategory>{
        return categoryRepository.getCategory()
    }
}