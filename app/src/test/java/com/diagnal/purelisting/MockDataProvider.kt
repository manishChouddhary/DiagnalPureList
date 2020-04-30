package com.diagnal.purelisting

import com.diagnal.purelisting.model.Content
import com.diagnal.purelisting.model.ContentListing
import com.google.gson.Gson

object MockDataProvider {
    const val response ="""{
      "page": {
        "title": "Romantic Comedy",
        "total-content-items" : "54",
        "page-num" : "1",
        "page-size" : "20",
        "content-items": {
          "content": [
            {
              "name": "The Birds",
              "poster-image": "poster1.jpg"
            }
            ]
          }
        }
    }"""

    const val title = "Romantic Comedy"

    val list = mutableListOf<Content>(Content(name = "The Birds", posterImage = "poster1.jpg"))

    fun getMockContentList(): ContentListing = Gson().fromJson(response, ContentListing::class.java)
}