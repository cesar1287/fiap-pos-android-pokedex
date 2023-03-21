package br.com.heiderlopes.pokemonwstemplatev2.presentation.listpokemons

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import br.com.heiderlopes.pokemonwstemplatev2.databinding.ActivityListPokemonsBinding
import br.com.heiderlopes.pokemonwstemplatev2.domain.ViewState
import br.com.heiderlopes.pokemonwstemplatev2.presentation.formpokemon.FormPokemonActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListPokemonsActivity : AppCompatActivity() {

    private val viewModel: ListPokemonsViewModel by viewModels()

    private val listPokemonsAdapter: ListPokemonsAdapter by lazy {
        ListPokemonsAdapter {
            val intent = Intent(this, FormPokemonActivity::class.java)
            intent.putExtra("POKEMON", it.number)
            startActivity(intent)
        }
    }

    private val binding: ActivityListPokemonsBinding by lazy {
        ActivityListPokemonsBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel.getPokemons()

        setupObservables()
        setupRecyclerView()
    }

    private fun setupObservables() {
        viewModel.pokemonResult.observe(this) {
            with(binding) {
                when (it) {
                    is ViewState.Success -> {
                        loading.containerLoading.isVisible = false
                        listPokemonsAdapter.submitList(it.data)
                    }
                    is ViewState.Loading -> {
                        loading.containerLoading.isVisible = true
                    }
                    is ViewState.Failure -> {
                        loading.containerLoading.isVisible = false
                        Toast.makeText(
                            this@ListPokemonsActivity,
                            it.throwable.message,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }
    }

    private fun setupRecyclerView() {
        with(binding) {
            rvPokemons.apply {
                adapter = listPokemonsAdapter
                layoutManager = GridLayoutManager(this@ListPokemonsActivity, 3)
            }
        }
    }
}