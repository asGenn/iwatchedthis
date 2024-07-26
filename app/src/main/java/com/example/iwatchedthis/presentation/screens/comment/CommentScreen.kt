package com.example.iwatchedthis.presentation.screens.comment

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.iwatchedthis.data.source.local.entitiy.MoviesComment
import com.example.iwatchedthis.ui.theme.CuteCrab
import com.example.iwatchedthis.ui.theme.HeiSeBlack

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommentScreen(modifier: Modifier = Modifier) {
    val viewModel: CommentsViewModel = hiltViewModel()
    val comments by viewModel.comments.collectAsState()
    var selectedComment by remember { mutableStateOf<MoviesComment?>(null) }
    var isDialogVisible by remember { mutableStateOf(false) }

    if (isDialogVisible && selectedComment != null) {
        CustomDialog(
            note = selectedComment!!.comment,
            onDismiss = {
                isDialogVisible = false
                selectedComment = null
            }
        )
    }
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(
                    text = "Comments",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineMedium,
                    color = HeiSeBlack,
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                )
            })

        }
    ) { paddingValues ->
        LazyColumn(
            contentPadding = PaddingValues(bottom = 80.dp),
            modifier = modifier.padding(paddingValues)
        ) {
            items(comments) { comment ->
                Card(
                    onClick = {
                        selectedComment = comment
                        isDialogVisible = true
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 5.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = HeiSeBlack)
                            .padding(16.dp)
                    ) {
                        Text(text = comment.movieName, color = Color.White)
                        Text(
                            text = comment.comment,
                            color = CuteCrab,
                            style = MaterialTheme.typography.bodyLarge,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
        }

    }


}

@Composable
private fun CustomDialog(modifier: Modifier = Modifier, note: String, onDismiss: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        Column(
            modifier = modifier
                .background(Color.White)
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = note,
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}
