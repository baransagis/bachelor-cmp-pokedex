package eu.baran.pokedex.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class PokedexColors(
    val material : ColorScheme,
    val extraColors: PokedexExtraColors,
)

@Immutable
data class PokedexTypeColors(
    val normal: Color,
    val fire: Color,
    val water: Color,
    val electric: Color,
    val grass: Color,
    val ice: Color,
    val fighting: Color,
    val poison: Color,
    val ground: Color,
    val flying: Color,
    val psychic: Color,
    val bug: Color,
    val rock: Color,
    val ghost: Color,
    val dragon: Color,
    val dark: Color,
    val steel: Color,
    val fairy: Color,
)

@Immutable
data class PokedexStatColors(
    val hp: Color,
    val attack: Color,
    val defense: Color,
    val specialAttack: Color,
    val specialDefense: Color,
    val speed: Color,
)

@Immutable
data class PokedexExtraColors(
    val type: PokedexTypeColors,
    val stat: PokedexStatColors,
    val statTrack: Color,
    val divider: Color,
    val cardBorder: Color,
    val positive: Color,
    val warning: Color,
    val neutralIcon: Color,
)

internal fun pokedexColors(material: ColorScheme = PokedexLightMaterialColorScheme, pokedexExtraColors: PokedexExtraColors = pokedexExtraColors()) =
    PokedexColors(
        material,
        pokedexExtraColors,
    )


internal fun pokedexExtraColors(
    type: PokedexTypeColors = pokedexTypeColors(),
    stat: PokedexStatColors = pokedexStatColors(),
    statTrack: Color = Color(0xFFF3F4F6),
    divider: Color = Color(0xFFE5E7EB),
    cardBorder: Color = Color(0xFFF3F4F6),
    positive: Color = Color(0xFF2E7D32),
    warning: Color = Color(0xFFF9A825),
    neutralIcon: Color = Color(0xFF585F6C),
) = PokedexExtraColors(
    type = type,
    stat = stat,
    statTrack = statTrack,
    divider = divider,
    cardBorder = cardBorder,
    positive = positive,
    warning = warning,
    neutralIcon = neutralIcon,
)

internal fun pokedexTypeColors(
    normal: Color = Color(0xFFA8A77A),
    fire: Color = Color(0xFFEE8130),
    water: Color = Color(0xFF6390F0),
    electric: Color = Color(0xFFF7D02C),
    grass: Color = Color(0xFF7AC74C),
    ice: Color = Color(0xFF96D9D6),
    fighting: Color = Color(0xFFC22E28),
    poison: Color = Color(0xFFA33EA1),
    ground: Color = Color(0xFFE2BF65),
    flying: Color = Color(0xFFA98FF3),
    psychic: Color = Color(0xFFF95587),
    bug: Color = Color(0xFFA6B91A),
    rock: Color = Color(0xFFB6A136),
    ghost: Color = Color(0xFF735797),
    dragon: Color = Color(0xFF6F35FC),
    dark: Color = Color(0xFF705746),
    steel: Color = Color(0xFFB7B7CE),
    fairy: Color = Color(0xFFD685AD),
) = PokedexTypeColors(
    normal = normal,
    fire = fire,
    water = water,
    electric = electric,
    grass = grass,
    ice = ice,
    fighting = fighting,
    poison = poison,
    ground = ground,
    flying = flying,
    psychic = psychic,
    bug = bug,
    rock = rock,
    ghost = ghost,
    dragon = dragon,
    dark = dark,
    steel = steel,
    fairy = fairy,
)

internal fun pokedexStatColors(
    hp: Color = Color(0xFFEF5350),
    attack: Color = Color(0xFFFFA726),
    defense: Color = Color(0xFFFFD54F),
    specialAttack: Color = Color(0xFF42A5F5),
    specialDefense: Color = Color(0xFF66BB6A),
    speed: Color = Color(0xFFAB47BC),
) = PokedexStatColors(
    hp = hp,
    attack = attack,
    defense = defense,
    specialAttack = specialAttack,
    specialDefense = specialDefense,
    speed = speed,
)



