package com.example.yourplace.domain.usecases

import com.example.yourplace.domain.PointsRepository
import com.example.yourplace.domain.models.ClassPoint


class AddPointUseCase(private val pointsRepository: PointsRepository) {
    suspend fun execute(point: ClassPoint){
        point.isChoised = true
        pointsRepository.update(point)
    }
}