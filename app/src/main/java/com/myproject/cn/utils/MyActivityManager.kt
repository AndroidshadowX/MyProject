package com.myproject.myproject.utils

import android.app.Activity
import java.lang.ref.WeakReference

/**
 * Created by shadow on 2018/3/7.
 */
object MyActivityManager {
    val sInstance = MyActivityManager()

    operator fun invoke(): Any {
        return sInstance
    }

    private var sCurrentActivityWeakRef: WeakReference<Activity>? = null


    fun getCurrentActivity(): Activity? {
        var currentActivity: Activity? = null
        if (sCurrentActivityWeakRef != null) {
            currentActivity = sCurrentActivityWeakRef!!.get()
        }
        return currentActivity
    }

    fun setCurrentActivity(activity: Activity) {
        sCurrentActivityWeakRef = WeakReference(activity)
    }
}