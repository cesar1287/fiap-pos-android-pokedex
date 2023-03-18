package br.com.heiderlopes.pokemonwstemplatev2.data.api

import android.content.Context
import br.com.heiderlopes.pokemonwstemplatev2.BuildConfig.BASE_URL
import br.com.heiderlopes.pokemonwstemplatev2.data.interceptor.AuthInterceptor
import br.com.heiderlopes.pokemonwstemplatev2.data.interceptor.CacheInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val CACHE_SIZE = 5 * 1024 * 1024L // 5 MB de cache

class RetrofitClient(
    private val application: Context
) {
    private val gson: Gson by lazy { GsonBuilder().create() }

    private val okHttp: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .cache(cacheSize())
            .addNetworkInterceptor(CacheInterceptor)
            .addInterceptor(AuthInterceptor())
            .build()
    }

    fun newInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttp)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    private fun cacheSize(): Cache {
        return Cache(application.cacheDir, CACHE_SIZE)
    }
}

