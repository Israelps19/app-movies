package com.example.movies.features.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movies.features.theme.MoviesAppTheme

@Composable
fun MovieGenreChip(
    genre: String,
    modifier: Modifier = Modifier
) {
    Surface (
        modifier = modifier,
        shape = MaterialTheme.shapes.large,
        color = MaterialTheme.colorScheme.primary
    ) {
        Text(
            text = genre,
            modifier = Modifier
                .padding(horizontal = 8.dp),
            style = MaterialTheme.typography.labelSmall
        )
    }
}

@Composable
@Preview
fun MovieGenreChipPreview(modifier: Modifier = Modifier) {
    MoviesAppTheme {
        MovieGenreChip(
            genre = "Action"
        )
    }
}