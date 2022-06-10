package com.assignment.acronym.ui.fragment.meaningfragment

import android.app.Application
import com.assignment.acronym.ui.fragment.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MeaningViewModel @Inject constructor(
    application: Application
) : BaseViewModel(application)