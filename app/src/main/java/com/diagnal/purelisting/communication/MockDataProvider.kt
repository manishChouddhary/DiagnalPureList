package com.diagnal.purelisting.communication

import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

class MockDataProvider(private val fileName: String) {
    fun getResponse(request: Request): Response {
        val json = readResourceOnClassPath()
        return Response.Builder()
            .code(200)
            .body(json.toResponseBody("application/json".toMediaType()))
            .message(json)
            .request(request)
            .protocol(Protocol.HTTP_2)
            .build()
    }

    private fun readResourceOnClassPath(): String {
        val classLoader = Thread.currentThread().contextClassLoader
        return classLoader.getResourceAsStream(fileName).use { it.reader().readText() }
    }
}