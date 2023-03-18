package br.com.heiderlopes.pokemonwstemplatev2.utils

interface Mapper<S, T> {
    fun map(source: S): T
}