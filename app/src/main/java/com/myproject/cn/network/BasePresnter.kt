package com.myproject.cn.network

import io.reactivex.Observable
import io.reactivex.disposables.Disposable

import io.reactivex.functions.Consumer

abstract class BasePresnter : Consumer<Disposable> {
    var disposableList = ArrayList<Disposable>()

    fun destyHttp(){
        Observable.fromIterable(setDisposableList()).subscribe(this)
    }
    override fun accept(t: Disposable) {
        if (!t.isDisposed) t.dispose()
    }

    abstract fun setDisposableList(): ArrayList<Disposable>
}