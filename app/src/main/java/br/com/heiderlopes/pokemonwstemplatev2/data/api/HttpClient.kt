package br.com.heiderlopes.pokemonwstemplatev2.data.api

import retrofit2.Retrofit

class HttpClient(private val retrofit: Retrofit) {

    fun <T> create(service: Class<T>): T {
        return retrofit.create(service)
    }
}
