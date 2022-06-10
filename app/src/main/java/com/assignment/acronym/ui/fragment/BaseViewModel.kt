package com.assignment.acronym.ui.fragment

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

open class BaseViewModel(application: Application) : AndroidViewModel(application) {
    private val _loading: MutableLiveData<Int> = MutableLiveData(View.GONE)
    val loading: LiveData<Int> = _loading

    fun showLoading() {
        _loading.postValue(View.VISIBLE)
    }

    fun hideLoading() {
        _loading.postValue(View.GONE)
    }
}