import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNoteDialog(onDismiss: () -> Unit, addComment: (String) -> Unit) {
    val noteText = remember { mutableStateOf("") }  // TextField için durum değişkeni

    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = {
            Text(text = "Bir not ekleyin")
        },
        text = {
            Column {
                OutlinedTextField(
                    value = noteText.value,
                    onValueChange = { noteText.value = it },
                    label = { Text("Enter your note") },
                    modifier = Modifier
                        .fillMaxWidth()


                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    // Burada notu kaydedebilirsiniz
                    addComment(noteText.value)
                    onDismiss()
                }
            ) {
                Text("Save")
            }
        },
        dismissButton = {
            Button(
                onClick = { onDismiss() }
            ) {
                Text("Cancel")
            }
        }
    )
}
