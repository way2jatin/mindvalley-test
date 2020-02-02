package com.jatin.mindvalley.interfaces

import java.util.ArrayList
interface JsonDataCallBack {
    fun onSuccess(responseData:ArrayList<*>)
    fun onFailure(throwable: Throwable)
}