package com.example.yourplace.domain

import androidx.lifecycle.MutableLiveData
import com.example.yourplace.domain.models.ClassPoint

interface PointsRepository {
    suspend fun getPointById(pointId: Int): ClassPoint
    suspend fun getAllChoisedPoints()
    suspend fun update(point: ClassPoint)
    suspend fun getPointsBySubCategoryId(subCategoryId: Int): List<ClassPoint>
    suspend fun getPriorityInc() : Int
}