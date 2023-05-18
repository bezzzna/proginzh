package com.example.yourplace.presentation.subcategoryfragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yourplace.di.Depenseties
import com.example.yourplace.domain.models.ClassSubCategory
import com.example.yourplace.domain.usecases.GetAllSubCategoryUseCase
import kotlinx.coroutines.launch

class SubCategoryFragmentViewModel : ViewModel() {
    private val subCategoryRepository = Depenseties.subCategoryRepository
    private val GetAllSubCategoryUseCase = GetAllSubCategoryUseCase(subCategoryRepository)

    val list = MutableLiveData<List<ClassSubCategory>>()


    fun getList(){
        viewModelScope.launch {
            list.postValue(GetAllSubCategoryUseCase.execute())
        }
    }

}