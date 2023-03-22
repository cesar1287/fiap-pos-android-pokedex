package br.com.heiderlopes.pokemonwstemplatev2.domain.usecase

import br.com.heiderlopes.pokemonwstemplatev2.data.repository.PokemonRepository
import br.com.heiderlopes.pokemonwstemplatev2.model.Pokemon
import javax.inject.Inject

interface UpdatePokemonUseCase {

    suspend operator fun invoke(pokemon: Pokemon): Result<Pokemon>
}

class UpdatePokemonUseCaseImpl @Inject constructor(
    private val pokemonRepository: PokemonRepository
): UpdatePokemonUseCase {

    override suspend operator fun invoke(pokemon: Pokemon) = pokemonRepository.update(
        pokemon
    )
}