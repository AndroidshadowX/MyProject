package com.myproject.cn.base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.multidex.MultiDex
import android.support.multidex.MultiDexApplication
import com.blankj.utilcode.util.Utils
import com.bumptech.glide.Glide
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import com.bumptech.glide.load.model.GlideUrl
import com.myproject.myproject.utils.MyActivityManager.setCurrentActivity
import okhttp3.OkHttpClient
import java.io.InputStream

class MyApp : MultiDexApplication() {

    var app: Context? = null
    override fun onCreate() {
        super.onCreate()
        app = applicationContext
        setUtils(applicationContext)
        this.registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            override fun onActivityPaused(activity: Activity?) {
                setCurrentActivity(activity!!)
            }

            override fun onActivityResumed(activity: Activity?) {
                setCurrentActivity(activity!!)
            }

            override fun onActivityStarted(activity: Activity?) {
            }

            override fun onActivityDestroyed(activity: Activity?) {
            }

            override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
            }

            override fun onActivityStopped(activity: Activity?) {
            }

            override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
            }
        })
    }

    private fun setUtils(applicationContext: Context?) {
        Utils.init(this)//常用工具箱
        MultiDex.install(applicationContext)
        Glide.get(this).register(GlideUrl::class.java, InputStream::class.java, OkHttpUrlLoader.Factory(OkHttpClient()))//Glide
    }
}