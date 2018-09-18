package com.myproject.cn.view;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Button;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by Administrator on 2016/9/8.
 */
public class TimeButton extends Button {
    public static Map<String, Long> map;//用来存放倒计时的时间
    private long lenght = 60 * 1000;// 倒计时长度,这里给了默认60秒
    private String textafter = "s";
    private String textbefore = "获取动态密码";
    private final String TIME = "time";
    private final String CTIME = "ctime";
    private OnClickListener mOnclickListener;
    private Timer t;
    private TimerTask tt;
    private long time;
    private Context mContext;
    //    Map<String, Long> map = new HashMap<String, Long>();
    private boolean validationCoded;

    public boolean isValidationCoded() {
        return validationCoded;
    }

    public void setValidationCoded(boolean validationCoded) {
        this.validationCoded = validationCoded;
    }

    public TimeButton(Context context) {
        super(context);
//        setOnClickListener(this);
    }

    public TimeButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
//        setOnClickListener(this);
    }

    @SuppressLint("HandlerLeak")
    Handler han = new Handler() {
        public void handleMessage(android.os.Message msg) {
            TimeButton.this.setText(time / 1000 + textafter);
            time -= 1000;
            if (time < 0) {
                TimeButton.this.setEnabled(true);
                TimeButton.this.setText(textbefore);
                clearTimer();
            }
        }

        ;
    };

    private void initTimer() {
        time = lenght;
        t = new Timer();
        tt = new TimerTask() {

            @Override
            public void run() {
                han.sendEmptyMessage(0x01);
            }
        };
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void clearTimer() {
        validationCoded = false;
//        setBackground(getResources().getDrawable(R.drawable.data_bg));
        if (tt != null) {
            tt.cancel();
            tt = null;
        }
        if (t != null)
            t.cancel();
        t = null;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void onMyClick() {
        validationCoded = true;
//        setBackground(getResources().getDrawable(R.drawable.data_bg));
        initTimer();
        this.setText(time / 1000 + textafter);
        this.setEnabled(false);
        t.schedule(tt, 0, 1000);
        // t.scheduleAtFixedRate(task, delay, period);
    }

    /**
     * 和activity的onDestroy()方法同步
     */
    public void onDestroy() {
        if (map == null)
            map = new HashMap<String, Long>();
        map.put(TIME, time);
        map.put(CTIME, System.currentTimeMillis());
        clearTimer();
        Log.e("yung", "onDestroy");
    }

    /**
     * 和activity的onCreate()方法同步
     */
    public void onCreate(Bundle bundle) {
        Log.e("yung", map + "");
        if (map == null)
            return;
        if (map.size() <= 0)// 这里表示没有上次未完成的计时
            return;
        long time = System.currentTimeMillis() - map.get(CTIME)
                - map.get(TIME);
        map.clear();
        if (time > 0)
            return;
        else {
            initTimer();
            this.time = Math.abs(time);
            t.schedule(tt, 0, 1000);
            this.setText(time + textafter);
            this.setEnabled(false);
        }
    }

    /**
     * 设置计时时候显示的文本
     */
    public TimeButton setTextAfter(String text1) {
        this.textafter = text1;
        return this;
    }

    /**
     * 设置点击之前的文本
     */
    public TimeButton setTextBefore(String text0) {
        this.textbefore = text0;
        this.setText(textbefore);
        return this;
    }

    /**
     * 设置到计时长度
     *
     * @param lenght 时间 默认毫秒
     * @return
     */
    public TimeButton setLenght(long lenght) {
        this.lenght = lenght;
        return this;
    }

    /**
     * 可否点击
     *
     * @param b
     */
    public void setClickable(boolean b) {
        this.setEnabled(b);
    }
}
