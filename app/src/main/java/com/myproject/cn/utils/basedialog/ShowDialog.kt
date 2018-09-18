package com.example.shadow.heartrecreation.utils.basedialog

import android.content.Context
import android.content.DialogInterface

class ShowDialog {
    val DIALOGLEFT = -1
    val DIALOGMIDDLE = 0
    val DIALOGRIGHT = 1

    fun ShowMyDialog(mContext: Context, title: String, message: String, leftText: String, listener: DialogInterface.OnClickListener) {
        CustomDialog.Builder().Builder(mContext)
                .setTitle(title)
                .setMessage(message)
                .setleftButtonText(leftText, listener)
                .create()
                .show()
    }

    fun ShowMyDialog(mContext: Context, title: String, message: String, leftText: String, rightText: String, listener: DialogInterface.OnClickListener) {
        CustomDialog.Builder().Builder(mContext)
                .setTitle(title)
                .setMessage(message)
                .setleftButtonText(leftText, listener)
                .setrightButtonText(rightText, listener)
                .create()
                .show()
    }

    fun ShowMyDialog(mContext: Context, title: String, message: String, leftText: String, middleText: String, rightText: String, listener: DialogInterface.OnClickListener) {
        CustomDialog.Builder().Builder(mContext)
                .setTitle(title)
                .setMessage(message)
                .setleftButtonText(leftText, listener)
                .setMiddleListener(middleText, listener)
                .setrightButtonText(rightText, listener)
                .create()
                .show()
    }

    fun ShowMyDialog(mContext: Context, title: String, message: String, leftText: String, onTach: Boolean, listener: DialogInterface.OnClickListener) {
        CustomDialog.Builder().Builder(mContext)
                .setTitle(title)
                .setMessage(message)
                .setleftButtonText(leftText, listener)
                .setTouchOutside(onTach)
                .create()
                .show()
    }

    fun ShowMyDialog(mContext: Context, title: String, message: String, leftText: String, rightText: String, onTach: Boolean, listener: DialogInterface.OnClickListener) {
        CustomDialog.Builder().Builder(mContext)
                .setTitle(title)
                .setMessage(message)
                .setleftButtonText(leftText, listener)
                .setrightButtonText(rightText, listener)
                .setTouchOutside(onTach)
                .create()
                .show()
    }

    fun ShowMyDialog(mContext: Context, title: String, message: String, leftText: String, middleText: String, rightText: String, onTach: Boolean, listener: DialogInterface.OnClickListener) {
        CustomDialog.Builder().Builder(mContext)
                .setTitle(title)
                .setMessage(message)
                .setleftButtonText(leftText, listener)
                .setMiddleListener(middleText, listener)
                .setrightButtonText(rightText, listener)
                .setTouchOutside(onTach)
                .create()
                .show()
    }
}