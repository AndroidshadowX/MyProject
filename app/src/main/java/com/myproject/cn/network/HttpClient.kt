package com.example.shadow.heartrecreation.network

import com.myproject.cn.network.ApiRetrofit

class HttpClient {

    object ApiServiceHolder {
        val API_SERVICE = ApiRetrofit.instance().initRetrofitClient().create(ApiService::class.java)
    }

}