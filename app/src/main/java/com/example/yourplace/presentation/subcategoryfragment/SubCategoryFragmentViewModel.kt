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
    private val getAllSubCategoryUseCase = GetAllSubCategoryUseCase(subCategoryRepository)

    val list = MutableLiveData<List<ClassSubCategory>>()


    fun getSubCategoryListByCategoryId(idCategory: Int){
        viewModelScope.launch {
            list.postValue(getAllSubCategoryUseCase.execute(idCategory))
        }
    }

}