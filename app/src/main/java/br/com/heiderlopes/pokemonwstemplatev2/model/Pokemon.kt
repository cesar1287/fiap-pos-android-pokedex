package br.com.heiderlopes.pokemonwstemplatev2.model

data class Pokemon(
    val number: String,
    val name: String,
    val imageURL: String,
    var ps: Int,
    var attack: Int,
    var defense: Int,
    var velocity: Int
)