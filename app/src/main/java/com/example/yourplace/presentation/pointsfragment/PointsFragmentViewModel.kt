package com.example.yourplace.presentation.pointsfragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yourplace.di.Depenseties
import com.example.yourplace.domain.models.ClassPoint
import com.example.yourplace.domain.usecases.GetAllPointsUseCase
import kotlinx.coroutines.launch

class PointsFragmentViewModel : ViewModel() {

    private val pointRepository = Depenseties.pointRepository

    private val getAllPointsUseCase = GetAllPointsUseCase(pointRepository)

    val list = MutableLiveData<List<ClassPoint>>()


    fun getList() {
        viewModelScope.launch {
            list.postValue(getAllPointsUseCase.execute())
        }
    }


}