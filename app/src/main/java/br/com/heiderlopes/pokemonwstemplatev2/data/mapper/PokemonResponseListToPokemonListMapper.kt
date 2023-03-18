package br.com.heiderlopes.pokemonwstemplatev2.data.mapper

import br.com.heiderlopes.pokemonwstemplatev2.data.model.PokemonResponse
import br.com.heiderlopes.pokemonwstemplatev2.model.Pokemon
import br.com.heiderlopes.pokemonwstemplatev2.utils.Mapper

class PokemonResponseListToPokemonListMapper :
    Mapper<List<PokemonResponse>, List<Pokemon>> {

    private val mapper = PokemonResponseToPokemonMapper()

    override fun map(source: List<PokemonResponse>): List<Pokemon> =
        source.map { mapper.map(it) }

}
