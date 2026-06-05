package eu.baran.pokedex.permissions

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable

enum class LocalNetworkAccessStatus {
    Blocked,
    Ready,
    Denied,
}

@Immutable
data class LocalNetworkAccessGate(
    val status: LocalNetworkAccessStatus,
    val requestAccess: () -> Unit,
)

@Composable
expect fun rememberLocalNetworkAccessGate(): LocalNetworkAccessGate
