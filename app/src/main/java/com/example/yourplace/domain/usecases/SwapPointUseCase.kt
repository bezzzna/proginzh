package com.example.yourplace.domain.usecases

import com.example.yourplace.domain.PointsRepository
import com.example.yourplace.domain.models.ClassPoint

class SwapPointUseCase(private val pointsRepository: PointsRepository) {
    suspend fun execute(point1:ClassPoint, point2: ClassPoint){
        val temp = point1.priority
        point1.priority = point2.priority
        point2.priority = temp
        pointsRepository.update(point1)
        pointsRepository.update(point2)
    }
}