package com.example.yourplace.presentation.pointsfragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yourplace.di.Depenseties
import com.example.yourplace.domain.models.ClassPoint
import com.example.yourplace.domain.usecases.DeletePointUseCase
import com.example.yourplace.domain.usecases.GetAllChoisedPointsUseCase
import com.example.yourplace.domain.usecases.SwapPointUseCase
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class PointsFragmentViewModel : ViewModel() {

    private val pointRepository = Depenseties.pointRepository

    private val getAllChoisedPointsUseCase = GetAllChoisedPointsUseCase(pointRepository)
    private val deletePointUseCase = DeletePointUseCase(pointRepository)
    private val swapPointUseCase = SwapPointUseCase(pointRepository)

    val list: MutableLiveData<List<ClassPoint>> by lazy {
        pointRepository.choisedList
    }


    fun deletePoint(point: ClassPoint) {
        viewModelScope.launch {
            deletePointUseCase.execute(point)

        }
    }

    fun getList() {
        viewModelScope.launch {
            getAllChoisedPointsUseCase.execute()
        }
    }
    fun swapPoint(point1: ClassPoint, point2: ClassPoint) {
        viewModelScope.launch {
            swapPointUseCase.execute(point1, point2)
        }
    }


}