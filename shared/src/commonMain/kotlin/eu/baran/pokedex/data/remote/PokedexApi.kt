package eu.baran.pokedex.data.remote

import eu.baran.pokedex.data.models.dto.PokemonDetail
import eu.baran.pokedex.data.models.dto.PokemonListItem
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class PokedexApi(
    private val client: HttpClient,
    private val config: ApiConfig
) {
    suspend fun getPokemonList(): List<PokemonListItem> {
        return client
            .get("${config.baseUrl}/pokemon")
            .body()
    }

    suspend fun getPokemonDetails(id: Int): PokemonDetail {
        return client
            .get("${config.baseUrl}/pokemon/$id")
            .body()
    }
}