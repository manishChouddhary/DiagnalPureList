package com.diagnal.purelisting.dependencyinjection

import com.diagnal.purelisting.communication.CommunicationService
import com.diagnal.purelisting.communication.NetworkClient
import com.diagnal.purelisting.content.ContentListPresenter
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class AppDiModule {
    @Provides
    fun getMockRetrofitClient(): Retrofit{
        return NetworkClient().getRetrofitClient()
    }

    @Provides
    fun getCommunicationService(retrofit: Retrofit): CommunicationService{
        return retrofit.create(CommunicationService::class.java)
    }

    @Provides
    fun provideContentPresenter(communicationService: CommunicationService): ContentListPresenter {
        return ContentListPresenter(communicationService)
    }
}
