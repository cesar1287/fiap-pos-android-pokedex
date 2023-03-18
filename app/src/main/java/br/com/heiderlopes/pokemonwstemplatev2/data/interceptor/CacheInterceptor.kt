package br.com.heiderlopes.pokemonwstemplatev2.data.interceptor

import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import java.util.concurrent.TimeUnit

const val CACHE_CONTROL_HEADER = "Cache-Control"
const val CACHE_TIME = 5

object CacheInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val originalResponse = chain.proceed(request)

        val cacheControl = CacheControl.Builder()
            .maxAge(CACHE_TIME, TimeUnit.MINUTES)
            .build()

        return originalResponse.newBuilder()
            .header(CACHE_CONTROL_HEADER, cacheControl.toString())
            .build()
    }
}
