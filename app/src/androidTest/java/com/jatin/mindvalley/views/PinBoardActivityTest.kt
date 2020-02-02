package com.jatin.mindvalley.views

import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.jatin.asyncmodule.interfaces.DataDownloadCallbacks
import com.jatin.asyncmodule.models.BaseDownloadResource
import com.jatin.asyncmodule.models.DataTypeEnum
import com.jatin.asyncmodule.models.DownloadTypeImage
import com.jatin.asyncmodule.models.DownloadTypeJson
import com.jatin.asyncmodule.utils.DownloadProvider
import com.jatin.mindvalley.views.activities.PinBoardActivity
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PinBoardActivityTest {
    @get:Rule
    var activityRule: ActivityTestRule<PinBoardActivity>
            = ActivityTestRule(PinBoardActivity::class.java)

    @Test
    fun testJsonDownload(){
        val mProvider = DownloadProvider.getInstance()
        val mDataTypeJson = DownloadTypeJson("https://pastebin.com/raw/wgkJgazE", object : DataDownloadCallbacks {
            override fun onStart(mDownloadDataType: BaseDownloadResource) {
                Log.e("TESTING", "START")
            }

            override fun onSuccess(mDownloadDataType: BaseDownloadResource) {
                assertEquals(DataTypeEnum.JSON, mDownloadDataType.getmDataType())
            }

            override fun onFailure(
                mDownloadDataType: BaseDownloadResource,
                statusCode: Int,
                errorResponse: ByteArray?,
                e: Throwable?
            ) {
                assertFalse(false)
            }

        })
        mProvider?.getRequest(mDataTypeJson)
    }
    @Test
    fun testImageDownload(){
        val mProvider = DownloadProvider.getInstance()
        val mDataTypeJson = DownloadTypeImage("https://images.unsplash.com/profile-1464495186405-68089dcd96c3?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&fit=crop&h=32&w=32&s=63f1d805cffccb834cf839c719d91702", object : DataDownloadCallbacks {
            override fun onStart(mDownloadDataType: BaseDownloadResource) {
                Log.e("TESTING", "START")
            }
            override fun onSuccess(mDownloadDataType: BaseDownloadResource) {
                assertEquals(DataTypeEnum.IMAGE, mDownloadDataType.getmDataType())
            }

            override fun onFailure(
                mDownloadDataType: BaseDownloadResource,
                statusCode: Int,
                errorResponse: ByteArray?,
                e: Throwable?
            ) {
                assertFalse(false)
            }

        })
        mProvider?.getRequest(mDataTypeJson)
    }
}