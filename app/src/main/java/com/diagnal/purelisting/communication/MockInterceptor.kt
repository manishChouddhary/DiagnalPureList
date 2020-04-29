package com.diagnal.purelisting.communication

import okhttp3.Interceptor
import okhttp3.Response

class MockInterceptor() : Interceptor {
    lateinit var request: MockRequest
    enum class MockRequest{
        PAGE_1,
        PAGE_2,
        PAGE_3,
    }

    private fun getPageFileName(page : MockRequest): String{
        return when(page){
            MockRequest.PAGE_1 -> "CONTENTLISTINGPAGE-PAGE1.json"
            MockRequest.PAGE_2 -> "CONTENTLISTINGPAGE-PAGE2.json"
            MockRequest.PAGE_3 -> "CONTENTLISTINGPAGE-PAGE3.json"
        }
    }
    override fun intercept(chain: Interceptor.Chain): Response {

        val requestBuilder = chain.request().newBuilder()
        return when(chain.request().url.toUri().path){
            PAGE_1_URI ->{
                MockDataProvider(getPageFileName(MockRequest.PAGE_1)).getResponse(requestBuilder.build())
            }
            PAGE_2_URI ->{
                MockDataProvider(getPageFileName(MockRequest.PAGE_2)).getResponse(requestBuilder.build())
            }
            PAGE_3_URI -> {
                MockDataProvider(getPageFileName(MockRequest.PAGE_3)).getResponse(requestBuilder.build())
            }
            else ->{ throw Exception() }
        }
    }

    companion object {
        const val PAGE_1_URI = "/1"
        const val PAGE_2_URI = "/2"
        const val PAGE_3_URI = "/3"
    }
}