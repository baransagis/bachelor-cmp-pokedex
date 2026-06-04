package eu.baran.pokedex.data.models.ui

import androidx.compose.runtime.Immutable
import eu.baran.pokedex.data.models.entities.PokemonListItemEntity
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toPersistentList

@Immutable
data class PokemonListItemUi(
    val id: Int,
    val name: String,
    val types: ImmutableList<String>,
)

internal fun PokemonListItemEntity.toUiModel() = PokemonListItemUi(
    id = id,
    name = name,
    types = types.toPersistentList(),
)