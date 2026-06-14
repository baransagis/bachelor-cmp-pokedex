package eu.baran.pokedex.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color


@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val pokedexTypography = rememberPokedexTypography()
    val pokedexColors = when {
        darkTheme -> pokedexColors(
            material = PokedexDarkMaterialColorScheme,
            pokedexExtraColors = pokedexExtraColors(
                statTrack = Color(0xFF2A2D2F),
                divider = Color(0xFF333637),
                cardBorder = Color(0xFF333637),
                positive = Color(0xFF81C784),
                warning = Color(0xFFFFD54F),
                neutralIcon = Color(0xFFC6C6CD),
            ),
        )

        else -> pokedexColors()
    }

    CompositionLocalProvider(
        LocalPokedexColors provides pokedexColors,
    ) {
        MaterialTheme(
            colorScheme = pokedexColors.material,
            typography = pokedexTypography,
            content = content
        )
    }
}

object AppTheme {
    val colors: PokedexColors
        @Composable
        get() = LocalPokedexColors.current

    val typography: Typography
        @Composable
        get() = rememberPokedexTypography()
}
