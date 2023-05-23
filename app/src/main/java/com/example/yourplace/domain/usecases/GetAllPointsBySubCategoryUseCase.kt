package com.example.yourplace.domain.usecases

import com.example.yourplace.domain.PointsRepository
import com.example.yourplace.domain.models.ClassPoint

class GetAllPointsBySubCategoryUseCase(private val pointRepository: PointsRepository) {
    suspend fun execute(idSubCategory: Int) : List<ClassPoint>{
        return pointRepository.getPointsBySubCategoryId(idSubCategory)
    }
}