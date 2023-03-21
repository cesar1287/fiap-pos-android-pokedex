package br.com.heiderlopes.pokemonwstemplatev2.domain.usecase

import br.com.heiderlopes.pokemonwstemplatev2.model.Pokemon
import br.com.heiderlopes.pokemonwstemplatev2.data.repository.PokemonRepository
import javax.inject.Inject

interface GetFirstGenerationPokemonsUseCase {

    suspend operator fun invoke(): Result<List<Pokemon>>
}

class GetFirstGenerationPokemonsUseCaseImpl @Inject constructor(
    private val pokemonRepository: PokemonRepository
): GetFirstGenerationPokemonsUseCase {

    override suspend operator fun invoke() = pokemonRepository.getPokemons(
        150,
        "number,asc"
    )
}
