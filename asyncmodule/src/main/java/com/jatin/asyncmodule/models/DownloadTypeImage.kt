package com.jatin.asyncmodule.models

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.jatin.asyncmodule.interfaces.DataDownloadCallbacks


class DownloadTypeImage(url: String, imDownloadDataType: DataDownloadCallbacks) :
    BaseDownloadResource(url, DataTypeEnum.IMAGE, imDownloadDataType) {

    val imageBitmap: Bitmap?
        get() = data?.size?.let { BitmapFactory.decodeByteArray(data, 0, it) }

    override fun getCopyOfMe(imDownloadDataType: DataDownloadCallbacks): BaseDownloadResource {
        return DownloadTypeImage(this.url, imDownloadDataType)
    }
}
