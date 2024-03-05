package com.akter.testlibrary.dialog

import android.content.Context
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.akter.testlibrary.Adfinix
import com.akter.testlibrary.databinding.LayoutAdviewDialogBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class AdviewDialog(context: Context) : DialogFragment() {


    private val binding = LayoutAdviewDialogBinding.inflate(LayoutInflater.from(context), null, false).apply {
        Adfinix.initialize(adView1)
    }
    private val dialog: AlertDialog

    init {
        MaterialAlertDialogBuilder(context,android.R.style.Theme_DeviceDefault_NoActionBar_Fullscreen).apply {
            //background = ContextCompat.getDrawable(context)
            dialog = create().apply {
                setView(binding.root)
                window?.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
            }
        }

    }

    fun show() {
        if (!dialog.isShowing) dialog.show()
    }

    fun dismiss() {
        if (dialog.isShowing) dialog.dismiss()
    }



    interface OnClickListener {
        fun onClick()
    }

}