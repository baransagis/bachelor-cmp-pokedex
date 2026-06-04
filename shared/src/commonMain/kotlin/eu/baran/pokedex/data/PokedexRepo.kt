package eu.baran.pokedex.data

import eu.baran.pokedex.data.models.dto.PokemonBaseStats
import eu.baran.pokedex.data.models.dto.PokemonDetail
import eu.baran.pokedex.data.models.ui.PokemonListItemUi
import eu.baran.pokedex.data.models.ui.toUiModel
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf


interface PokedexRepo {
    fun observePokemonList(): Flow<PersistentList<PokemonListItemUi>>
    suspend fun loadPokemonListFromNetwork()
    suspend fun getPokemonDetail(id: Int): PokemonDetail
}

class PokedexRepoImpl() : PokedexRepo {
    private val samplePokemon = listOf(
        PokemonDetail(
            id = 1,
            name = "Bisasam",
            types = listOf("Grass", "Poison"),
            heightDm = 7,
            weightHg = 69,
            heightMeters = 0.7,
            weightKg = 6.9,
            abilities = listOf("Overgrow", "Chlorophyll"),
            baseStats = PokemonBaseStats(
                hp = 45,
                attack = 49,
                defense = 49,
                specialAttack = 65,
                specialDefense = 65,
                speed = 45,
            ),
            genus = "Seed Pokemon",
            description = "A strange seed was planted on its back at birth. The plant sprouts and grows with this Pokemon.",
            color = "green",
            habitat = "grassland",
        ),
        PokemonDetail(
            id = 4,
            name = "Charmander",
            types = listOf("Fire"),
            heightDm = 6,
            weightHg = 85,
            heightMeters = 0.6,
            weightKg = 8.5,
            abilities = listOf("Blaze", "Solar Power"),
            baseStats = PokemonBaseStats(
                hp = 39,
                attack = 52,
                defense = 43,
                specialAttack = 60,
                specialDefense = 50,
                speed = 65,
            ),
            genus = "Lizard Pokemon",
            description = "It has a preference for hot things. When it rains, steam is said to spout from the tip of its tail.",
            color = "red",
            habitat = "mountain",
        ),
        PokemonDetail(
            id = 7,
            name = "Squirtle",
            types = listOf("Water"),
            heightDm = 5,
            weightHg = 90,
            heightMeters = 0.5,
            weightKg = 9.0,
            abilities = listOf("Torrent", "Rain Dish"),
            baseStats = PokemonBaseStats(
                hp = 44,
                attack = 48,
                defense = 65,
                specialAttack = 50,
                specialDefense = 64,
                speed = 43,
            ),
            genus = "Tiny Turtle Pokemon",
            description = "When it retracts its long neck into its shell, it squirts out water with vigorous force.",
            color = "blue",
            habitat = "waters-edge",
        ),
    )

    override fun observePokemonList(): Flow<PersistentList<PokemonListItemUi>> =
        flowOf(
            samplePokemon
                .map {
                    PokemonListItemUi(
                        id = it.id,
                        name = it.name,
                        types = it.types.toPersistentList(),
                    )
                }
                .toPersistentList()
        )

    override suspend fun loadPokemonListFromNetwork() {
        //        withContext(Dispatchers.IO) {
//            // avoid network call if data  is already persisted (for benchmarking)
//            if (database.pokedexDao().getPokemonCount() == 0) {
//                val pokemon = pokedexApi.getPokemonList()
//                database.pokedexDao().insertPokemon(pokemon.map { it.toEntity() })
//            }
//        }
    }

    override suspend fun getPokemonDetail(id: Int): PokemonDetail = samplePokemon.first()
}
