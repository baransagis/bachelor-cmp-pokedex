package eu.baran.pokedex.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import eu.baran.pokedex.ui.screens.list.ListScreen
import kotlinx.serialization.Serializable

@Serializable
internal data object List

internal fun NavGraphBuilder.listScreen(onPokemonClick: (id: Int) -> Unit) {
    composable<List> {
        ListScreen(onPokemonClick = onPokemonClick)
    }
}