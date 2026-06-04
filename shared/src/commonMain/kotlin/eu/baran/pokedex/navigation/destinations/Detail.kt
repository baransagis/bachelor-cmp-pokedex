package eu.baran.pokedex.navigation.destinations

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import eu.baran.pokedex.ui.screens.detail.DetailScreen
import kotlinx.serialization.Serializable

@Serializable
internal data class Detail(
    val id: Int,
)

internal fun NavGraphBuilder.detailScreen(onNavigateUp: () -> Unit) {
    composable<Detail> { backstackEntry ->
        val detail: Detail = backstackEntry.toRoute()
        DetailScreen(id = detail.id, onNavigateUp = onNavigateUp)
    }
}

internal fun NavController.navigateToDetail(id: Int) = this.navigate(Detail(id = id))