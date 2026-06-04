package eu.baran.pokedex.data.models.dto

import kotlinx.serialization.Serializable

@Serializable
data class PokemonDetail(
    val id: Int,
    val name: String,
    val types: List<String>,
    val heightDm: Int,
    val weightHg: Int,
    val heightMeters: Double,
    val weightKg: Double,
    val abilities: List<String>,
    val baseStats: PokemonBaseStats,
    val genus: String,
    val description: String,
    val color: String?,
    val habitat: String?,
)

@Serializable
data class PokemonBaseStats(
    val hp: Int,
    val attack: Int,
    val defense: Int,
    val specialAttack: Int,
    val specialDefense: Int,
    val speed: Int,
)