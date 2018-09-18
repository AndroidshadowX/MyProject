package com.muzhi.camerasdk;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Toast;

import com.muzhi.camerasdk.activity.CutActivityCamera;
import com.muzhi.camerasdk.activity.PhotoPickActivityCamera;
import com.muzhi.camerasdk.model.CameraSdkParameterInfo;
import com.muzhi.camerasdk.model.CutCameraResult;
import com.muzhi.camerasdk.utils.FileUtils;
import com.tbruyelle.rxpermissions2.RxPermissions;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.File;
import java.util.ArrayList;

import io.reactivex.functions.Consumer;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static com.muzhi.camerasdk.model.CameraSdkParameterInfo.EXTRA_PARAMETER;
import static com.muzhi.camerasdk.model.CameraSdkParameterInfo.TAKE_PICTURE_PREVIEW;
import static com.muzhi.camerasdk.model.CameraSdkParameterInfo.TAKE_PICTURE_SINGLE_CAMERA;


public class CameraUtils implements SelectCameraDialog.SelectCameraDialogFace {

    private File mTmpFile;
    private Activity activity;
    private SelectCameraDialog selectCameraDialog;
    private CameraSdkParameterInfo cameraSdkParameterInfo;
    private CameraImageCallBack cameraImageCallBack;


    public CameraUtils(Activity activity, CameraSdkParameterInfo cameraSdkParameterInfo, CameraImageCallBack cameraImageCallBack) {
        this.activity = activity;
        this.cameraSdkParameterInfo = cameraSdkParameterInfo;
        this.cameraImageCallBack = cameraImageCallBack;
        this.selectCameraDialog = new SelectCameraDialog(activity);
        this.selectCameraDialog.setDialogFace(this);
        EventBus.getDefault().register(this);
    }

    public void openCamera() {
        RxPermissions rxPermissions = new RxPermissions(activity);
        rxPermissions.request(WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE, CAMERA).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                if (aBoolean) openCameraSdk();
                else Toast.makeText(activity, "请打开相机，内存读取权限", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void openCameraSdk() {
        if (cameraSdkParameterInfo.isSingleMode()
//                && cameraSdkParameterInfo.isCutoutImage()
                && cameraSdkParameterInfo.isOpenDialog()) {
            selectCameraDialog.showDialog();
        } else {
            openPhotoPick();
        }
    }

    @Override
    public void cameraBtn() {
        showCameraAction();
    }

    @Override
    public void photoBtn() {
        openPhotoPick();
    }

    //打开相册
    private void openPhotoPick() {
        Bundle b = new Bundle();
        b.putSerializable(EXTRA_PARAMETER, cameraSdkParameterInfo);
        Intent intent = new Intent(activity, PhotoPickActivityCamera.class);
        intent.putExtras(b);
        activity.startActivityForResult(intent, CameraSdkParameterInfo.TAKE_PICTURE_FROM_GALLERY);
    }


    //系统拍照(单选、裁剪调用)
    private void showCameraAction() {
        if (cameraSdkParameterInfo.isSingleMode() && cameraSdkParameterInfo.isCutoutImage()) {
            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (cameraIntent.resolveActivity(activity.getPackageManager()) != null) {
                mTmpFile = FileUtils.createTmpFile(activity);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mTmpFile));
                activity.startActivityForResult(cameraIntent, TAKE_PICTURE_SINGLE_CAMERA);
            } else {
                Toast.makeText(activity, R.string.camerasdk_msg_no_camera, Toast.LENGTH_SHORT).show();
            }
        }
    }


    public void onActivityCameraResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case CameraSdkParameterInfo.TAKE_PICTURE_FROM_GALLERY:
                setImageListData(data);
                break;
            case TAKE_PICTURE_PREVIEW:
                setImageDeResult(data);
                break;
            case TAKE_PICTURE_SINGLE_CAMERA:
                setSingleModeResult();
                break;
        }
    }

    private void setImageDeResult(Intent data) {
        if (data == null) {
            return;
        }
        int position = data.getIntExtra("position", -1);
        cameraSdkParameterInfo.getImageList().remove(position);
    }


    private void setSingleModeResult() {
        if (cameraSdkParameterInfo.isSingleMode()) {
            cameraSdkParameterInfo.getImageList().clear();
            cameraSdkParameterInfo.getImageList().add(mTmpFile.getPath());
            if (fileIsExists(mTmpFile.getPath())) {
                stateCutActivity();
            }
        }
    }

    public boolean fileIsExists(String strFile) {
        try {
            File f = new File(strFile);
            if (!f.exists()) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    private void stateCutActivity() {
        Bundle b = new Bundle();
        b.putSerializable(EXTRA_PARAMETER, cameraSdkParameterInfo);
        Intent intent = new Intent();
        intent.putExtras(b);
        intent = new Intent(activity, CutActivityCamera.class);
        intent.putExtras(b);
        activity.startActivity(intent);
    }

    //单选模式剪裁成功回调
    @Subscribe
    public void getCutCameraResult(CutCameraResult cutCameraResult) {
        cameraSdkParameterInfo.getImageList().clear();
        cameraSdkParameterInfo.getImageList().add(cutCameraResult.getPath());
        cameraImageCallBack.returnCameraImageList(cameraSdkParameterInfo.getImageList());
    }


    private ArrayList<String> getImageList(Bundle bundle) {
        if (bundle != null) {
            cameraSdkParameterInfo = (CameraSdkParameterInfo) bundle.getSerializable(EXTRA_PARAMETER);
            ArrayList<String> list = cameraSdkParameterInfo.getImageList();
            return list;
        } else {
            return null;
        }
    }


    //删除照片调用
    public void deleteImage(int index) {
        cameraSdkParameterInfo.getImageList().remove(index);
    }

    private void setImageListData(Intent data) {
        if (data == null) {
            return;
        }
        ArrayList<String> list = getImageList(data.getExtras());
        cameraImageCallBack.returnCameraImageList(list);
    }


    public interface CameraImageCallBack {

        void returnCameraImageList(ArrayList<String> list);

    }
}
