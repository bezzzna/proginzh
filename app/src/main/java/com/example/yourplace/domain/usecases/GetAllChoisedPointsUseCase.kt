package com.example.yourplace.domain.usecases

import androidx.lifecycle.MutableLiveData
import com.example.yourplace.domain.PointsRepository
import com.example.yourplace.domain.models.ClassPoint

class GetAllChoisedPointsUseCase(private val pointRepository: PointsRepository) {
    suspend fun execute() {
        return pointRepository.getAllChoisedPoints()
    }
}