package com.example.yourplace.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "SubCategory")
data class SubCategory (
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    @ColumnInfo()
    var name:String,
    @ColumnInfo()
    var idCategory:Int
)