package com.diagnal.purelisting

import android.app.Application
import com.diagnal.purelisting.dependencyinjection.AppDiComponent
import com.diagnal.purelisting.dependencyinjection.AppDiModule
import com.diagnal.purelisting.dependencyinjection.DaggerAppDiComponent
import com.diagnal.purelisting.utility.AssetLoader
import com.diagnal.purelisting.utility.AssetLoaderImpl

class AppApplication : Application() {

    companion object {
        lateinit var assetLoader: AssetLoader
        lateinit var diComponent : AppDiComponent
    }

    override fun onCreate() {
        super.onCreate()
        assetLoader = AssetLoaderImpl(this.resources.assets)
        diComponent = DaggerAppDiComponent.builder()
            .appDiModule(AppDiModule())
            .build()
    }

}