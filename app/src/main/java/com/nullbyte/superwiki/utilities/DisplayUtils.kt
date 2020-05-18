package com.nullbyte.superwiki.utilities

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable
import com.nullbyte.superwiki.R

object DisplayUtils {
    fun createLoaderDialog(context: Context): AlertDialog {
        val builder =
            AlertDialog.Builder(context)
        val view =
            LayoutInflater.from(context).inflate(R.layout.loader_layout, null)
        builder.setView(view)
        val dialog = builder.create()
        (dialog.window)?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setCanceledOnTouchOutside(false)
        val mLottieAnimationView: LottieAnimationView = view.findViewById(R.id.lottie_loader)
        mLottieAnimationView.setAnimation("batman.json")
        mLottieAnimationView.playAnimation()
        mLottieAnimationView.repeatCount = LottieDrawable.INFINITE
        return dialog
    }
}