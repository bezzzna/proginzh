package com.example.yourplace.presentation.pointsfragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yourplace.di.Depenseties
import com.example.yourplace.domain.models.ClassPoint
import com.example.yourplace.domain.usecases.DeletePointUseCase
import com.example.yourplace.domain.usecases.GetAllChoisedPointsUseCase
import com.example.yourplace.domain.usecases.GetAllPointsBySubCategoryUseCase
import kotlinx.coroutines.launch

class PointsFragmentViewModel : ViewModel() {

    private val pointRepository = Depenseties.pointRepository

    private val getAllChoisedPointsUseCase = GetAllChoisedPointsUseCase(pointRepository)
    private val deletePointUseCase = DeletePointUseCase(pointRepository)

    val list = MutableLiveData<List<ClassPoint>>()

    fun getList() {
        viewModelScope.launch {
            list.postValue(getAllChoisedPointsUseCase.execute())
        }
    }

    fun deletePoint(point: ClassPoint) {
        viewModelScope.launch {
            deletePointUseCase.execute(point)
            getList()
        }
    }


}