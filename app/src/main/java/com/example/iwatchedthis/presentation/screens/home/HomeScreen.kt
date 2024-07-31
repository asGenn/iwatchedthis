package com.example.iwatchedthis.presentation.screens.home

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.iwatchedthis.data.source.network.model.TopMoviesItem
import com.example.iwatchedthis.presentation.screens.home.composable.CustomSearchBar
import com.example.iwatchedthis.ui.theme.HeiSeBlack
import com.example.iwatchedthis.ui.theme.Orange
import kotlinx.coroutines.flow.collectLatest

@Composable
fun HomeScreen(onDetailsButtonClick: (String) -> Unit, moviesViewModel: MoviesViewModel) {


    val moviesList = moviesViewModel.movies.collectAsState().value
    val context = LocalContext.current



    LaunchedEffect(key1 = moviesViewModel.showToastChannel) {
        moviesViewModel.showToastChannel.collectLatest { show ->
            if (show) {
                Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
            }
        }

    }
    if (moviesList.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        Column(
            modifier = Modifier
                .background(HeiSeBlack)
                .padding(top = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CustomSearchBar()
            Spacer(modifier = Modifier.size(10.dp))
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxSize()
                    .background(HeiSeBlack)


            ) {
                items(moviesList.size) {

                    CustomMovieBox(
                        movie = moviesList[it],
                        onDetailsButtonClick = onDetailsButtonClick
                    )

                }


            }


        }

    }


}

@Composable
fun CustomMovieBox(
    movie: TopMoviesItem,
    modifier: Modifier = Modifier,
    onDetailsButtonClick: (String) -> Unit
) {
    val imageState = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current).data(movie.image).size(Size.ORIGINAL)
            .build()
    ).state
    if (imageState is AsyncImagePainter.State.Loading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .height(200.dp)
                .width(200.dp),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else if (imageState is AsyncImagePainter.State.Success) {
        CustomCard(
            movie = movie,
            imageState = imageState,
            onDetailsButtonClick = onDetailsButtonClick
        )
    }


}

@Composable
fun CustomCard(
    movie: TopMoviesItem,
    imageState: AsyncImagePainter.State,
    modifier: Modifier = Modifier,
    onDetailsButtonClick: (String) -> Unit
) {
    Card(
        modifier = modifier
            .padding(10.dp)
            .fillMaxSize()
            .height(300.dp)
            .clickable {
                onDetailsButtonClick(movie.imdbid)
            },
        shape = RoundedCornerShape(15.dp),

        ) {
        Box(
            modifier = Modifier

                .clip(shape = RoundedCornerShape(15.dp))
        ) {
            CustomBackgroundImage(imageState = imageState)
            // Rank Bilgisi
            Text(
                text = "#${movie.rank}",
                color = Color.White,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight(FontWeight.Bold.weight),
                modifier = Modifier
                    .align(Alignment.TopStart) // Sol üst köşe
                    .background(Color.Black.copy(alpha = 0.6f)) // Arka plan rengi
                    .padding(4.dp) // Padding
                    .clip(RoundedCornerShape(4.dp)) // Köşeleri yuvarlama
                    .padding(4.dp)
            )
            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .background(Color(0xD9000000)) // Arka plan rengi
                    .padding(12.dp)
                    .padding(start = 8.dp, bottom = 8.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = movie.title,
                    color = Color.White,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(bottom = 4.dp)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.Bottom
                ) {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = null,
                        tint = Orange
                    )
                    Spacer(modifier = Modifier.size(2.dp))
                    Text(text = movie.rating.toString(), color = Color.White)
                    Text(text = " |", color = Color.White)
                    Spacer(modifier = Modifier.size(2.dp))
                    Text(
                        text = movie.genre.getOrElse(0) { "N/A" },
                        color = Color.White
                    ) // Tür bilgisi
                }
            }
        }
    }

}

@Composable
fun CustomBackgroundImage(imageState: AsyncImagePainter.State) {
    if (imageState is AsyncImagePainter.State.Success) {
        Image(
            painter = imageState.painter,
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )
    }

}