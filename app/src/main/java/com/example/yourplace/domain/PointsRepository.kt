package com.example.yourplace.domain

import com.example.yourplace.domain.models.ClassPoint

interface PointsRepository {
    suspend fun getPointById(pointId: Int): ClassPoint
    suspend fun getAllChoisedPoints(): List<ClassPoint>
    suspend fun update(point: ClassPoint)
    suspend fun getPointsBySubCategoryId(subCategoryId: Int): List<ClassPoint>
}