val PokedexLightMaterialColorScheme = lightColorScheme(
    primary = Color(0xFF000000),
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFF141B2B),
    onPrimaryContainer = Color(0xFFDCE2F7),

    secondary = Color(0xFF585F6C),
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFFDCE2F3),
    onSecondaryContainer = Color(0xFF151C27),

    tertiary = Color(0xFF705746),
    onTertiary = Color(0xFFFFFFFF),
    tertiaryContainer = Color(0xFFF9DEBF),
    onTertiaryContainer = Color(0xFF261906),

    error = Color(0xFFBA1A1A),
    onError = Color(0xFFFFFFFF),
    errorContainer = Color(0xFFFFDAD6),
    onErrorContainer = Color(0xFF93000A),

    background = Color(0xFFF8F9FA),
    onBackground = Color(0xFF191C1D),

    surface = Color(0xFFF8F9FA),
    onSurface = Color(0xFF191C1D),
    surfaceVariant = Color(0xFFE1E3E4),
    onSurfaceVariant = Color(0xFF45464C),

    outline = Color(0xFF76777D),
    outlineVariant = Color(0xFFC6C6CD),

    inverseSurface = Color(0xFF2E3132),
    inverseOnSurface = Color(0xFFF0F1F2),
    inversePrimary = Color(0xFFC0C6DB),

    surfaceTint = Color(0xFF575E70),

    surfaceDim = Color(0xFFD9DADB),
    surfaceBright = Color(0xFFF8F9FA),
    surfaceContainerLowest = Color(0xFFFFFFFF),
    surfaceContainerLow = Color(0xFFF3F4F5),
    surfaceContainer = Color(0xFFEDEEEF),
    surfaceContainerHigh = Color(0xFFE7E8E9),
    surfaceContainerHighest = Color(0xFFE1E3E4),
)

val PokedexDarkMaterialColorScheme = darkColorScheme(
    primary = Color(0xFFE1E5F4),
    onPrimary = Color(0xFF151922),
    primaryContainer = Color(0xFFC0C6DB),
    onPrimaryContainer = Color(0xFF141B2B),

    secondary = Color(0xFFC0C7D6),
    onSecondary = Color(0xFF151C27),
    secondaryContainer = Color(0xFF404754),
    onSecondaryContainer = Color(0xFFDCE2F3),

    tertiary = Color(0xFFDCC2A4),
    onTertiary = Color(0xFF261906),
    tertiaryContainer = Color(0xFF55442D),
    onTertiaryContainer = Color(0xFFF9DEBF),

    error = Color(0xFFFFB4AB),
    onError = Color(0xFF690005),
    errorContainer = Color(0xFF93000A),
    onErrorContainer = Color(0xFFFFDAD6),

    background = Color(0xFF111415),
    onBackground = Color(0xFFE1E3E4),

    surface = Color(0xFF111415),
    onSurface = Color(0xFFE1E3E4),
    surfaceVariant = Color(0xFF45464C),
    onSurfaceVariant = Color(0xFFC6C6CD),

    outline = Color(0xFF909198),
    outlineVariant = Color(0xFF45464C),

    inverseSurface = Color(0xFFE1E3E4),
    inverseOnSurface = Color(0xFF2E3132),
    inversePrimary = Color(0xFF575E70),

    surfaceTint = Color(0xFFC0C6DB),

    surfaceDim = Color(0xFF111415),
    surfaceBright = Color(0xFF373A3B),
    surfaceContainerLowest = Color(0xFF0C0F10),
    surfaceContainerLow = Color(0xFF191C1D),
    surfaceContainer = Color(0xFF1D2021),
    surfaceContainerHigh = Color(0xFF282B2C),
    surfaceContainerHighest = Color(0xFF333637),
)


internal val LocalPokedexColors: ProvidableCompositionLocal<PokedexColors> =
    staticCompositionLocalOf {
            pokedexColors()
    }