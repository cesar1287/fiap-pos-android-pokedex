package br.com.heiderlopes.pokemonwstemplatev2.di

import br.com.heiderlopes.pokemonwstemplatev2.data.api.PokemonService
import br.com.heiderlopes.pokemonwstemplatev2.data.repository.PokemonRepository
import br.com.heiderlopes.pokemonwstemplatev2.data.repository.PokemonRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun providePokemonRepository(
        pokemonService: PokemonService
    ): PokemonRepository {
        return PokemonRepositoryImpl(
            pokemonService
        )
    }
}