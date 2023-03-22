package br.com.heiderlopes.pokemonwstemplatev2.presentation.formpokemon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import br.com.heiderlopes.pokemonwstemplatev2.data.interceptor.GlideAuthenticator
import br.com.heiderlopes.pokemonwstemplatev2.databinding.ActivityFormPokemonBinding
import br.com.heiderlopes.pokemonwstemplatev2.domain.ViewState
import br.com.heiderlopes.pokemonwstemplatev2.model.Pokemon
import br.com.heiderlopes.pokemonwstemplatev2.presentation.listpokemons.ListPokemonsActivity.Companion.KEY_INTENT_POKEMON_NUMBER
import br.com.heiderlopes.pokemonwstemplatev2.utils.GlideApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FormPokemonActivity : AppCompatActivity() {

    private val viewModel: FormPokemonViewModel by viewModels()

    private val binding: ActivityFormPokemonBinding by lazy {
        ActivityFormPokemonBinding.inflate(layoutInflater)
    }

    private lateinit var pokemon: Pokemon

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val pokemonNumber = intent.getStringExtra(KEY_INTENT_POKEMON_NUMBER) ?: ""
        viewModel.getPokemon(pokemonNumber)

        registerObserver()
    }

    private fun registerObserver() {
        viewModel.pokemonResult.observe(this) {
            when (it) {
                is ViewState.Success -> {
                    setValues(it.data)
                }
                is ViewState.Loading -> {
                }
                is ViewState.Failure -> {
                    Toast.makeText(this, it.throwable.message, Toast.LENGTH_LONG).show()
                }
            }
        }

        viewModel.pokemonUpdateResult.observe(this) {
            when (it) {
                is ViewState.Success -> {
                    Toast.makeText(this, "Pokémon atualizado com sucesso", Toast.LENGTH_LONG).show()
                }
                is ViewState.Loading -> {
                }
                is ViewState.Failure -> {
                    Toast.makeText(this, it.throwable.message, Toast.LENGTH_LONG).show()
                }
            }
        }

        binding.btSaveForm.setOnClickListener {
            with(binding) {
                pokemon.attack = sbAttack.progress
                pokemon.defense = sbDefense.progress
                pokemon.velocity = sbVelocity.progress
                pokemon.ps = sbPS.progress
                viewModel.update(
                    pokemon
                )
            }
        }

    }

    private fun setValues(pokemon: Pokemon) {
        with(binding) {
            this@FormPokemonActivity.pokemon = pokemon
            tvPokemonNameForm.text = pokemon.name

            val imageUrl = "https://pokedexdx.herokuapp.com${pokemon.imageURL}"
            GlideApp.with(this@FormPokemonActivity)
                .load(GlideAuthenticator.getUrlWithAuthenticator(imageUrl))
                .into(ivPokemonForm)

            sbAttack.progress = pokemon.attack
            sbDefense.progress = pokemon.defense
            sbPS.progress = pokemon.ps
            sbVelocity.progress = pokemon.velocity

            tvAttackValue.text = pokemon.attack.toString()
            tvDefenseValue.text = pokemon.defense.toString()
            tvPSValue.text = pokemon.ps.toString()
            tvVelocityValue.text = pokemon.velocity.toString()

            setListener(sbAttack, tvAttackValue)
            setListener(sbDefense, tvDefenseValue)
            setListener(sbVelocity, tvVelocityValue)
            setListener(sbPS, tvPSValue)
        }
    }

    private fun setListener(seekBar: SeekBar, textView: TextView) {
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                textView.text = progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }
}