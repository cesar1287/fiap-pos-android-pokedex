package br.com.heiderlopes.pokemonwstemplatev2.domain.usecase

import br.com.heiderlopes.pokemonwstemplatev2.data.repository.PokemonRepository
import br.com.heiderlopes.pokemonwstemplatev2.model.Pokemon
import javax.inject.Inject

interface GetPokemonUseCase {

    suspend operator fun invoke(number: String): Result<Pokemon>
}

class GetPokemonUseCaseImpl @Inject constructor(
    private val pokemonRepository: PokemonRepository
): GetPokemonUseCase {

    override suspend fun invoke(number: String) = pokemonRepository.getPokemon(
        number
    )
}