package eu.baran.pokedex.ui.screens.list


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.baran.pokedex.data.PokedexRepo
import eu.baran.pokedex.data.models.ui.PokemonListItemUi
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ListViewModel(val pokedexRepo: PokedexRepo) : ViewModel() {

    data class State(
        val pokemonList: ImmutableList<PokemonListItemUi> = persistentListOf(),
        val isError: Boolean = false,
    )

    private val _uiState = MutableStateFlow(State())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            pokedexRepo.observePokemonList().collect { pokemonList ->
                _uiState.update { currentState ->
                    currentState.copy(pokemonList = pokemonList)
                }
            }
        }
    }

    fun loadPokemon() {
        viewModelScope.launch {
            val result = pokedexRepo.loadPokemonListFromNetwork()
            _uiState.update { currentState ->
                currentState.copy(isError = result.isFailure)
            }
        }
    }
}