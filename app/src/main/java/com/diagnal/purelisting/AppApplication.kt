package com.diagnal.purelisting

import android.app.Application
import com.diagnal.purelisting.dependencyinjection.AppDiComponent
import com.diagnal.purelisting.dependencyinjection.AppDiModule
import com.diagnal.purelisting.dependencyinjection.DaggerAppDiComponent

class AppApplication : Application() {

    companion object {
        lateinit var diComponent : AppDiComponent
    }

    override fun onCreate() {
        super.onCreate()
        diComponent = DaggerAppDiComponent.builder()
            .appDiModule(AppDiModule())
            .build()
    }

}