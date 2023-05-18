package com.example.yourplace.presentation.categoryfragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yourplace.di.Depenseties
import com.example.yourplace.domain.models.ClassCategory
import com.example.yourplace.domain.usecases.GetAllCategoryUseCase
import kotlinx.coroutines.launch

class CategoryFragmentViewModel:ViewModel() {

    private val categoryRepository = Depenseties.categoryRepository

    private val getAllCategoryUseCase = GetAllCategoryUseCase(categoryRepository)

    val list = MutableLiveData<List<ClassCategory>>()

    fun getList(){
        viewModelScope.launch {
            list.postValue(getAllCategoryUseCase.execute())
        }
    }
}