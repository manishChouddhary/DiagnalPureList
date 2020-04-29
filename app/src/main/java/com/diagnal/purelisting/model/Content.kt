package com.diagnal.purelisting.model

import com.google.gson.annotations.SerializedName

data class ContentListing(val page: Page)
data class Page(
    val title: String,
    @SerializedName(value = "total-content-items") val totalContentItems: String,
    @SerializedName(value = "page-num") val pageNum: Int,
    @SerializedName(value = "page-size") val pageSize : Int,
    @SerializedName(value = "content-items")val contentItems : ContentItems
)
data class ContentItems(val content : List<Content>)
data class Content(
    val name: String,
    @SerializedName(value = "poster-image") val posterImage: String
)