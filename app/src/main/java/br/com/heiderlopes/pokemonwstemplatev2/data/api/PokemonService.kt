package br.com.heiderlopes.pokemonwstemplatev2.data.api

import br.com.heiderlopes.pokemonwstemplatev2.data.model.PokemonListResponse
import br.com.heiderlopes.pokemonwstemplatev2.data.model.PokemonResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonService {

    @GET("/api/pokemon")
    suspend fun getPokemons(
        @Query("size") size: Int,
        @Query("sort") sort: String
    ): PokemonListResponse

    @GET("/api/pokemon/{number}")
    suspend fun getPokemon(
        @Path("number") number: String
    ) : PokemonResponse
}
