package br.com.heiderlopes.pokemonwstemplatev2.presentation.listpokemons

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import br.com.heiderlopes.pokemonwstemplatev2.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListPokemonsActivity : AppCompatActivity() {

    private val viewModel: ListPokemonsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_pokemons)
    }
}