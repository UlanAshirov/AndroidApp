package com.uli.androidapp.ui.utils

import android.widget.ImageView
import coil.load
import com.uli.androidapp.R

fun ImageView.loadImage(imageView: ImageView, url: String?) {
    url?.let {
        imageView.load(url) {
            crossfade(true)
                .error(R.drawable.ic_baseline_error_24)
                .placeholder(R.drawable.animation_loading)
        }
    }
}