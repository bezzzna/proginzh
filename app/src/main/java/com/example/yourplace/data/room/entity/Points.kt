package com.example.yourplace.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.android.gms.location.Priority

//таблица мест поесть
@Entity(tableName = "Points")
data class Points(
        //генерация ключей и столбцов
        @PrimaryKey(autoGenerate = true)
        var id: Int? = null,
        @ColumnInfo(name = "name")
        var name: String,
        @ColumnInfo(name = "image", typeAffinity = ColumnInfo.BLOB)
        var image: ByteArray,
        @ColumnInfo(name = "address")
        var address: String,
        @ColumnInfo(name = "idSubCategory")
        var idSubCategory: Int,
        @ColumnInfo(name = "Rate")
        var rate: Float,
        @ColumnInfo(name = "isChoised")
        var isChoised: Boolean,
        @ColumnInfo(name = "priority")
        var priority: Int
        )