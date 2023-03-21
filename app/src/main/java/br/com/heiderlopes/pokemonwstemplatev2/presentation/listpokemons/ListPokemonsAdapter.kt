package br.com.heiderlopes.pokemonwstemplatev2.presentation.listpokemons

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.heiderlopes.pokemonwstemplatev2.databinding.PokemonListItemBinding
import br.com.heiderlopes.pokemonwstemplatev2.model.Pokemon
import br.com.heiderlopes.pokemonwstemplatev2.utils.GlideApp

class ListPokemonsAdapter(
    val clickListener: (Pokemon) -> Unit
) : ListAdapter<Pokemon, ListPokemonsAdapter.PokemonViewHolder>(Pokemon.DIFF_CALLBACK) {

    inner class PokemonViewHolder(val binding: PokemonListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        return PokemonViewHolder(
            PokemonListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemon = getItem(position)
        holder.binding.tvPokemonName.text = pokemon.name
        holder.binding.tvPokemonNumber.text = pokemon.number


        GlideApp.with(holder.itemView.context)
            .load("https://pokedexdx.herokuapp.com${pokemon.imageURL}")
            .into(holder.binding.ivPokemon)

        holder.binding.root.setOnClickListener {
            clickListener(pokemon)
        }
    }
}

