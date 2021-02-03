package com.example.customalertbox

import android.view.View
import android.view.animation.AnimationUtils
import android.view.animation.Interpolator
import androidx.interpolator.view.animation.FastOutSlowInInterpolator

fun View.animateView(animTime: Long, startOffset: Long, anim: Int) {
    val anim = AnimationUtils.loadAnimation(context, anim).apply {
        duration = animTime
        interpolator = FastOutSlowInInterpolator()
        this.startOffset = startOffset
    }
    startAnimation(anim)
}