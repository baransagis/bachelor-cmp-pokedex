package eu.baran.pokedex.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import eu.baran.pokedex.navigation.destinations.List
import eu.baran.pokedex.navigation.destinations.detailScreen
import eu.baran.pokedex.navigation.destinations.listScreen
import eu.baran.pokedex.navigation.destinations.navigateToDetail

@Composable
fun NavHostController.AppNavHost(modifier: Modifier = Modifier) {
    NavHost(
        modifier = modifier,
        navController = this,
        startDestination = List
    ) {
        listScreen(onPokemonClick = {
            this@AppNavHost.navigateToDetail(id = it)
        })

        detailScreen(onNavigateUp = this@AppNavHost::navigateUp)
    }
}