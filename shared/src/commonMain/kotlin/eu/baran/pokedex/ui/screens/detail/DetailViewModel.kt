package eu.baran.pokedex.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.baran.pokedex.data.PokedexRepo
import eu.baran.pokedex.data.models.ui.PokemonDetailUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailViewModel(val pokedexRepo: PokedexRepo) : ViewModel() {

    data class State(
        val pokemon: PokemonDetailUi? = null,
        val isError: Boolean = false,
    )

    private val _uiState = MutableStateFlow(State())
    val uiState = _uiState.asStateFlow()

    fun loadPokemonDetail(id: Int) {
        viewModelScope.launch {
            val pokemonDetail = pokedexRepo.getPokemonDetail(id).getOrNull()
            _uiState.update { currentState ->
                currentState.copy(pokemon = pokemonDetail, isError = pokemonDetail == null)
            }
        }
    }
}
