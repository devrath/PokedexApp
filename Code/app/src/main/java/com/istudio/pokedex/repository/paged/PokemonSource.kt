package com.istudio.pokedex.repository.paged

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.istudio.pokedex.data.remote.PokeApi
import com.istudio.pokedex.data.remote.models.PokedexListEntry
import com.istudio.pokedex.data.remote.responses.Result
import com.istudio.pokedex.util.Constants.PAGE_SIZE
import java.util.Locale
import javax.inject.Inject

class PokemonSource @Inject constructor(
    private val api: PokeApi
): PagingSource<Int, PokedexListEntry>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokedexListEntry> {
        return try {
            val nextPage = params.key ?: 1
            val pokemonListResponse =  api.getPokemonList(limit = PAGE_SIZE,nextPage)

            val resultResp = pokemonListResponse.results

            val pokedexEntries =  pokemonListResponse.results.mapIndexed { index, entry ->
                val number = if(entry.url.endsWith("/")) {
                    entry.url.dropLast(1).takeLastWhile { it.isDigit() }
                } else {
                    entry.url.takeLastWhile { it.isDigit() }
                }
                val url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${number}.png"
                PokedexListEntry(entry.name.capitalize(Locale.ROOT), url, number.toInt())
            }

            LoadResult.Page(
                data = pokedexEntries,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = nextPage.plus(1)
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PokedexListEntry>): Int? {
        TODO("Not yet implemented")
    }
}
