package br.com.heiderlopes.pokemonwstemplatev2.data.model

import com.google.gson.annotations.SerializedName

data class PokemonResponse(
    @SerializedName("number")
    val number: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("imageURL")
    val imageURL: String,
    @SerializedName("ps")
    var ps: Int,
    @SerializedName("attack")
    var attack: Int,
    @SerializedName("defense")
    var defense: Int,
    @SerializedName("velocity")
    var velocity: Int
)
