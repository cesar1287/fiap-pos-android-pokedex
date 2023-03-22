package br.com.heiderlopes.pokemonwstemplatev2.data.interceptor

import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders

object GlideAuthenticator {

    fun getUrlWithAuthenticator(url: String): GlideUrl {
        return GlideUrl(
            url,
            LazyHeaders
                .Builder()
                .addHeader(
                    "Authorization",
                    "Basic cG9rZWFwaTpwb2tlbW9u"
                )
                .build()
        )
    }
}