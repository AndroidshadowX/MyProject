package com.myproject.cn.mvp.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.muzhi.camerasdk.CameraUtils
import com.muzhi.camerasdk.LuBanSDK
import com.muzhi.camerasdk.model.CameraSdkParameterInfo
import com.myproject.cn.R
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

class MainActivity : AppCompatActivity(), CameraUtils.CameraImageCallBack, LuBanSDK.LuBanImageFace {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViewCamera()
        show_camera.setOnClickListener {
            cameraUtils!!.openCamera()
        }

    }

    var cameraUtils: CameraUtils? = null
    var cameraInfo: CameraSdkParameterInfo? = null

    private fun initViewCamera() {
        cameraInfo = CameraSdkParameterInfo()
        //设置单选模式
        cameraInfo!!.setSingleMode(true)
        //打开图片剪裁
        cameraInfo!!.setCutoutImage(true)
        cameraInfo!!.isShowCamera = false
        cameraInfo!!.isOpenDialog = true
        cameraUtils = CameraUtils(this, cameraInfo, this)
    }

    override fun returnCameraImageList(list: ArrayList<String>?) {

    }

    override fun returnLuBanImageList(file: ArrayList<String>?) {
    }

    override fun onStartImages() {
    }

    override fun onError(error: String?) {
    }
}
