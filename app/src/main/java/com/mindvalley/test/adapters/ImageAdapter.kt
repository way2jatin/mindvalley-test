package com.mindvalley.test.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.BaseAdapter
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.jatin.asyncmodule.interfaces.DataDownloadCallbacks
import com.jatin.asyncmodule.models.BaseDownloadResource
import com.jatin.asyncmodule.models.DownloadTypeImage
import com.jatin.asyncmodule.utils.DownloadProvider
import com.jatin.mindvalley.R
import kotlinx.android.synthetic.main.list_item.view.*
import java.util.ArrayList



class ImageAdapter// Constructor
    (private val mContext: Context, imgUrlArray: ArrayList<String>) : RecyclerView.Adapter<ImageAdapter.PhotoHolder>()  {

    private val mProvider: DownloadProvider?

    // Keep all Images in array
    private var imgUrlArray = ArrayList<String>()

    var onItemClick: ((Int) -> Unit)? = null

    init {
        this.imgUrlArray = imgUrlArray
        mProvider = DownloadProvider.getInstance()
    }

    fun setImgUrlArray(imgUrlArray: ArrayList<String>) {
        this.imgUrlArray = imgUrlArray
    }

//    override fun getCount(): Int {
//        return imgUrlArray.size
//    }
//
//    override fun getItem(position: Int): Any {
//        return imgUrlArray[position]
//    }

//    override fun getItemId(position: Int): Long {
//        return 0
//    }

//    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
//
//        val imageView = ImageView(mContext)
//
//        val mDataTypeImageCancel = DownloadTypeImage(imgUrlArray[position], object :
//            DataDownloadCallbacks {
//            override fun onStart(mDownloadDataType: BaseDownloadResource) {
//
//            }
//            override fun onSuccess(mDownloadDataType: BaseDownloadResource) {
//                imageView.setImageBitmap((mDownloadDataType as DownloadTypeImage).imageBitmap)
//                imageView.scaleType = ImageView.ScaleType.CENTER_CROP
//                imageView.layoutParams = AbsListView.LayoutParams(250, 250)
//            }
//
//            override fun onFailure(
//                mDownloadDataType: BaseDownloadResource,
//                statusCode: Int,
//                errorResponse: ByteArray?,
//                e: Throwable?
//            ) {
//                imageView.setImageResource(R.drawable.ic_launcher_background)
//                imageView.scaleType = ImageView.ScaleType.CENTER_CROP
//                imageView.layoutParams = AbsListView.LayoutParams(250, 250)
//            }
//
//        })
//        mProvider?.getRequest(mDataTypeImageCancel)
//        return imageView
//    }

    inner class PhotoHolder (view: View) : RecyclerView.ViewHolder(view) {
        val ivImage = view.ivImage

        init {
            ivImage.setOnClickListener {
                onItemClick?.invoke(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoHolder {
        return PhotoHolder(LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return imgUrlArray.size
    }

    override fun onBindViewHolder(holder: PhotoHolder, position: Int) {
        val mDataTypeImageCancel = DownloadTypeImage(imgUrlArray[position], object :
            DataDownloadCallbacks {
            override fun onStart(mDownloadDataType: BaseDownloadResource) {

            }
            override fun onSuccess(mDownloadDataType: BaseDownloadResource) {
                holder.ivImage.setImageBitmap((mDownloadDataType as DownloadTypeImage).imageBitmap)
            }

            override fun onFailure(
                mDownloadDataType: BaseDownloadResource,
                statusCode: Int,
                errorResponse: ByteArray?,
                e: Throwable?
            ) {
                holder.ivImage.setImageResource(R.drawable.ic_launcher_background)
            }

        })
        mProvider?.getRequest(mDataTypeImageCancel)
    }

}