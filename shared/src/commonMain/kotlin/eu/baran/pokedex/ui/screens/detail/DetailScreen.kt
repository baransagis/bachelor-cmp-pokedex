package eu.baran.pokedex.ui.screens.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cmppokedex.shared.generated.resources.Res
import cmppokedex.shared.generated.resources.detail_background
import cmppokedex.shared.generated.resources.ic_arrow_back
import eu.baran.pokedex.data.models.ui.PokemonBaseStatsUi
import eu.baran.pokedex.data.models.ui.PokemonDetailUi
import eu.baran.pokedex.ui.common.LoadingView
import eu.baran.pokedex.ui.pokemonImageRes
import eu.baran.pokedex.ui.pokemonTypeColor
import eu.baran.pokedex.ui.screens.common.TypeChip
import eu.baran.pokedex.ui.theme.AppTheme
import eu.baran.pokedex.ui.toNumberText
import kotlinx.collections.immutable.persistentListOf
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun DetailScreen(
    id: Int,
    onNavigateUp: () -> Unit,
    viewModel: DetailViewModel = koinViewModel(),
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    LaunchedEffect(id) {
        viewModel.loadPokemonDetail(id)
    }
    state.pokemon?.let {
        DetailView(pokemon = it, onNavigateUp = onNavigateUp)
    } ?: run {
        LoadingView()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DetailView(pokemon: PokemonDetailUi, onNavigateUp: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "${pokemon.name} ${pokemon.id.toNumberText()}") },
                navigationIcon = {
                    IconButton(onClick = onNavigateUp) {
                        Icon(
                            painter = painterResource(Res.drawable.ic_arrow_back),
                            modifier = Modifier.semantics {
                                testTag = "detailNavigateUp"
                            },
                            contentDescription = "Zurück navigieren"
                        )
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(it)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box {
                Image(
                    modifier = Modifier.size(248.dp),
                    painter = painterResource(Res.drawable.detail_background),
                    contentDescription = null
                )

                Image(
                    modifier = Modifier.size(248.dp),
                    painter = painterResource(pokemonImageRes(pokemon.id)),
                    contentDescription = null
                )
            }
            Spacer(Modifier.height(8.dp))
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                pokemon.types.forEachIndexed { index, type ->
                    val color = pokemonTypeColor(type)
                    TypeChip(
                        label = type.uppercase(),
                        textColor = Color.White,
                        backgroundColor = color,
                    )
                    if (index != pokemon.types.lastIndex) {
                        Spacer(Modifier.width(8.dp))
                    }
                }
            }
            Spacer(Modifier.height(32.dp))
            Text(
                text = pokemon.description,
                style = AppTheme.typography.bodyLarge,
                color = AppTheme.colors.material.onSurface,
            )
            Spacer(Modifier.height(32.dp))

            Row(Modifier.fillMaxWidth()) {
                AttributeCard(
                    modifier = Modifier.fillMaxWidth().weight(0.5f),
                    label = "Größe",
                    value = "${pokemon.heightMeters}m"
                )
                Spacer(
                    Modifier
                        .width(8.dp)
                )
                AttributeCard(
                    modifier = Modifier.fillMaxWidth().weight(0.5f),
                    label = "Gewicht",
                    value = "${pokemon.weightKg}kg"
                )
            }
            Spacer(Modifier.height(8.dp))
            AttributeCard(
                modifier = Modifier.fillMaxWidth(),
                label = "Kategorie",
                value = pokemon.genus
            )

            Spacer(Modifier.height(32.dp))

            Header(modifier = Modifier.fillMaxWidth(), label = "Fähigkeiten")
            Spacer(Modifier.height(8.dp))
            FlowRow(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                pokemon.abilities.forEach { ability ->
                    Text(
                        modifier = Modifier
                            .background(
                                color = AppTheme.colors.material.surfaceContainerHighest,
                                shape = RoundedCornerShape(999.dp)
                            )
                            .padding(vertical = 4.dp, horizontal = 8.dp),
                        text = ability,
                        style = AppTheme.typography.titleMedium,
                        color = AppTheme.colors.material.onSurface,
                    )
                }
                pokemon.abilities.ifEmpty {
                    Text(
                        text = "Keine Fähigkeiten vorhanden",
                        style = AppTheme.typography.bodyMedium,
                        color = AppTheme.colors.material.onSurfaceVariant,
                    )
                }
            }
            Spacer(Modifier.height(32.dp))
            BaseStats(modifier = Modifier, baseStats = pokemon.baseStats)
        }
    }
}

