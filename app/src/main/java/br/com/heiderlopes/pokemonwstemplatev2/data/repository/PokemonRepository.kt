package br.com.heiderlopes.pokemonwstemplatev2.data.repository

import br.com.heiderlopes.pokemonwstemplatev2.data.api.PokemonService
import br.com.heiderlopes.pokemonwstemplatev2.data.mapper.PokemonResponseListToPokemonListMapper
import br.com.heiderlopes.pokemonwstemplatev2.model.Pokemon

interface PokemonRepository {

    suspend fun getPokemons(
        size: Int,
        sort: String
    ): Result<List<Pokemon>>
}

class PokemonRepositoryImpl(
    private val pokemonService: PokemonService
): PokemonRepository {

    private val pokemonListMapper: PokemonResponseListToPokemonListMapper =
        PokemonResponseListToPokemonListMapper()

    override suspend fun getPokemons(size: Int, sort: String) =
        Result.success(
            pokemonListMapper.map(
                pokemonService.getPokemons(size, sort).pokemons
            )
        )

}
