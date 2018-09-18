package com.example.shadow.heartrecreation.utils.basedialog

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.myproject.cn.R

class CustomDialog(mContext: Context) : Dialog(mContext) {

    constructor(mContext: Context, theme: Int) : this(mContext)

    class Builder {
        private var mContext: Context? = null
        private var title: String? = null
        private var message: String? = null

        private var leftButtonText: String? = null
        private var rightButtonText: String? = null
        private var middleButtonText: String? = null
        private var TouchOutside = false

        private var leftButtonOnClickListener: DialogInterface.OnClickListener? = null
        private var rightButtonOnClickListener: DialogInterface.OnClickListener? = null
        private var middleButtonOnClickListener: DialogInterface.OnClickListener? = null

        fun setTouchOutside(TouchOutside: Boolean): Builder {
            this.TouchOutside = TouchOutside
            return this
        }

        fun Builder(mContext: Context): Builder {
            this.mContext = mContext
            return this
        }

        fun setMessage(message: String): Builder {
            this.message = message
            return this
        }

        fun setTitle(title: String): Builder {
            this.title = title
            return this
        }

        fun setleftButtonText(leftButtonText: String, leftButtonOnClickListener: DialogInterface.OnClickListener): Builder {
            this.leftButtonText = leftButtonText
            this.leftButtonOnClickListener = leftButtonOnClickListener
            return this
        }

        fun setMiddleListener(middleButtonText: String, middleButtonOnClickListener: DialogInterface.OnClickListener): Builder {
            this.middleButtonText = middleButtonText
            this.middleButtonOnClickListener = middleButtonOnClickListener
            return this
        }

        fun setrightButtonText(rightButtonText: String, rightButtonOnClickListener: DialogInterface.OnClickListener): Builder {
            this.rightButtonText = rightButtonText
            this.rightButtonOnClickListener = rightButtonOnClickListener
            return this
        }

        fun create(): CustomDialog {
            val inflater = mContext!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var dialog = CustomDialog(mContext!!, R.style.my_dialog)
            var layout = inflater.inflate(R.layout.dialog_layout, null)
            dialog.addContentView(layout, ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT))
//            dialog.setView(layout, 0, 0, 0, 0)
            dialog.setCanceledOnTouchOutside(false)
            (layout.findViewById(R.id.dialog_title) as TextView).setText(title)

            if (message != null) {
                (layout.findViewById(R.id.dialog_message) as TextView).setText(message)
            }

            if (leftButtonText != null) {
                (layout.findViewById(R.id.dialog_left_btn) as TextView).setText(leftButtonText)
                if (leftButtonOnClickListener != null) {
                    (layout.findViewById(R.id.dialog_left_btn) as TextView).setOnClickListener {
                        leftButtonOnClickListener!!.onClick(dialog, ShowDialog().DIALOGLEFT)
                    }
                }
            }

            if (middleButtonText != null) {
                (layout.findViewById(R.id.dialog_middle_btn) as TextView).setText(middleButtonText)
                if (middleButtonOnClickListener != null) {
                    (layout.findViewById(R.id.dialog_middle_btn) as TextView).setOnClickListener {
                        middleButtonOnClickListener!!.onClick(dialog, ShowDialog().DIALOGMIDDLE)
                    }
                }
            } else {
                (layout.findViewById(R.id.dialog_middle_btn) as TextView).visibility = View.GONE
            }

            if (rightButtonText != null) {
                (layout.findViewById(R.id.dialog_right_btn) as TextView).setText(rightButtonText)
                if (rightButtonOnClickListener != null) {
                    (layout.findViewById(R.id.dialog_right_btn) as TextView).setOnClickListener {
                        rightButtonOnClickListener!!.onClick(dialog, ShowDialog().DIALOGRIGHT)
                    }
                }
            } else {
                (layout.findViewById(R.id.dialog_right_btn) as TextView).visibility = View.GONE
            }

            if (leftButtonText != null && rightButtonText == null && middleButtonText == null) {
                (layout.findViewById(R.id.dialog_left) as View).visibility = View.GONE
                (layout.findViewById(R.id.dialog_right) as View).visibility = View.GONE
            } else if (leftButtonText != null && rightButtonText != null && middleButtonText == null) {
                (layout.findViewById(R.id.dialog_left) as View).visibility = View.VISIBLE
                (layout.findViewById(R.id.dialog_right) as View).visibility = View.GONE
            } else if (leftButtonText != null && rightButtonText != null && middleButtonText != null) {
                (layout.findViewById(R.id.dialog_left) as View).visibility = View.VISIBLE
                (layout.findViewById(R.id.dialog_right) as View).visibility = View.VISIBLE
            } else {
                (layout.findViewById(R.id.dialog_left) as View).visibility = View.GONE
                (layout.findViewById(R.id.dialog_right) as View).visibility = View.GONE
            }

//            dialog.setContentView(layout)
            return dialog
        }

    }
}