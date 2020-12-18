package com.example.topgithub.helper

import android.app.Dialog
import android.content.Context
import com.example.topgithub.R

class CustomProgressDialog(
    context: Context?
) : Dialog(context!!) {

    init {
        setContentView(R.layout.layout_loader)
    }

    fun show(message: String?) {
        try {
            if (!isShowing) {
                super.show()
            }
        } catch (e: Exception) {
            super.dismiss()
        }
    }

    override fun hide() {
        try {
            if (isShowing) {
                super.dismiss()
            }
        } catch (e: Exception) {
            super.dismiss()
        }
    }

}