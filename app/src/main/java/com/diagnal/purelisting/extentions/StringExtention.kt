package com.diagnal.purelisting.extentions

fun String.removeExt() : String{
    return this.split(".")[0]
}