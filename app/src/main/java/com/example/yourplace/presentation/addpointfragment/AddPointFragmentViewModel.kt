package com.example.yourplace.presentation.addpointfragment

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yourplace.R
import com.example.yourplace.di.Depenseties
import com.example.yourplace.domain.models.ClassPoint
import com.example.yourplace.domain.usecases.AddPointUseCase
import com.example.yourplace.domain.usecases.GetAllPointsBySubCategoryUseCase
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.yandex.mapkit.Animation
import com.yandex.mapkit.directions.DirectionsFactory
import com.yandex.mapkit.geometry.Geo
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.runtime.image.ImageProvider
import kotlinx.coroutines.launch

class AddPointFragmentViewModel: ViewModel() {



    private val addPointRepository = Depenseties.pointRepository
    private val addPointUseCase = AddPointUseCase(addPointRepository)
    private val getAllPointsBySubCategoryUseCase = GetAllPointsBySubCategoryUseCase(addPointRepository)

    val list = MutableLiveData<List<ClassPoint>>()
    var currentPoint: Point = Point(0.0, 0.0)

//    fun getDistance():Int{
//        val currentPoint = MapFragment.currentPoint
//        val listX = MapFragment.listX
//        val listY = MapFragment.listY
//        var distance = 0
//        for (i in 0  until listX.count()){
//            val point = Point(listX[i].toDouble(), listY[i].toDouble())
//            distance = Geo.distance(currentPoint,point).toInt()
//        }
//        return distance
//    }
//    fun getSortPoints(idSubCategory: Int) {
//        val distance = getDistance()
//
//        if (distance <= 2){
//            viewModelScope.launch {
//                list.postValue(getAllPointsBySubCategoryUseCase.execute(idSubCategory))
//            }
//        } else {
//            error("No Points!")
//        }
//    }


    fun getListBySubCategory(idSubCategory: Int){
        viewModelScope.launch {
            val temp = getAllPointsBySubCategoryUseCase.execute(idSubCategory)
            for (i in 0 until temp.count()){
                val point = Point(temp[i].coordinateX.toDouble(), temp[i].coordinateY.toDouble())
                temp[i].distance = Geo.distance(currentPoint, point)
            }

            list.postValue(temp.sortedBy {
                it.distance
            })
        }
    }




    fun choisePoint(point: ClassPoint) {
        viewModelScope.launch {
            addPointUseCase.execute(point)
        }
    }
}