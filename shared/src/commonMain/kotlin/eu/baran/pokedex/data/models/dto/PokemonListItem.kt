package eu.baran.pokedex.data.models.dto

import kotlinx.serialization.Serializable

@Serializable
data class PokemonListItem(
    val id: Int,
    val name: String,
    val types: List<String>,
)