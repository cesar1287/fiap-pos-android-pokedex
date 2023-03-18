package br.com.heiderlopes.pokemonwstemplatev2.di

import br.com.heiderlopes.pokemonwstemplatev2.data.repository.PokemonRepository
import br.com.heiderlopes.pokemonwstemplatev2.usecase.GetFirstGenerationPokemonsUseCase
import br.com.heiderlopes.pokemonwstemplatev2.usecase.GetFirstGenerationPokemonsUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    fun provideGetFirsGenerationPokemonsUseCase(
        pokemonRepository: PokemonRepository
    ): GetFirstGenerationPokemonsUseCase {
        return GetFirstGenerationPokemonsUseCaseImpl(
            pokemonRepository
        )
    }
}