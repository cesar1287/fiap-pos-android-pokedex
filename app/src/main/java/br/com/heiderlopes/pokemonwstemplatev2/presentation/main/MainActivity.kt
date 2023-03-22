package br.com.heiderlopes.pokemonwstemplatev2.presentation.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.heiderlopes.pokemonwstemplatev2.databinding.ActivityMainBinding
import br.com.heiderlopes.pokemonwstemplatev2.presentation.listpokemons.ListPokemonsActivity
import br.com.heiderlopes.pokemonwstemplatev2.presentation.scan.ScanActivity

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btPokemonList.setOnClickListener {
            val intent = Intent(this, ListPokemonsActivity::class.java)
            startActivity(intent)
        }

        binding.btPokedex.setOnClickListener {
            startActivity(Intent(this, ScanActivity::class.java))
        }

    }
}