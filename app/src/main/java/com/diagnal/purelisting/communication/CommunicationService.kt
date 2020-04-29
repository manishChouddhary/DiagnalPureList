package com.diagnal.purelisting.communication

import com.diagnal.purelisting.model.ContentListing
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface CommunicationService {

    @GET("/{page}")
    fun getPageData(@Path("page") page: Int): Single<ContentListing>
}