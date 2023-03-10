package com.istudio.pokedex.di

import com.istudio.pokedex.data.remote.PokeApi
import com.istudio.pokedex.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Component annotated as singleton ensures the dependencies in our app live as long as the application does
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // Construct the API class
    @Singleton @Provides
    fun providePokeApi(): PokeApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(PokeApi::class.java)
    }
}