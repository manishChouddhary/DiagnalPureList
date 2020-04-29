package com.diagnal.purelisting.utility

import android.content.res.AssetManager
import com.diagnal.purelisting.model.ContentListing
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader

interface AssetLoader {
    fun getResponsePage(): ContentListing
}

class AssetLoaderImpl(private val assetsManager: AssetManager) : AssetLoader {
    override fun getResponsePage(): ContentListing {
        return Gson().fromJson(BufferedReader(InputStreamReader(assetsManager.open("{\n  \"page\": {\n    \"title\": \"Romantic Comedy\",\n    \"total-content-items\" : \"54\",\n    \"page-num\" : \"1\",\n    \"page-size\" : \"20\",\n    \"content-items\": {\n      \"content\": [\n        {\n          \"name\": \"The Birds\",\n          \"poster-image\": \"poster1.jpg\"\n        },\n        {\n          \"name\": \"Rear Window\",\n          \"poster-image\": \"poster2.jpg\"\n        },\n        {\n          \"name\": \"Family Pot\",\n          \"poster-image\": \"poster3.jpg\"\n        },\n        {\n          \"name\": \"Family Pot\",\n          \"poster-image\": \"poster2.jpg\"\n        },\n        {\n          \"name\": \"Rear Window\",\n          \"poster-image\": \"poster1.jpg\"\n        },\n        {\n          \"name\": \"The Birds\",\n          \"poster-image\": \"poster3.jpg\"\n        },\n        {\n          \"name\": \"Rear Window\",\n          \"poster-image\": \"poster3.jpg\"\n        },\n        {\n          \"name\": \"The Birds\",\n          \"poster-image\": \"poster2.jpg\"\n        },\n        {\n          \"name\": \"Family Pot\",\n          \"poster-image\": \"poster1.jpg\"\n        },\n        {\n          \"name\": \"The Birds\",\n          \"poster-image\": \"poster1.jpg\"\n        },\n                {\n          \"name\": \"The Birds\",\n          \"poster-image\": \"poster1.jpg\"\n        },\n        {\n          \"name\": \"Rear Window\",\n          \"poster-image\": \"poster2.jpg\"\n        },\n        {\n          \"name\": \"Family Pot\",\n          \"poster-image\": \"poster3.jpg\"\n        },\n        {\n          \"name\": \"Family Pot\",\n          \"poster-image\": \"poster2.jpg\"\n        },\n        {\n          \"name\": \"Rear Window\",\n          \"poster-image\": \"poster1.jpg\"\n        },\n        {\n          \"name\": \"The Birds\",\n          \"poster-image\": \"poster3.jpg\"\n        },\n        {\n          \"name\": \"Rear Window\",\n          \"poster-image\": \"poster3.jpg\"\n        },\n        {\n          \"name\": \"The Birds\",\n          \"poster-image\": \"poster2.jpg\"\n        },\n        {\n          \"name\": \"Family Pot\",\n          \"poster-image\": \"poster1.jpg\"\n        },\n        {\n          \"name\": \"The Birds\",\n          \"poster-image\": \"poster1.jpg\"\n        }\n      ]\n    }\n  }\n}"))), ContentListing::class.java)
    }
}