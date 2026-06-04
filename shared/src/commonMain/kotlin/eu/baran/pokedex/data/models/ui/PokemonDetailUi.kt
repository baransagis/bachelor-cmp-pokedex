package eu.baran.pokedex.data.models.ui

import androidx.compose.runtime.Immutable
import eu.baran.pokedex.data.models.dto.PokemonBaseStats
import eu.baran.pokedex.data.models.dto.PokemonDetail
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toPersistentList

@Immutable
data class PokemonDetailUi(
    val id: Int,
    val name: String,
    val types: ImmutableList<String>,
    val heightDm: Int,
    val weightHg: Int,
    val heightMeters: Double,
    val weightKg: Double,
    val abilities: ImmutableList<String>,
    val baseStats: PokemonBaseStatsUi,
    val genus: String,
    val description: String,
    val color: String?,
    val habitat: String?,
)

@Immutable
data class PokemonBaseStatsUi(
    val hp: Int,
    val attack: Int,
    val defense: Int,
    val specialAttack: Int,
    val specialDefense: Int,
    val speed: Int,
)

internal fun PokemonDetail.toUiModel() = PokemonDetailUi(
    id = id,
    name = name,
    types = types.toPersistentList(),
    heightDm = heightDm,
    weightHg = weightHg,
    heightMeters = heightMeters,
    weightKg = weightKg,
    abilities = abilities.toPersistentList(),
    baseStats = baseStats.toUiModel(),
    genus = genus,
    description = description,
    color = color,
    habitat = habitat,)

private fun PokemonBaseStats.toUiModel() = PokemonBaseStatsUi(
    hp = hp,
    attack = attack,
    defense = defense,
    specialAttack = specialAttack,
    specialDefense = specialDefense,
    speed = speed,
)

