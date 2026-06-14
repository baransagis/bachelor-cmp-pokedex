package eu.baran.pokedex

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import eu.baran.pokedex.navigation.AppNavHost
import eu.baran.pokedex.ui.theme.AppTheme

@Composable
@Preview
fun App() {
    val navController = rememberNavController()
    AppTheme {
        navController.AppNavHost(
            modifier = Modifier
                .fillMaxSize()
        )
    }
}
