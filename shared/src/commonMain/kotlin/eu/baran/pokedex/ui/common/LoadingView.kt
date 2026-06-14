package eu.baran.pokedex.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import eu.baran.pokedex.ui.theme.AppTheme

@Composable
fun LoadingView() {
    Box(modifier = Modifier.fillMaxSize().background(AppTheme.colors.material.background), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}