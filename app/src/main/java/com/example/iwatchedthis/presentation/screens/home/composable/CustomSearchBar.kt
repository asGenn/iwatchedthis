package com.example.iwatchedthis.presentation.screens.home.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.iwatchedthis.presentation.screens.details.VerticalDiveder
import com.example.iwatchedthis.ui.theme.HeiSeBlack


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomSearchBar(modifier: Modifier = Modifier) {
    val viewModel: CustomSearchBarViewModel = viewModel(factory = CustomSearchBarViewModelFactory())
    val queryText = viewModel.queryText.collectAsState().value
    val active = viewModel.isActive.collectAsState().value
    val movies = viewModel.movies.collectAsState().value
    SearchBar(
        query = queryText,
        onQueryChange = {
            viewModel.onQueryChange(it)

        },
        onSearch = {
            viewModel.getMoviesList(queryText)
            viewModel.onActiveChange(true)

        },
        active = active,
        onActiveChange = {
            viewModel.onActiveChange(it)
        },
        placeholder = {
            Text(text = "Search")
        },

        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = null
            )
        },
        trailingIcon = {
            if (active) {
                Icon(
                    imageVector = Icons.Filled.Clear,
                    contentDescription = null,
                    modifier = Modifier.clickable {
                        if (queryText.isNotEmpty()) {
                            viewModel.onQueryChange("")
                        } else {
                            viewModel.onActiveChange(false)

                        }
                    }
                )
            }
        }
    ) {
        LazyColumn {
            items(movies.result.size) {
                Card(
                    modifier = Modifier
                        .padding(10.dp)


                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = HeiSeBlack.copy(alpha = 0.9f)),

                        ) {
                        CustomImage(movies.result[it].Poster)
                        Spacer(modifier = Modifier.width(15.dp))
                        Column {
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = movies.result[it].title,
                                color = Color.White,
                                style = MaterialTheme.typography.titleMedium
                            )
                            Spacer(modifier = Modifier.height(5.dp))
                            Row {
                                Text(text = movies.result[it].Type, color = Color.White)
                                VerticalDiveder()
                                Text(text = movies.result[it].Year, color = Color.White)


                            }


                        }


                    }

                }

            }
        }

    }
}

@Composable
fun CustomImage(moviesUrl: String) {
    val imageState = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current).data(moviesUrl).size(
            Size.ORIGINAL
        ).build()
    ).state
    if (imageState is AsyncImagePainter.State.Loading) {
        Text(text = "Loading")
    } else if (imageState is AsyncImagePainter.State.Success) {
        Image(
            painter = imageState.painter,
            contentDescription = null,
            modifier = Modifier.size(100.dp),
            contentScale = ContentScale.FillWidth
        )
    }

}