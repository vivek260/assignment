package com.assignment.presentation.utilities

import android.view.animation.AnimationUtils.loadAnimation
import android.widget.ImageView
import androidx.annotation.DrawableRes
import coil.Coil
import coil.load
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation


@Suppress("SpellCheckingInspection")
object Imagify {

    fun loadImage(view: ImageView, url: String) {
        view.load(url) {
            crossfade(1000)
        }
    }

    fun loadCircularImage(view: ImageView, url: String) {
        view.load(url) {
            crossfade(1000)
            transformations(CircleCropTransformation())
        }
    }
}