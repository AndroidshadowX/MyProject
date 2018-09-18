package com.myproject.cn.base

import com.myproject.cn.network.BasePresnter


interface IBaseView {
    fun setLayout(): Int
    fun initView()
    fun initDataView()
    fun onClick()
    fun getPresenters(): BasePresnter?
}