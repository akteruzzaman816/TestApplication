package com.akter.testlibrary.dialog

import android.content.Context
import android.view.LayoutInflater
import android.widget.RelativeLayout
import androidx.appcompat.app.AlertDialog
import com.akter.testlibrary.Adfinix
import com.akter.testlibrary.R
import com.akter.testlibrary.databinding.LayoutAdviewDialogBinding


class AdviewDialog(private val context: Context,private val slotID:Int){

    private val binding = LayoutAdviewDialogBinding.inflate(LayoutInflater.from(context), null, false)
    private val dialog: AlertDialog

    init {
        AlertDialog.Builder(context, R.style.full_screen_ads).apply {
            dialog = create().apply {
                this.setCancelable(true)
                setView(binding.root)
            }
        }
        setUpAds()
    }

    private fun setUpAds() = with(binding){
        adView.setupSlotID(slotID)
        Adfinix.initialize(adView)

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