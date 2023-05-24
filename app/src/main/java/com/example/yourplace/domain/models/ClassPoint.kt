package com.example.yourplace.domain.models

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class ClassPoint(
    var id: Int? = null,
    var name: String,
    var image: ByteArray,
    var address: String,
    var idSubCategory: Int,
    var rate: Float,
    var isChoised: Boolean,
    var priority: Int,
    var coordinateX: Float,
    var coordinateY: Float
)