package br.com.heiderlopes.pokemonwstemplatev2.di

import android.content.Context
import br.com.heiderlopes.pokemonwstemplatev2.data.api.PokemonService
import br.com.heiderlopes.pokemonwstemplatev2.data.api.RetrofitClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {

    @Provides
    fun providePokemonService(
        @ApplicationContext context: Context
    ): PokemonService {
        return RetrofitClient(context).newInstance().create(PokemonService::class.java)
    }
}