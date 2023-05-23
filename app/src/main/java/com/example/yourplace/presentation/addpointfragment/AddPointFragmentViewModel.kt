package com.example.yourplace.presentation.addpointfragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yourplace.di.Depenseties
import com.example.yourplace.domain.models.ClassPoint
import com.example.yourplace.domain.usecases.AddPointUseCase
import com.example.yourplace.domain.usecases.GetAllPointsBySubCategoryUseCase
import kotlinx.coroutines.launch

class AddPointFragmentViewModel: ViewModel() {

    private val addPointRepository = Depenseties.pointRepository

    private val addPointUseCase = AddPointUseCase(addPointRepository)
    private val getAllPointsBySubCategoryUseCase = GetAllPointsBySubCategoryUseCase(addPointRepository)

    val list = MutableLiveData<List<ClassPoint>>()

    fun getListBySubCategory(idSubCategory: Int){
        viewModelScope.launch {
            list.postValue(getAllPointsBySubCategoryUseCase.execute(idSubCategory))
        }
    }

    fun choisePoint(point: ClassPoint) {
        viewModelScope.launch {
            addPointUseCase.execute(point)
        }
    }
}