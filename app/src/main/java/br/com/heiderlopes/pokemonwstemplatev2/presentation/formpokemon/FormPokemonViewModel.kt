package br.com.heiderlopes.pokemonwstemplatev2.presentation.formpokemon

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.heiderlopes.pokemonwstemplatev2.domain.ViewState
import br.com.heiderlopes.pokemonwstemplatev2.domain.usecase.GetPokemonUseCase
import br.com.heiderlopes.pokemonwstemplatev2.domain.usecase.UpdatePokemonUseCase
import br.com.heiderlopes.pokemonwstemplatev2.model.Pokemon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FormPokemonViewModel @Inject constructor(
    val getPokemonUseCase: GetPokemonUseCase,
    val updatePokemonUseCase: UpdatePokemonUseCase
) : ViewModel() {

    private val _pokemonResult = MutableLiveData<ViewState<Pokemon>>()

    val pokemonResult: LiveData<ViewState<Pokemon>>
        get() = _pokemonResult

    private val _pokemonUpdateResult = MutableLiveData<ViewState<Pokemon>>()

    val pokemonUpdateResult : LiveData<ViewState<Pokemon>>
        get() = _pokemonUpdateResult


    fun getPokemon(number: String) {
        _pokemonResult.postValue(ViewState.Loading)

        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                getPokemonUseCase(number)
            }.onSuccess {
                _pokemonResult.postValue(
                    ViewState.Success(
                        it.getOrDefault(
                            Pokemon(
                                "",
                                "",
                                "",
                                0,
                                0,
                                0,
                                0
                            )
                        )
                    )
                )

            }.onFailure {
                _pokemonResult.postValue(ViewState.Failure(it))
            }
        }
    }

    fun update(pokemon: Pokemon) {
        _pokemonUpdateResult.postValue(ViewState.Loading)

        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                updatePokemonUseCase(pokemon)
            }.onSuccess {
                _pokemonUpdateResult.postValue(
                    ViewState.Success(
                        it.getOrDefault(
                            Pokemon(
                                "",
                                "",
                                "",
                                0,
                                0,
                                0,
                                0
                            )
                        )
                    )
                )
            }.onFailure {
                _pokemonUpdateResult.postValue(ViewState.Failure(it))
            }
        }
    }

}
