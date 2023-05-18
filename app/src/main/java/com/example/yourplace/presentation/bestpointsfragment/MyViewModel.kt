package com.example.yourplace.presentation.bestpointsfragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yourplace.di.Depenseties
import com.example.yourplace.data.room.entity.Points
import kotlinx.coroutines.launch

class MyViewModel: ViewModel() {

    val list = MutableLiveData<List<Points>>()

    fun getList() {
        viewModelScope.launch {
            //list.postValue(Depenseties.db.PointsDao().getAllPoints())
        }
    }
}