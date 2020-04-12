package com.catnip.cospreadmap.feature.main.globalspread

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.catnip.cospreadmap.data.repositories.GlobalSpreadRepository

class GlobalSpreadViewModel(private val repository: GlobalSpreadRepository) : ViewModel() {

    val globalData = repository.result
    fun getData() {
        repository.getData()
    }

    override fun onCleared() {
        super.onCleared()
        repository.onCleared()
    }
}