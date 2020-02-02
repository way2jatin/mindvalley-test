package com.jatin.mindvalley.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jatin.mindvalley.repositories.PinboardRepository

class PinboardFragmentViewModel : ViewModel() {
    fun downloadJsonType(): MutableLiveData<PinboardRepository.Companion.DownloadTypeResponse>? {
        return PinboardRepository.getInstance()?.downloadJsonType()
    }
}