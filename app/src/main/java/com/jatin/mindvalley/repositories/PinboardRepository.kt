package com.jatin.mindvalley.repositories

import androidx.lifecycle.MutableLiveData
import com.google.gson.reflect.TypeToken
import com.jatin.asyncmodule.interfaces.DataDownloadCallbacks
import com.jatin.asyncmodule.models.BaseDownloadResource
import com.jatin.asyncmodule.models.DownloadTypeJson
import com.jatin.asyncmodule.utils.DownloadProvider
import com.jatin.mindvalley.constants.AppConstants
import com.jatin.mindvalley.enums.StatusEnum
import com.jatin.mindvalley.newModels.PinboardModel

class PinboardRepository(private val mProvider: DownloadProvider? = DownloadProvider.getInstance()) {
    companion object {
        private var instance: PinboardRepository? = null
        private var liveJsonData = MutableLiveData<DownloadTypeResponse>()

        class DownloadTypeResponse(
            var mStatus: StatusEnum = StatusEnum.START,
            var mData: ArrayList<*>? = null,
            var mError: Throwable? = null
        )

        fun getInstance(): PinboardRepository? {
            if (instance == null) {
                instance = PinboardRepository()
            }
            return instance
        }
    }

    fun downloadJsonType(): MutableLiveData<DownloadTypeResponse> {
        val mDataTypeJson = DownloadTypeJson(AppConstants.BASE_URL, object : DataDownloadCallbacks {
            override fun onStart(mDownloadDataType: BaseDownloadResource) {
                liveJsonData.value = DownloadTypeResponse(StatusEnum.START, null, null)
            }

            override fun onSuccess(mDownloadDataType: BaseDownloadResource) {
                val type = object : TypeToken<List<PinboardModel>>() {
                }.type
                val lstBoard = (mDownloadDataType as DownloadTypeJson).getJson(type) as ArrayList<PinboardModel>
                liveJsonData.value = DownloadTypeResponse(StatusEnum.SUCCESS, lstBoard, null)
            }

            override fun onFailure(
                mDownloadDataType: BaseDownloadResource,
                statusCode: Int,
                errorResponse: ByteArray?,
                e: Throwable?
            ) {
                liveJsonData.value = DownloadTypeResponse(StatusEnum.FAILURE, null, e)
            }

        })
        mProvider?.getRequest(mDataTypeJson)
        return liveJsonData
    }
}