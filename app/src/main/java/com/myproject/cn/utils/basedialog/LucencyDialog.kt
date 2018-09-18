package com.example.shadow.heartrecreation.utils.basedialog

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Build
import android.view.Gravity
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.view.WindowManager.LayoutParams
import com.myproject.cn.R

class LucencyDialog(mContext: Context) : Dialog(mContext) {

    protected var mLayoutParams: LayoutParams? = null

    constructor(mContext: Context, themeResId: Int) : this(mContext) {
        initView(mContext)
    }

    constructor(mContext: Context, themeResId: Int, cancelListener: DialogInterface.OnCancelListener) : this(mContext, themeResId) {
        initView(mContext)
    }

    private fun initView(mContext: Context) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setBackgroundDrawableResource(R.drawable.transparent_bg)
        var windows = window
        mLayoutParams = windows.attributes
        mLayoutParams!!.alpha = 1f
        windows.attributes = mLayoutParams
        if (mLayoutParams != null) {
            mLayoutParams!!.height = ViewGroup.LayoutParams.MATCH_PARENT
            mLayoutParams!!.gravity = Gravity.CENTER
        }
    }

    constructor(mContext: Context, alpha: Float, gravity: Int) : this(mContext) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setBackgroundDrawableResource(R.drawable.transparent_bg)
        var windows = window
        mLayoutParams = windows.attributes
        mLayoutParams!!.alpha = 1f
        windows.attributes = mLayoutParams
        if (mLayoutParams != null) {
            mLayoutParams!!.height = ViewGroup.LayoutParams.MATCH_PARENT
            mLayoutParams!!.gravity = gravity
        }
    }

    fun NOTools() {
        if (Build.VERSION.SDK_INT < 19) {
            return
        }
        window.setFlags(LayoutParams.FLAG_FULLSCREEN, LayoutParams.FLAG_FULLSCREEN)
    }

    fun setMatchScreen() {
        var window = window
        window.decorView.setPadding(0, 0, 0, 0)
        var lp = window.attributes
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.MATCH_PARENT
        window.attributes = lp
    }

    fun setScreenWidth() {
        var window = window
        window.decorView.setPadding(0, 0, 0, 0)
        var lp = window.attributes
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
        window.attributes = lp
    }

    fun setScreenHeight() {
        var window = window
        window.decorView.setPadding(0, 0, 0, 0)
        var lp = window.attributes
        lp.height = WindowManager.LayoutParams.MATCH_PARENT
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT
        window.attributes = lp
    }

}