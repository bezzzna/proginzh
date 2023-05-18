package com.example.yourplace.domain.usecases

import com.example.yourplace.domain.PointsRepository
import com.example.yourplace.domain.models.ClassPoint

class GetAllPointsUseCase(private val pointRepository: PointsRepository) {
    suspend fun execute() : List<ClassPoint>{
        return pointRepository.getAllPoints()
    }
}