package com.berakahnd.notebook.ui.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.RemoveCircleOutline
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.berakahnd.notebook.data.local.Note
import com.berakahnd.notebook.ui.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpsetScreen(
    mainViewModel : MainViewModel,
    onBackClick : () -> Unit
){
    var note  = mainViewModel.note

    var text by rememberSaveable { mutableStateOf(note.text) }

    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(navigationIcon = {
                IconButton(onClick = onBackClick ) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                }
            },title = {
                Text(text = "Notebook")
            }, actions = {
                if (note.id > 0){
                    IconButton(onClick = {
                        mainViewModel.deleteNote(note)
                        Toast.makeText(context, "note deleted!", Toast.LENGTH_LONG).show()
                        onBackClick()
                    }) {
                        Icon(imageVector = Icons.Default.RemoveCircleOutline, contentDescription = null)
                    }
                }
            })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                note = if(note.id > 0){
                    Note(id = note.id, text = text)
                }else{
                    Note(text = text)
                }
                mainViewModel.upSetNote(note)
                Toast.makeText(context, "note saved!", Toast.LENGTH_LONG).show()
            }) {
                Icon(imageVector = Icons.Default.Check, contentDescription = null)
            }
        }
    ) { paddingValues ->
        Column(modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()) {
            TextField(
                modifier = Modifier.fillMaxSize(),
                colors = TextFieldDefaults.textFieldColors(
                    disabledTextColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                value = text,
                onValueChange = { newText ->
                    text = newText
                },
                placeholder = {
                    Text(text = "Write somethings...")
                }
            )
        }
    }
}