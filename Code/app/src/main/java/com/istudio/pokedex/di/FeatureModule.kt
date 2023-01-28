package com.istudio.pokedex.di

import com.istudio.pokedex.data.implementation.PokemonRepositoryFeatureImpl
import com.istudio.pokedex.domain.PokemonRepositoryFeature
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class FeatureModule {

    @Singleton
    @Binds
    abstract fun bindRepository(implementation: PokemonRepositoryFeatureImpl): PokemonRepositoryFeature
}