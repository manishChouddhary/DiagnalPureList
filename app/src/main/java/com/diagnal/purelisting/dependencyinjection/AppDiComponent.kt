package com.diagnal.purelisting.dependencyinjection

import com.diagnal.purelisting.content.ContentListFragment
import dagger.Component

@Component(modules = [AppDiModule::class])
interface AppDiComponent {
    fun inject(contentListFragment: ContentListFragment)
}