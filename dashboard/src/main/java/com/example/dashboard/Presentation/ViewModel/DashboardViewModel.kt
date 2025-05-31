package com.example.dashboard.Presentation.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(): ViewModel()
{
   private val _houseId= MutableLiveData<String>()
    val houseId: LiveData<String> get() = _houseId

    fun setHouseId(id: String) {
        _houseId.value = id
    }
}