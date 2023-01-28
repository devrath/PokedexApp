package com.istudio.pokedex.di

import com.istudio.pokedex.data.implementation.PokemonRepositoryFeatureImpl
import com.istudio.pokedex.domain.PokemonRepositoryFeature
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
abstract class FeatureModule {

    @ViewModelScoped
    @Binds
    abstract fun bindRepository(implementation: PokemonRepositoryFeatureImpl): PokemonRepositoryFeature
}