package com.example.yourplace.data

import com.example.yourplace.domain.PointsRepository
import com.example.yourplace.data.room.dao.PointsDao
import com.example.yourplace.data.room.entity.Points
import com.example.yourplace.domain.models.ClassPoint

class PointsRepositoryImpl(private val pointsDao: PointsDao): PointsRepository {
    override suspend fun getPointById(pointId: Int): ClassPoint {
        return pointToClassPoint(pointsDao.getPointById(pointId))
    }

    override suspend fun getAllChoisedPoints(): List<ClassPoint> {
        return pointsDao.getAllChoisedPoints().map {
            pointToClassPoint(it)
        }
    }

    override suspend fun update(point: ClassPoint) {
        pointsDao.updatePoint(classPointToPoint(point))
    }

    override suspend fun getPointsBySubCategoryId(subCategoryId: Int) : List<ClassPoint> {
        return pointsDao.getAllPointsBySubCategoryId(subCategoryId).map {
            pointToClassPoint(it)
        }
    }

    private fun pointToClassPoint(point: Points) : ClassPoint {
        return ClassPoint(
            id = point.id,
            name = point.name,
            image = point.image,
            address = point.address,
            idSubCategory = point.idSubCategory,
            rate = point.rate,
            isChoised = point.isChoised,
            priority = point.priority,
            coordinateX = point.coordinateX,
            coordinateY = point.coordinateY
        )
    }

    private fun classPointToPoint(classPoint: ClassPoint) : Points {
        return Points(
            id = classPoint.id,
            name = classPoint.name,
            image = classPoint.image,
            address = classPoint.address,
            idSubCategory = classPoint.idSubCategory,
            rate = classPoint.rate,
            isChoised = classPoint.isChoised,
            priority = classPoint.priority,
            coordinateX = classPoint.coordinateX,
            coordinateY = classPoint.coordinateY
        )
    }
}