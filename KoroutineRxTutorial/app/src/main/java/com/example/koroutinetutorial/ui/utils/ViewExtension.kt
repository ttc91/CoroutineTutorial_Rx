package com.example.koroutinetutorial.ui.utils

import android.view.View
import androidx.databinding.BindingAdapter
import com.example.koroutinetutorial.R

class ViewExtension {

    interface OnButtonListener {

        fun onGetDataClick()

        fun onRemoveDataClick()

    }

    companion object {

        @BindingAdapter("android:onButtonClick")
        fun setOnButtonClickListener(view: View, oldPadding: Int, listener: OnButtonListener) {
            view.setOnClickListener(View.OnClickListener { view: View? ->
                if (view!!.id == R.id.btn_get_data) {
                    listener.onGetDataClick()
                } else if (view!!.id == R.id.btn_remove_data) {
                    listener.onRemoveDataClick()
                }
            })
        }

    }
}