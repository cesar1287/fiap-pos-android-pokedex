package br.com.heiderlopes.pokemonwstemplatev2.model

import androidx.recyclerview.widget.DiffUtil

data class Pokemon(
    val number: String,
    val name: String,
    val imageURL: String,
    var ps: Int,
    var attack: Int,
    var defense: Int,
    var velocity: Int
) {

    companion object {
        var DIFF_CALLBACK: DiffUtil.ItemCallback<Pokemon> =
            object : DiffUtil.ItemCallback<Pokemon>() {
                override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
                    return oldItem.number == newItem.number
                }

                override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
                    return oldItem == newItem
                }
            }
    }
}