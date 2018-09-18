package com.myproject.cn.base

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.myproject.cn.network.BasePresnter
import com.myproject.cn.utils.SstatusBarUtils.setMeizuStatusBarDarkIcon
import com.myproject.cn.utils.SstatusBarUtils.setMiuiStatusBarDarkMode


abstract class BaseActivity : AppCompatActivity(), IBaseView {

    var mContext: Context? = null
    var controller: BasePresnter? = getPresenters()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
        }
        setContentView(setLayout())
        setMiuiStatusBarDarkMode(this, true)
        setMeizuStatusBarDarkIcon(this, true)
        mContext = this
        initView()
        initDataView()
        onClick()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (controller != null) {
            controller!!.destyHttp()
        }
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }
}