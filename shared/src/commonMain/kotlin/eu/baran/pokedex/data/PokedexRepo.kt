package eu.baran.pokedex.data

import eu.baran.pokedex.data.models.entities.toEntity
import eu.baran.pokedex.data.models.ui.PokemonDetailUi
import eu.baran.pokedex.data.models.ui.PokemonListItemUi
import eu.baran.pokedex.data.models.ui.toUiModel
import eu.baran.pokedex.data.remote.PokedexApi
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.withContext


interface PokedexRepo {
    fun observePokemonList(): Flow<PersistentList<PokemonListItemUi>>
    suspend fun loadPokemonListFromNetwork()
    suspend fun getPokemonDetail(id: Int): PokemonDetailUi
}

class PokedexRepoImpl(val pokedexApi: PokedexApi) : PokedexRepo {
    private val _pokemonList = MutableStateFlow<PersistentList<PokemonListItemUi>>(persistentListOf())

    override fun observePokemonList(): Flow<PersistentList<PokemonListItemUi>>  {
        return _pokemonList
    }


    override suspend fun loadPokemonListFromNetwork() {
        withContext(Dispatchers.IO) {
            val pokemon = pokedexApi.getPokemonList()
            _pokemonList.value = pokemon.map {
                it.toEntity().toUiModel()
            }.toPersistentList()
        }
    }

    override suspend fun getPokemonDetail(id: Int): PokemonDetailUi = withContext(Dispatchers.IO) {
        pokedexApi.getPokemonDetails(id).toUiModel()
    }
}
