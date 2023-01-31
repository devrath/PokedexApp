package com.istudio.pokedex.data.remote.paging

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.istudio.pokedex.data.remote.PokeApi
import javax.inject.Inject

class PagingRepository @Inject constructor(
    private val api: PokeApi
) {
    fun getPokemon() = Pager(config = PagingConfig(pageSize = 20,),
        pagingSourceFactory = { PokemonPagingSource(api) }
    ).flow
}