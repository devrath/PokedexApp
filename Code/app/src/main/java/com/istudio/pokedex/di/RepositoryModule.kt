package com.istudio.pokedex.di

import com.istudio.pokedex.data.implementation.PokemonRepositoryFeatureImpl
import com.istudio.pokedex.data.remote.PokeApi
import com.istudio.pokedex.data.repository.PokemonRepository
import com.istudio.pokedex.domain.PokemonRepositoryFeature
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Provides
    @Singleton
    fun provideRepositoryImpl(feature: PokemonRepositoryFeatureImpl) = PokemonRepository(feature)


    @Provides
    @Singleton
    fun provideRepositoryFeature(api: PokeApi): PokemonRepositoryFeature {
        return PokemonRepositoryFeatureImpl(api)
    }

}