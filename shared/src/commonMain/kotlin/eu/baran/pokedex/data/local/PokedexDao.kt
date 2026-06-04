package eu.baran.pokedex.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import eu.baran.pokedex.data.models.entities.PokemonListItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PokedexDao {
    @Query("SELECT * FROM pokemon ORDER BY id ASC")
    fun getPokemonList(): Flow<List<PokemonListItemEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemon(pokemon: List<PokemonListItemEntity>)

    // this is used to quickly check if data is in db, to block the network request (to ensure benchmarking will not load network calls, when data is preloaded)
    @Query("SELECT COUNT(*) FROM pokemon")
    suspend fun getPokemonCount(): Int
}