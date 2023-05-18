package com.example.yourplace.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Category (
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    @ColumnInfo()
    var name:String

)