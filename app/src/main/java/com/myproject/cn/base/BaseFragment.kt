package com.myproject.cn.base

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.myproject.cn.network.BasePresnter

abstract class BaseFragment : Fragment(), IBaseView {
    var mContext: Context? = null

    var controller: BasePresnter? = getPresenters()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = activity
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(setLayout(), container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
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
}