package br.com.heiderlopes.pokemonwstemplatev2.domain

sealed class ViewState<out T> {
    object Loading : ViewState<Nothing>()
    data class Success<T>(val data: T) : ViewState<T>()
    data class Failure(val throwable: Throwable) : ViewState<Nothing>()
}
