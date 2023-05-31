package com.example.yourplace.data.room.dao
import android.database.Cursor
import androidx.room.*
import com.example.yourplace.data.room.entity.Points



@Dao
abstract class PointsDao {

    @Query("SELECT * FROM Points WHERE isChoised = 1" )
    abstract suspend fun getAllChoisedPoints(): List<Points>

    @Query("SELECT * FROM Points WHERE id = :pointId")
    abstract suspend fun getPointById(pointId: Int): Points

    @Query("SELECT * FROM Points WHERE idSubCategory = :subcategoryId AND isChoised = 0 LIMIT :limit OFFSET :offset")
    abstract suspend fun getAllPointsBySubCategoryId(subcategoryId: Int, offset: Int, limit: Int): List<Points>

    @Update
    abstract suspend fun updatePoint(points: Points)

}