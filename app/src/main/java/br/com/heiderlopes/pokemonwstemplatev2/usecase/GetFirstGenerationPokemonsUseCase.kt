package br.com.heiderlopes.pokemonwstemplatev2.usecase

import br.com.heiderlopes.pokemonwstemplatev2.model.Pokemon
import br.com.heiderlopes.pokemonwstemplatev2.data.repository.PokemonRepository

interface GetFirstGenerationPokemonsUseCase {

    suspend operator fun invoke(): Result<List<Pokemon>>
}

class GetFirstGenerationPokemonsUseCaseImpl(
    private val pokemonRepository: PokemonRepository
): GetFirstGenerationPokemonsUseCase {

    override suspend operator fun invoke() = pokemonRepository.getPokemons(
        150,
        "number,asc"
    )
}
