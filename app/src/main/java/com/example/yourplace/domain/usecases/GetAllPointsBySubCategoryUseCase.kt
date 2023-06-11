package com.example.yourplace.domain.usecases

import com.example.yourplace.domain.PointsRepository
import com.example.yourplace.domain.models.ClassPoint
import com.example.yourplace.presentation.mapfragment.MapFragment
import com.yandex.mapkit.geometry.Point

class GetAllPointsBySubCategoryUseCase(private val pointRepository: PointsRepository) {
    suspend fun execute(idSubCategory: Int) : List<ClassPoint>{
        return pointRepository.getPointsBySubCategoryId(idSubCategory)
    }
}