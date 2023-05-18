package com.example.yourplace.data.room.dao
import androidx.room.*
import com.example.yourplace.data.room.entity.Points



@Dao
abstract class PointsDao {

    @Query("SELECT * FROM Points" )
    abstract suspend fun getAllPoints(): List<Points>

    @Query("SELECT * FROM Points WHERE id = :pointId")
    abstract suspend fun getPointById(pointId: Int): Points

    @Query("SELECT * FROM Points WHERE idSubCategory = :subcategoryId")
    abstract suspend fun getAllPointsBySubCategoryId(subcategoryId: Int): List<Points>

    @Update
    abstract suspend fun updatePoint(points: Points)

}