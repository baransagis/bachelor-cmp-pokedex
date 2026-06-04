package eu.baran.pokedex.data.models.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import eu.baran.pokedex.data.models.dto.PokemonListItem


@Entity(tableName = "pokemon")
data class PokemonListItemEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo val name: String,
    @ColumnInfo val types: List<String>,
)

internal fun PokemonListItem.toEntity() = PokemonListItemEntity(
    id = id,
    name = name,
    types = types,
)