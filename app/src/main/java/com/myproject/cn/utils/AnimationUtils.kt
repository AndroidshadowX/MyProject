package com.myproject.cn.utils

import android.view.View
import android.view.animation.Animation
import android.view.animation.TranslateAnimation

object AnimationUtils {
    //从上到下显示
    fun ShowAction(view: View) {
        var mShowAction = TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, -1.0f, Animation.RELATIVE_TO_SELF, 0.0f)
        mShowAction.setDuration(500)
        view.startAnimation(mShowAction)
        view.visibility = View.VISIBLE
    }


    //从下到上隐藏
    fun HiddenAction(view: View) {
        var mHiddenAction = TranslateAnimation(Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, -1.0f)
        mHiddenAction.setDuration(500)
        view.startAnimation(mHiddenAction)
        view.visibility = View.GONE
    }

    fun ShowLiftAction(view: View) {
        var mShowAction = TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 1.0f,//X开始
                Animation.RELATIVE_TO_SELF, 0.0f,//X结束
                Animation.RELATIVE_TO_SELF, 0.0f,//Y开始
                Animation.RELATIVE_TO_SELF, 0.0f)//Y结束
        mShowAction.duration = 500
        view.startAnimation(mShowAction)
        view.visibility = View.VISIBLE
    }
}