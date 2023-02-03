package com.istudio.pokedex.data.remote.paging

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.istudio.pokedex.data.remote.PokeApi
import com.istudio.pokedex.util.Constants.PAGE_SIZE
import javax.inject.Inject

class PagingRepository @Inject constructor(
    private val api: PokeApi
) {
    fun getPokemon() = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE),
        pagingSourceFactory = {
            PokemonPagingSource(api)
        }
    ).flow
}