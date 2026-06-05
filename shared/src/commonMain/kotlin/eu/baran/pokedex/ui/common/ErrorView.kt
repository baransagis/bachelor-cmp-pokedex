package eu.baran.pokedex.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import eu.baran.pokedex.ui.theme.AppTheme

@Composable
fun ErrorView(modifier: Modifier = Modifier.fillMaxSize(), onRetry: () -> Unit) {
    Column(modifier = modifier.background(AppTheme.colors.material.background).padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Text(
            text = "Das Laden der Daten ist fehlgeschlagen.",
            color = AppTheme.colors.material.onSurface,
            textAlign = TextAlign.Center
            )
        Spacer(Modifier.height(16.dp))
        Button(modifier = Modifier.fillMaxWidth(), onClick = onRetry) {
            Text(text = "Erneut versuchen")
        }
    }
}