package com.jatin.asyncmodule.interfaces

import com.jatin.asyncmodule.models.BaseDownloadResource

interface DataDownloadCallbacks {
    fun onStart(mDownloadDataType: BaseDownloadResource)

    fun onSuccess(mDownloadDataType: BaseDownloadResource)

    fun onFailure(mDownloadDataType: BaseDownloadResource, statusCode: Int, errorResponse: ByteArray?, e: Throwable?)

}
