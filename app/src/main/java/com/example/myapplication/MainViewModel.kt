package com.example.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel : ViewModel() {
    private val _numbers = MutableStateFlow<List<Int>>(listOf(1, 2, 3, 4, 5))
    val numbers: StateFlow<List<Int>> = _numbers

    fun removeNumber(number: Int) {
        _numbers.value = _numbers.value.filter { it != number }
    }
}