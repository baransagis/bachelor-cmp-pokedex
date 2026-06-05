package eu.baran.pokedex.data

import eu.baran.pokedex.data.local.AppDatabase
import eu.baran.pokedex.data.models.entities.toEntity
import eu.baran.pokedex.data.models.ui.PokemonDetailUi
import eu.baran.pokedex.data.models.ui.PokemonListItemUi
import eu.baran.pokedex.data.models.ui.toUiModel
import eu.baran.pokedex.data.remote.PokedexApi
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext


interface PokedexRepo {
    fun observePokemonList(): Flow<PersistentList<PokemonListItemUi>>
    suspend fun loadPokemonListFromNetwork()
    suspend fun getPokemonDetail(id: Int): PokemonDetailUi?
}

class PokedexRepoImpl(val pokedexApi: PokedexApi, val database: AppDatabase) : PokedexRepo {
    override fun observePokemonList(): Flow<PersistentList<PokemonListItemUi>> =
        database.pokedexDao().getPokemonList().distinctUntilChanged().map { pokemonList ->
            pokemonList.map {
                it.toUiModel()
            }.toPersistentList()
        }

    override suspend fun loadPokemonListFromNetwork() {
        withContext(Dispatchers.IO) {
            // avoid network call if data is already persisted (for benchmarking)
            if (database.pokedexDao().getPokemonCount() == 0) {
                runCatching {
                    val pokemon = pokedexApi.getPokemonList()
                    database.pokedexDao().insertPokemon(pokemon.map { it.toEntity() })
                }
            }
        }
    }

    override suspend fun getPokemonDetail(id: Int): PokemonDetailUi?  = withContext(Dispatchers.IO) {
        runCatching { pokedexApi.getPokemonDetail(id = id).toUiModel() }.getOrNull()
    }
}