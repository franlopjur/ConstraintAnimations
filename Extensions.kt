package com.betabeers.constraintanimations

import android.support.annotation.IdRes
import android.view.View

fun <T : View> View.bind(@IdRes res : Int) : Lazy<T> {
    return lazy { findViewById<T>(res) }
}