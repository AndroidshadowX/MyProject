package com.example.shadow.heartrecreation.network

import io.reactivex.Observable
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("sms/send/code")
    fun getSendCode(@Body body: RequestBody): Observable<ResponseBody>


}