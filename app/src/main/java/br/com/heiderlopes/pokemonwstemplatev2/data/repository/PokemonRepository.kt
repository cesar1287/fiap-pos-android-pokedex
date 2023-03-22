package br.com.heiderlopes.pokemonwstemplatev2.data.repository

import br.com.heiderlopes.pokemonwstemplatev2.data.api.PokemonService
import br.com.heiderlopes.pokemonwstemplatev2.data.mapper.PokemonResponseListToPokemonListMapper
import br.com.heiderlopes.pokemonwstemplatev2.data.mapper.PokemonResponseToPokemonMapper
import br.com.heiderlopes.pokemonwstemplatev2.data.mapper.PokemonToUpdatePokemonRequestMapper
import br.com.heiderlopes.pokemonwstemplatev2.model.Pokemon
import javax.inject.Inject

interface PokemonRepository {

    suspend fun getPokemons(
        size: Int,
        sort: String
    ): Result<List<Pokemon>>

    suspend fun getPokemon(
        number: String
    ): Result<Pokemon>

    suspend fun update(
        pokemon: Pokemon
    ): Result<Pokemon>
}

class PokemonRepositoryImpl @Inject constructor(
    private val pokemonService: PokemonService
): PokemonRepository {

    private val pokemonListMapper: PokemonResponseListToPokemonListMapper =
        PokemonResponseListToPokemonListMapper()

    private val pokemonMapper: PokemonResponseToPokemonMapper =
        PokemonResponseToPokemonMapper()

    private val pokemonToUpdatePokemonRequestMapper: PokemonToUpdatePokemonRequestMapper =
        PokemonToUpdatePokemonRequestMapper()

    override suspend fun getPokemons(size: Int, sort: String) =
        Result.success(
            pokemonListMapper.map(
                pokemonService.getPokemons(size, sort).pokemons
            )
        )

    override suspend fun getPokemon(number: String) =
        Result.success(
            pokemonMapper.map(
                pokemonService.getPokemon(number)
            )
        )

    override suspend fun update(pokemon: Pokemon) =
        Result.success(
            pokemonMapper.map(
                pokemonService.updatePokemon(
                    pokemonToUpdatePokemonRequestMapper.map(pokemon)
                )
            )
        )
}
