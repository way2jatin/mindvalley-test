package com.jatin.mindvalley.views.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.jatin.asyncmodule.interfaces.DataDownloadCallbacks
import com.jatin.asyncmodule.models.BaseDownloadResource
import com.jatin.asyncmodule.models.DownloadTypeImage
import com.jatin.asyncmodule.utils.DownloadProvider
import com.jatin.mindvalley.R
import com.jatin.mindvalley.constants.AppConstants
import com.jatin.mindvalley.databinding.ActivityFullImageBinding
import kotlinx.android.synthetic.main.activity_full_image.*


class FullImageActivity : AppCompatActivity() {
    private var mProvider: DownloadProvider? = null
    private lateinit var fullImageBinding: ActivityFullImageBinding
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fullImageBinding = DataBindingUtil.setContentView(this, R.layout.activity_full_image)

        mProvider = DownloadProvider.getInstance()

        val i = intent
        var url: String?
        if (i.extras != null) {
            url = i.extras?.getString(AppConstants.FULL_IMAGE_URL)
        } else {
            throw Exception("No Url Provided")
        }
        val mDataTypeImageCancel = url?.let {
            DownloadTypeImage(it, object : DataDownloadCallbacks {
                override fun onStart(mDownloadDataType: BaseDownloadResource) {
                    progress_bar.visibility = View.VISIBLE
                }

                override fun onSuccess(mDownloadDataType: BaseDownloadResource) {
                    full_image_view.setImageBitmap((mDownloadDataType as DownloadTypeImage).imageBitmap)
                    progress_bar.visibility = View.GONE
                }

                override fun onFailure(
                    mDownloadDataType: BaseDownloadResource,
                    statusCode: Int,
                    errorResponse: ByteArray?,
                    e: Throwable?
                ) {
                    full_image_view.setImageResource(R.drawable.ic_menu_gallery)
                    progress_bar.visibility = View.GONE
                }

            })
        }
        mDataTypeImageCancel?.let { mProvider?.getRequest(it) }
    }

}