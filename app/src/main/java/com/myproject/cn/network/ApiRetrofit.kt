package com.myproject.cn.network

import com.blankj.utilcode.util.LogUtils
import com.example.shadow.heartrecreation.network.HTTPURL.MAIN_URL
import com.myproject.cn.network.ApiRetrofit.SingletonHolder.INSTANCE
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiRetrofit {
    val httpTime = 15
    var client: OkHttpClient.Builder? = null
    var retrofit: Retrofit? = null

    companion object {
        fun instance(): ApiRetrofit = INSTANCE
    }

    object SingletonHolder {
        var INSTANCE = ApiRetrofit()
    }

    fun initRetrofitClient(): Retrofit {
        return retrofit()
    }

    fun retrofit(): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder().baseUrl(MAIN_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(defaultClint().build())
                    .build()
        }
        return retrofit!!
    }

    fun defaultClint(): OkHttpClient.Builder {
        if (client == null) {
            client = OkHttpClient.Builder()
        }
        client!!.connectTimeout(httpTime.toLong(), TimeUnit.SECONDS)
        val httpsLog = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message ->
            LogUtils.a("HTTPS====>${message}")
        }).setLevel(HttpLoggingInterceptor.Level.BODY)
        client!!.addInterceptor(httpsLog)
        client!!.addInterceptor(Interceptor { chain: Interceptor.Chain? ->
            var origin = chain!!.request()
            var request = origin!!.newBuilder()
                    .addHeader("Content-Type", "application/x-www-form-urlencoded;chatset=UTF-8")
                    .method(origin!!.method(), origin!!.body())
                    .build()
            return@Interceptor chain!!.proceed(request)
        })
        return client!!
    }
}