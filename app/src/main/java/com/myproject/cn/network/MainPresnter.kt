package com.myproject.cn.network

import com.blankj.utilcode.util.NetworkUtils
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody

abstract class MainPresnter<T> : BasePresnter(), Observer<ResponseBody> {

    fun startHttpRequest(observable: Observable<ResponseBody>) {
        observable?.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(this)
    }

    override fun onComplete() {

    }


    override fun onSubscribe(d: Disposable) {
        getDisposable(d)
    }

    override fun onError(e: Throwable) {
        if (NetworkUtils.isConnected()) {
            setRequestError("网络异常")
        } else {
            //服务器异常
            setRequestError("服务器异常")
        }
    }

    override fun onNext(t: ResponseBody) {
        setRequestOnNext(t.string())
    }

    abstract fun setRequestOnNext(t: String)

    abstract fun setRequestError(s: String)

    abstract fun getDisposable(d: Disposable)
}