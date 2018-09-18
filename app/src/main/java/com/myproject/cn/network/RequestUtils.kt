package com.example.shadow.heartrecreation.network

import okhttp3.MediaType
import okhttp3.RequestBody

object RequestUtils {

    fun getRequestBody(Data: String): RequestBody {
        return RequestBody.create(MediaType.parse("application/json; charset=utf-8"), Data)
    }
}