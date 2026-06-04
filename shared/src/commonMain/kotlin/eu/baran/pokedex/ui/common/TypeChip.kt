package eu.baran.pokedex.ui.screens.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import eu.baran.pokedex.ui.theme.AppTheme

@Composable
fun TypeChip(label: String, textColor:  Color, backgroundColor: Color) {
    Text(
        modifier = Modifier
            .background(backgroundColor, shape = RoundedCornerShape(999.dp))
            .padding(vertical = 2.dp, horizontal = 6.dp),
        text = label,
        style = AppTheme.typography.labelLarge.copy(color = textColor),
    )
}
