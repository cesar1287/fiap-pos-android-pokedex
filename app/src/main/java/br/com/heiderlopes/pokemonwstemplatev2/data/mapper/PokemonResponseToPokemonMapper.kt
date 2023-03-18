package br.com.heiderlopes.pokemonwstemplatev2.data.mapper

import br.com.heiderlopes.pokemonwstemplatev2.data.model.PokemonResponse
import br.com.heiderlopes.pokemonwstemplatev2.model.Pokemon
import br.com.heiderlopes.pokemonwstemplatev2.utils.Mapper

class PokemonResponseToPokemonMapper : Mapper<PokemonResponse, Pokemon> {

    override fun map(source: PokemonResponse): Pokemon {
        return Pokemon(
            number = source.number,
            name = source.name,
            imageURL = source.imageURL,
            ps = source.ps,
            attack = source.attack,
            defense = source.defense,
            velocity = source.velocity,
        )
    }
}
