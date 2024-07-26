package com.example.iwatchedthis.presentation.screens.details

import AddNoteDialog
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.iwatchedthis.R
import com.example.iwatchedthis.data.source.network.model.MovieDetail
import com.example.iwatchedthis.ui.theme.CuteCrab
import com.example.iwatchedthis.ui.theme.HeiSeBlack
import com.example.iwatchedthis.ui.theme.Orange
import kotlinx.coroutines.flow.collectLatest

@Composable
fun DetailsScreen(movieId: String) {
    val viewModel: DetailsScreenViewModel = hiltViewModel()
    val movieDetail = viewModel.movieDetail.collectAsState().value
    val isFavorite by viewModel.isFavorite.collectAsState()
    val isWatched by viewModel.isWatched.collectAsState()
    val context = LocalContext.current
    val showDialog = remember {
        mutableStateOf(false)
    }
    LaunchedEffect(key1 = viewModel.showCommentDialogChannel) {

        viewModel.showCommentDialogChannel.collectLatest { message ->
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }

    }
    DetailScreenMainContent(
        movieDetail = movieDetail,
        showDialog = showDialog,
        viewModel = viewModel,
        isFavorite = isFavorite,
        isWatched = isWatched
    )
    if (showDialog.value) {
        AddNoteDialog(
            onDismiss = {
                showDialog.value = false
            },
            addComment = { comment ->
                viewModel.addCommentToDb(
                    movieId,
                    comment,
                    movieName = movieDetail?.result?.title ?: ""
                )
            }

        )
    }


}

@Composable
fun DetailScreenMainContent(
    movieDetail: MovieDetail?,
    modifier: Modifier = Modifier,
    showDialog: MutableState<Boolean>,
    viewModel: DetailsScreenViewModel,
    isFavorite: Boolean,
    isWatched: Boolean,

    ) {

    if (movieDetail == null) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
        return
    } else {

        Scaffold(
            bottomBar = {
                Column {

                    OutlinedButton(
                        onClick = {
                            showDialog.value = true

                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(HeiSeBlack.copy(alpha = 0.99f))
                            .padding(horizontal = 16.dp),
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent,
                            contentColor = Orange,


                            )
                    ) {
                        Text(text = "Add note", style = MaterialTheme.typography.titleMedium)

                    }
                    Button(
                        onClick = {
                            viewModel.toggleWatched()
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(HeiSeBlack.copy(alpha = 0.99f))
                            .padding(16.dp),
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = CuteCrab)
                    ) {
                        Text(
                            text = if (!isWatched) "I watched this" else "I didn't watch this",
                            style = MaterialTheme.typography.titleMedium
                        )

                    }

                }

            }
        ) { innerPading ->
            val scrollState = rememberScrollState()
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(innerPading)
                    .verticalScroll(scrollState)
                    .background(color = HeiSeBlack)


            ) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = HeiSeBlack)
                ) {
                    TopImage(movieDetail = movieDetail)
                }
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = HeiSeBlack.copy(alpha = 0.99f))
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)

                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = movieDetail.result.title,
                                color = Color.White,
                                style = MaterialTheme.typography.headlineMedium,
                                modifier = Modifier.weight(1f)
                            )

                            Icon(
                                imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                                contentDescription = null,
                                tint = Orange,
                                modifier = Modifier
                                    .size(40.dp)
                                    .weight(0.2f)
                                    .clickable {
                                        viewModel.toggleFavorite()
                                    }
                            )


                        }

                        Spacer(modifier = Modifier.height(10.dp))
                        MovieDetail(movieDetail = movieDetail)
                        Spacer(modifier = Modifier.height(15.dp))
                        Row(
                            modifier = Modifier
                                .align(Alignment.Start)
                                .fillMaxWidth(),
                            // verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceAround


                        ) {
                            MovieRating(
                                color = Orange,
                                drawbleId = R.drawable.imdb_logo,
                                source = movieDetail.result.ratings[0].source,
                                rating = movieDetail.result.ratings[0].value
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            MovieRating(

                                color = Color.Red,
                                drawbleId = R.drawable.rotten_tomatoes_logo,
                                source = movieDetail.result.ratings[1].source,
                                rating = movieDetail.result.ratings[1].value
                            )

                        }
                        Spacer(modifier = Modifier.height(15.dp))
                        HorizontalDivider(color = Color.Gray)
                        Spacer(modifier = Modifier.height(15.dp))
                        Text(
                            text = movieDetail.result.plot,
                            color = Color.White,
                            style = MaterialTheme.typography.bodyLarge
                        )


                    }


                }

            }

        }

    }


}


@Composable
fun MovieRating(
    color: Color,
    drawbleId: Int,
    source: String,
    rating: String,

    ) {
    Image(
        painter = painterResource(id = drawbleId),
        contentDescription = null,
        modifier = Modifier.size(50.dp)
    )
    Spacer(modifier = Modifier.width(5.dp))
    Column {
        Text(
            text = rating,
            color = color,
            style = MaterialTheme.typography.titleMedium,
        )
        Text(
            text = source,
            color = Color.White,
            style = MaterialTheme.typography.labelMedium,
        )

    }

}

@Composable
fun MovieDetail(movieDetail: MovieDetail) {
    val color = Color.Green
    Row {
        Text(
            text = movieDetail.result.type,
            color = color,
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.width(10.dp))
        VerticalDivider(
            modifier = Modifier
                .height(18.dp),
            thickness = 2.dp,
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = movieDetail.result.released,
            color = color,
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.width(10.dp))
        VerticalDivider(
            modifier = Modifier
                .height(18.dp),
            thickness = 2.dp,
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = movieDetail.result.runtime,
            color = color,
            style = MaterialTheme.typography.bodyLarge
        )
    }

}

@Composable
fun VerticalDiveder() {
    Spacer(modifier = Modifier.width(10.dp))
    HorizontalDivider(
        modifier = Modifier
            .height(18.dp)  //fill the max height
            .width(2.dp),
        color = Color.White
    )
    Spacer(modifier = Modifier.width(10.dp))
}

@Composable
fun TopImage(movieDetail: MovieDetail, modifier: Modifier = Modifier) {
    val imageState = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current).data(movieDetail.result.poster)
            .size(Size.ORIGINAL).build()
    ).state

    if (imageState is AsyncImagePainter.State.Loading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()

        }
    } else if (imageState is AsyncImagePainter.State.Success) {
        Image(
            painter = imageState.painter,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .clip(shape = RoundedCornerShape(bottomEnd = 15.dp, bottomStart = 15.dp)),
            contentScale = ContentScale.FillBounds,


            )

    }


}