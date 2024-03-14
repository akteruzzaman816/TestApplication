package com.akter.testlibrary.dialog

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.widget.RelativeLayout
import androidx.appcompat.app.AlertDialog
import com.akter.testlibrary.Adfinix
import com.akter.testlibrary.databinding.LayoutAdviewDialogBinding


class AdviewDialog(private val context: Context,private val slotID:Int){

    private val binding = LayoutAdviewDialogBinding.inflate(LayoutInflater.from(context), null, false)
    private val dialog: AlertDialog

    init {
        AlertDialog.Builder(context,android.R.style.Theme_Black_NoTitleBar_Fullscreen).apply {
            dialog = create().apply {
                this.setCancelable(true)
                setView(binding.root)
            }
        }
        setUpAds()
    }

    @SuppressLint("InternalInsetResource", "DiscouragedApi")
    private fun setUpAds() = with(binding){
        adView.setupSlotID(slotID)
        Adfinix.showAds(adView)

        // setup (height / width) of view
        val width: Int = context.resources.displayMetrics.widthPixels
        val height: Int = context.resources.displayMetrics.heightPixels

        val params = RelativeLayout.LayoutParams(width, height)
        binding.adView.layoutParams = params
    }

    fun show() {
        if (!dialog.isShowing) dialog.show()
    }

    private fun dismiss() {
        if (dialog.isShowing) dialog.dismiss()
    }

}