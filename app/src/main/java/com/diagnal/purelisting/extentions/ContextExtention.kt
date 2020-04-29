package com.diagnal.purelisting.extentions

import android.content.Context
import android.content.res.Configuration

fun Context.isOrientationPortrait(): Boolean =  (this.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT)

fun Context.getDimen(dimen: Int): Int  = this.resources.getDimensionPixelSize(dimen)
