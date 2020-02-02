package com.jatin.asyncmodule.interfaces


import com.jatin.asyncmodule.models.BaseDownloadResource


interface ProvideCallbacks {
    fun onComplete(mDownloadDataType: BaseDownloadResource)
    fun onFailure(mDownloadDataType: BaseDownloadResource)
    fun onCancel(mDownloadDataType: BaseDownloadResource)
}
