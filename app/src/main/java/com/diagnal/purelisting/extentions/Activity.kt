package com.diagnal.purelisting.extentions

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun AppCompatActivity.replace(
    fragment: Fragment, resContainer: Int,
    isBackStack: Boolean,
    backStackName: String?
) {
    with(this.supportFragmentManager.beginTransaction()) {
        if (isBackStack)
            this.addToBackStack(backStackName)
        this.replace(resContainer, fragment, backStackName)
        this.commit()
    }
}