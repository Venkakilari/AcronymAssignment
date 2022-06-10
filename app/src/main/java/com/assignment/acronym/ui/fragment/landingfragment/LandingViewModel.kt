package com.assignment.acronym.ui.fragment.landingfragment

import android.app.Application
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.assignment.acronym.ui.fragment.BaseViewModel
import com.assignment.data.model.networkmodel.AcronymResponse
import com.assignment.network.remote.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LandingViewModel @Inject constructor(
    private val repository: Repository,
    application: Application
): BaseViewModel(application) {

    private val _response: MutableLiveData<List<String>?> =
        MutableLiveData()
    val response: LiveData<List<String>?> = _response

    fun fetchByShortForm(sf: String) = viewModelScope.launch {
        showLoading()
        repository.getByShortForm(sf).collect {
            hideLoading()
            _response.value = transformResponseToList(it.data)
        }
    }

    fun fetchByLongForm(lf: String) = viewModelScope.launch {
        showLoading()
        repository.getByLongForm(lf).collect {
            hideLoading()
            _response.value = transformResponseToList(it.data)
        }
    }

    fun transformResponseToList(response: List<AcronymResponse>?): List<String> {
        val meanings = mutableListOf<String>()
        response?.map { it.lfs?.map { it.lf } }?.forEach {
            it?.takeIf { it.isNotEmpty() }?.forEach {
                it?.let {
                    meanings.add(it)
                }
            }
        }
        return meanings
    }
}