@Preview
@Composable
private fun DetailPreview() {
    AppTheme {
        DetailView(
            pokemon = PokemonDetailUi(
                id = 1,
                name = "Bisasam",
                types = persistentListOf("Pflanze", "Gift"),
                heightDm = 7,
                weightHg = 69,
                heightMeters = 0.7,
                weightKg = 6.9,
                abilities = persistentListOf("Notdünger", "Chlorophyll"),
                baseStats = PokemonBaseStatsUi(
                    hp = 45,
                    attack = 49,
                    defense = 49,
                    specialAttack = 65,
                    specialDefense = 65,
                    speed = 45
                ),
                genus = "Samen-Pokémon",
                description = "Dieses Pokémon trägt von Geburt an einen Samen auf dem Rücken, der mit ihm keimt und wächst.",
                color = "green",
                habitat = "grassland"
            ), onNavigateUp = {})
    }
}

@Composable
private fun AttributeCard(
    modifier: Modifier = Modifier,
    label: String,
    value: String,
) {
    Card(modifier = modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = label.uppercase(),
                style = AppTheme.typography.titleMedium,
                color = AppTheme.colors.material.onSurfaceVariant,
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = value,
                style = AppTheme.typography.titleLarge,
                color = AppTheme.colors.material.onSurface,
            )
        }
    }
}

@Composable
private fun BaseStats(modifier: Modifier = Modifier, baseStats: PokemonBaseStatsUi) {
    val statColors = AppTheme.colors.extraColors.stat

    Column(modifier = modifier.fillMaxWidth()) {
        Header(label = "Artenspezifische Stärken")
        Spacer(Modifier.height(16.dp))
        BaseStatItem(label = "kp", value = baseStats.hp, color = statColors.hp)
        Spacer(Modifier.height(16.dp))
        BaseStatItem(label = "angriff", value = baseStats.attack, color = statColors.attack)
        Spacer(Modifier.height(16.dp))
        BaseStatItem(label = "verteidigung", value = baseStats.defense, color = statColors.defense)
        Spacer(Modifier.height(16.dp))
        BaseStatItem(label = "Spezial-Angriff", value = baseStats.specialAttack, color = statColors.specialAttack)
        Spacer(Modifier.height(16.dp))
        BaseStatItem(label = "Spezial-Verteidigung", value = baseStats.specialDefense, color = statColors.specialDefense)
        Spacer(Modifier.height(16.dp))
        BaseStatItem(label = "Initiative", value = baseStats.speed, color = statColors.speed)
    }
}

@Composable
private fun BaseStatItem(label: String, value: Int, color: Color) {
    Column(Modifier.fillMaxWidth()) {
        Row(Modifier.fillMaxWidth()) {
            Text(
                text = label.uppercase(),
                style = AppTheme.typography.titleSmall,
                color = AppTheme.colors.material.onSurfaceVariant,
            )
            Spacer(Modifier.weight(1f))
            Text(
                text = value.toString(),
                style = AppTheme.typography.titleSmall,
                color = AppTheme.colors.material.onSurface,
            )
        }
        Spacer(Modifier.height(6.dp))
        val progress = remember(value) { (value/255f).coerceIn(0f, 1f) }
        LinearProgressIndicator(
            modifier = Modifier.fillMaxWidth(),
            progress = { progress },
            color = color,
            trackColor = AppTheme.colors.extraColors.statTrack,
            drawStopIndicator = {},
            gapSize = 0.dp,
        )
    }
}

@Composable
private fun Header(modifier: Modifier = Modifier, label: String) {
    Text(
        modifier = modifier,
        text = label,
        style = AppTheme.typography.titleLarge,
        color = AppTheme.colors.material.onSurface,
    )
}
