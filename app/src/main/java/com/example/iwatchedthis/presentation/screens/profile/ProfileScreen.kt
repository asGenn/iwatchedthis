@file:OptIn(ExperimentalFoundationApi::class)

package com.example.iwatchedthis.presentation.screens.profile

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.iwatchedthis.data.source.local.entitiy.FavoriteMovies
import com.example.iwatchedthis.data.source.local.entitiy.WatchedMovies
import com.example.iwatchedthis.ui.theme.HeiSeBlack


@Composable
fun ProfileScreen(modifier: Modifier = Modifier, onItemClick: (String) -> Unit) {
    val viewModel: ProfileScreenViewModel = hiltViewModel()
    val userName by viewModel.userName.collectAsState()
    val isInEditMode by viewModel.isInEditMode.collectAsState()
    val favoriteMovies by viewModel.favoriteMovies.collectAsState()
    val watchedMovies by viewModel.watchedMovies.collectAsState()



    LaunchedEffect(Unit) {
        viewModel.refreshItems()
    }
    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {

        NameField(
            userName = userName,
            isInEditMode = isInEditMode,
            onValueChange = { newName -> viewModel.onUserNameChange(newName) }
        )
        Spacer(modifier = Modifier.height(16.dp))

        EditMode(
            isInEditMode = isInEditMode,
            onEditModeToggle = {
                viewModel.onEditModeToggle()

            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(16.dp))
        CustomTabs(
            favoriteMovies = favoriteMovies,
            watchedMoviesList = watchedMovies,
            onItemClick = onItemClick
        )

    }
}


@Composable
fun FavoriteMoviesComp(
    modifier: Modifier = Modifier,
    favoriteMovies: FavoriteMovies,
    onItemClick: () -> Unit
) {

    val imageState = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current).data(favoriteMovies.poster)
            .size(Size.ORIGINAL).build(),
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
                .size(200.dp)
                .padding(8.dp)
                .clip(RoundedCornerShape(8.dp))
                .clickable {
                    onItemClick()

                },
            contentScale = ContentScale.Crop
        )
    }

}

@Composable
fun WatchedMovies(
    modifier: Modifier = Modifier,
    watchedMovies: WatchedMovies,
    onItemClick: (String) -> Unit
) {

    val imageState = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current).data(watchedMovies.poster)
            .size(Size.ORIGINAL).build(),
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
                .size(200.dp)
                .padding(8.dp)
                .clip(RoundedCornerShape(8.dp))
                .clickable { onItemClick(watchedMovies.imdbID) },
            contentScale = ContentScale.Crop
        )
    }


}

@Composable
fun NameField(
    modifier: Modifier = Modifier,
    userName: String,
    isInEditMode: Boolean,
    onValueChange: (String) -> Unit
) {
    TextField(
        value = userName,
        textStyle = MaterialTheme.typography.displaySmall.copy(
            color = HeiSeBlack
        ),
        onValueChange = onValueChange,
        trailingIcon = {
            if (isInEditMode) {
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = "Clear",
                    modifier = Modifier.clickable { onValueChange("") }
                )
            }
        },

        enabled = isInEditMode,
        shape = OutlinedTextFieldDefaults.shape,
        colors = OutlinedTextFieldDefaults.colors(
            disabledContainerColor = Color.Transparent,
            disabledBorderColor = Color.Transparent,
        ),
        modifier = modifier
    )
}


@Composable
fun EditMode(
    modifier: Modifier = Modifier,
    isInEditMode: Boolean,
    onEditModeToggle: () -> Unit
) {
    Button(onClick = onEditModeToggle, modifier = modifier) {
        Text(if (isInEditMode) "Save" else "Edit Profile")
    }
}

@Composable
fun CustomTabs(
    modifier: Modifier = Modifier,
    favoriteMovies: List<FavoriteMovies>,
    watchedMoviesList: List<WatchedMovies>,
    onItemClick: (String) -> Unit
) {
    var selectedTabIndex by rememberSaveable {
        mutableIntStateOf(0)
    }

    val tabItem = listOf(
        TabItem(
            title = "Favorites (${favoriteMovies.size})",
            unSelectedIcon = Icons.Default.FavoriteBorder,
            selectedIcon = Icons.Default.Favorite
        ),
        TabItem(
            title = "Watched (${watchedMoviesList.size})",
            unSelectedIcon = Icons.Default.Check,
            selectedIcon = Icons.Default.CheckCircle
        )
    )

    val pagerState = rememberPagerState(pageCount = {
        tabItem.size
    })
    LaunchedEffect(selectedTabIndex) {
        pagerState.animateScrollToPage(
            selectedTabIndex,
            animationSpec = spring(stiffness = Spring.StiffnessHigh)
        )

    }
    LaunchedEffect(pagerState.currentPage, pagerState.isScrollInProgress) {
        if (!pagerState.isScrollInProgress) {
            selectedTabIndex = pagerState.currentPage
        }


    }
    Column {
        TabRow(selectedTabIndex = selectedTabIndex) {
            tabItem.forEachIndexed { index, tabItem ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = {
                        selectedTabIndex = index
                    },
                    text = { Text(text = tabItem.title) },
                    icon = {
                        Icon(
                            imageVector = if (selectedTabIndex == index) tabItem.selectedIcon else tabItem.unSelectedIcon,
                            contentDescription = tabItem.title
                        )
                    }
                )
            }
        }
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) { selectedTabIndex ->
            Box(
                modifier = Modifier.fillMaxSize(),


                ) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(
                        tabItem.size
                    ),
                    modifier = Modifier.padding(16.dp),

                    ) {
                    if (selectedTabIndex == 0) {
                        items(favoriteMovies.size) {
                            FavoriteMoviesComp(
                                favoriteMovies = favoriteMovies[it],
                                onItemClick = {
                                    onItemClick(favoriteMovies[it].imdbID)
                                }
                            )

                        }
                    } else if (selectedTabIndex == 1) {
                        items(watchedMoviesList.size) { idx ->
                            WatchedMovies(watchedMovies = watchedMoviesList[idx], onItemClick = {
                                onItemClick(watchedMoviesList[idx].imdbID)
                            })


                        }
                    }

                }

            }
        }


    }
}
