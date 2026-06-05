package eu.baran.pokedex.ui.screens.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import eu.baran.pokedex.data.models.ui.PokemonListItemUi
import eu.baran.pokedex.permissions.LocalNetworkAccessStatus
import eu.baran.pokedex.permissions.rememberLocalNetworkAccessGate
import eu.baran.pokedex.ui.common.ErrorView
import eu.baran.pokedex.ui.common.LoadingView
import eu.baran.pokedex.ui.pokemonImageRes
import eu.baran.pokedex.ui.pokemonTypeColor
import eu.baran.pokedex.ui.screens.common.TypeChip
import eu.baran.pokedex.ui.theme.AppTheme
import eu.baran.pokedex.ui.toNumberText
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ListScreen(onPokemonClick: (id: Int) -> Unit, viewModel: ListViewModel = koinViewModel()) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val accessGate = rememberLocalNetworkAccessGate()

    LaunchedEffect(Unit) {
        accessGate.requestAccess()
    }

    LaunchedEffect(accessGate.status) {
        if (accessGate.status == LocalNetworkAccessStatus.Ready) {
            viewModel.loadPokemon()
        }
    }

    when {
        state.pokemonList.isNotEmpty() -> {
            ListView(
                pokemonList = state.pokemonList,
                onPokemonClick = onPokemonClick
            )
        }
        state.isError -> {
            ErrorView(onRetry = {
                viewModel.loadPokemon()
            })
        }
        else -> {
            LoadingView()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ListView(
    pokemonList: ImmutableList<PokemonListItemUi>,
    onPokemonClick: (id: Int) -> Unit,
) {
    Scaffold(
        topBar = {
            Column {
                TopAppBar(title = { Text(text = "Pokédex") })
                HorizontalDivider(color = AppTheme.colors.extraColors.divider)
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
        ) {
            itemsIndexed(pokemonList) { index, pokemon ->
                ListItem(
                    id = pokemon.id,
                    name = pokemon.name,
                    types = pokemon.types,
                    onClick = onPokemonClick
                )
                if (index != pokemonList.lastIndex) {
                    HorizontalDivider(color = AppTheme.colors.extraColors.divider)
                }
            }
        }
    }
}

@Preview
@Composable
private fun ListPreview() {
    AppTheme {
        ListView(
            pokemonList = persistentListOf(),
            onPokemonClick = {})
    }
}

@Composable
private fun ListItem(
    id: Int,
    name: String,
    types: ImmutableList<String>,
    onClick: (id: Int) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = {
                onClick(id)
            })
            .padding(16.dp), verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = id.toNumberText(),
            style = AppTheme.typography.headlineSmall,
            color = AppTheme.colors.material.onSurfaceVariant,
        )
        Spacer(Modifier.width(24.dp))
        Column {
            Text(
                text = name,
                style = AppTheme.typography.titleLarge,
                color = AppTheme.colors.material.onSurface,
            )
            Spacer(Modifier.height(4.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                types.forEach {
                    val color = pokemonTypeColor(it)
                    TypeChip(label = it, textColor = color, backgroundColor = color.copy(0.25f))
                }
            }
        }
        Spacer(Modifier.weight(1f))
        Image(
            modifier = Modifier.size(64.dp),
            painter = painterResource(pokemonImageRes(id)),
            contentDescription = null,
        )
    }
}

