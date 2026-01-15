package com.example.connecttointernet.ui

import android.media.MediaPlayer
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.connecttointernet.network.PokeData

@Composable
fun ConnectScreen (
    pokemones: List<PokeData>
) {

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(pokemones) { pokemon ->
            PokemonCard(pokemon)
        }
    }
}

@Composable
fun PokemonCard(pokemon: PokeData) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween // clave
        ) {
            // Texto a la izquierda
            Column {
                Row {
                    Text(
                        text = "#" + pokemon.id.toString(),
                        fontWeight = FontWeight.ExtraBold
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = pokemon.name.replaceFirstChar { it.uppercase() },
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                }

                Row {
                    PokemonTypesChips(pokemon)
                }
            }

            // Imagen a la derecha
            PokemonImage(pokemon)
        }
    }
}

@Composable
fun BasicChip(
    type: String
) {
    AssistChip(
        onClick = {},
        label = {Text(text = type)},
        enabled = false,
        shape = RoundedCornerShape(16.dp)
    )
}

@Composable
fun PokemonTypesChips(pokemon: PokeData) {
    FlowRow(
        modifier = Modifier.padding(8.dp)
    ) {
        pokemon.types.forEach { tipo ->
            BasicChip(tipo.type.name.replaceFirstChar { it.uppercase() })

            Spacer(modifier = Modifier.padding(4.dp))
        }
    }
}

@Composable
fun rememberPokemonCryPlayer(url: String?): () -> Unit {
    // Recuerda el sonido de cada pokemon, además de añadir la utilidad de emitir el sonido
    var mediaPlayer by remember { mutableStateOf<MediaPlayer?>(null) }

    // Ejecuta un unico valor, cuando cambia se limpia el valor anterior y lanza el nuevo
    DisposableEffect(url) {
        // se ejecuta cuando cambia o se libera el valor asignado
        onDispose {
            // libera el sonido del media player
            mediaPlayer?.release()
            mediaPlayer = null
        }
    }

    // devuelve una función qeu solo se ejecuta cuando haces click en la imagen
    return let@{
        // evita errores por si no hay ningún url con el sonido
        if (url.isNullOrBlank()) return@let

        // libera el sonido
        mediaPlayer?.release()

        // crea la nueva instancia de media Player
        mediaPlayer = MediaPlayer().apply {
            // indica el sonido a reproducir
            setDataSource(url)
            // prepara el sonido en segundo plano
            prepareAsync()
            // ejecuta el sonido
            setOnPreparedListener {
                start()
            }
        }
    }
}

@Composable
fun PokemonImage(pokemon: PokeData) {
    // guarda la funcion de reproducción de sonido
    val playCry = rememberPokemonCryPlayer(pokemon.cries.latest)

    AsyncImage(
        model = pokemon.sprites.other.officialArtwork.url,
        contentDescription = pokemon.name,
        modifier = Modifier
            .size(80.dp)
            .clickable {
                playCry()
            }
    )
}
