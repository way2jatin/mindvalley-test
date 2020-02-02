package com.jatin.asyncmodule.models


import android.os.Build
import com.google.gson.GsonBuilder
import com.jatin.asyncmodule.interfaces.DataDownloadCallbacks
import java.lang.reflect.Type
import java.nio.charset.StandardCharsets


class DownloadTypeJson(url: String, imDownloadDataType: DataDownloadCallbacks) :
    BaseDownloadResource(url, DataTypeEnum.JSON, imDownloadDataType) {

    private val jsonText: String?
        get() {
            try {
                return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    data?.let { String(it, StandardCharsets.UTF_8) }
                } else {
                    data?.let { String(it, Charsets.UTF_8) }

                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return ""
        }

    override fun getCopyOfMe(imDownloadDataType: DataDownloadCallbacks): BaseDownloadResource {
        return DownloadTypeJson(url, imDownloadDataType)
    }

    fun getJson(type: Type): Any? {
        val gsonBuilder = GsonBuilder()
        val gson = gsonBuilder.create()
        return gson.fromJson<Any>(jsonText, type)
    }
}
