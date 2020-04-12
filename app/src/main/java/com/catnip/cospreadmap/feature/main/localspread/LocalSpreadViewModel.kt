package com.catnip.cospreadmap.feature.main.localspread

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.catnip.cospreadmap.data.repositories.LocalSpreadRepository

class LocalSpreadViewModel(private val repository: LocalSpreadRepository) : ViewModel() {

    val localData = repository.result
    fun getData() {
        repository.getData()
    }

    override fun onCleared() {
        super.onCleared()
        repository.onCleared()
    }
}