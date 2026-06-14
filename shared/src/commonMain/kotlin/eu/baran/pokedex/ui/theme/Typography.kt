package eu.baran.pokedex.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import cmppokedex.shared.generated.resources.Res
import cmppokedex.shared.generated.resources.inter_italic_variable
import cmppokedex.shared.generated.resources.inter_variable
import org.jetbrains.compose.resources.Font

@Composable
fun rememberPokedexFontFamily(): FontFamily {
    val regularFont = Font(Res.font.inter_variable, weight = FontWeight.Normal)
    val mediumFont = Font(Res.font.inter_variable, weight = FontWeight.Medium)
    val semiBoldFont = Font(Res.font.inter_variable, weight = FontWeight.SemiBold)
    val boldFont = Font(Res.font.inter_variable, weight = FontWeight.Bold)
    val italicRegularFont = Font(
        Res.font.inter_italic_variable,
        weight = FontWeight.Normal,
        style = FontStyle.Italic,
    )
    val italicMediumFont = Font(
        Res.font.inter_italic_variable,
        weight = FontWeight.Medium,
        style = FontStyle.Italic,
    )
    val italicSemiBoldFont = Font(
        Res.font.inter_italic_variable,
        weight = FontWeight.SemiBold,
        style = FontStyle.Italic,
    )
    val italicBoldFont = Font(
        Res.font.inter_italic_variable,
        weight = FontWeight.Bold,
        style = FontStyle.Italic,
    )

    return remember(
        regularFont,
        mediumFont,
        semiBoldFont,
        boldFont,
        italicRegularFont,
        italicMediumFont,
        italicSemiBoldFont,
        italicBoldFont,
    ) {
        FontFamily(
            regularFont,
            mediumFont,
            semiBoldFont,
            boldFont,
            italicRegularFont,
            italicMediumFont,
            italicSemiBoldFont,
            italicBoldFont,
        )
    }
}

@Composable
fun rememberPokedexTypography(): Typography {
    val pokedexFontFamily = rememberPokedexFontFamily()

    return remember(pokedexFontFamily) {
        Typography(
            displayLarge = TextStyle(
                fontFamily = pokedexFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 40.sp,
        lineHeight = 48.sp,
        letterSpacing = (-0.8).sp,
    ),
    displayMedium = TextStyle(
                fontFamily = pokedexFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 36.sp,
        lineHeight = 44.sp,
        letterSpacing = (-0.72).sp,
    ),
    displaySmall = TextStyle(
                fontFamily = pokedexFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
        lineHeight = 40.sp,
        letterSpacing = (-0.64).sp,
    ),

    headlineLarge = TextStyle(
                fontFamily = pokedexFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
        lineHeight = 40.sp,
        letterSpacing = (-0.64).sp,
    ),
    headlineMedium = TextStyle(
                fontFamily = pokedexFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp,
        lineHeight = 34.sp,
        letterSpacing = (-0.28).sp,
    ),
    headlineSmall = TextStyle(
                fontFamily = pokedexFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp,
        lineHeight = 32.sp,
        letterSpacing = (-0.12).sp,
    ),

    titleLarge = TextStyle(
                fontFamily = pokedexFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        lineHeight = 28.sp,
    ),
    titleMedium = TextStyle(
                fontFamily = pokedexFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        lineHeight = 24.sp,
    ),
    titleSmall = TextStyle(
                fontFamily = pokedexFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
    ),

    bodyLarge = TextStyle(
                fontFamily = pokedexFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
    ),
    bodyMedium = TextStyle(
                fontFamily = pokedexFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
    ),
    bodySmall = TextStyle(
                fontFamily = pokedexFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
    ),

    labelLarge = TextStyle(
                fontFamily = pokedexFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        lineHeight = 20.sp,
    ),
    labelMedium = TextStyle(
                fontFamily = pokedexFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 16.sp,
    ),
    labelSmall = TextStyle(
                fontFamily = pokedexFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
    ),
        )
    }
}
