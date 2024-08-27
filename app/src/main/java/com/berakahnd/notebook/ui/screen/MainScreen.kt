package com.berakahnd.notebook.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.berakahnd.notebook.data.local.Note
import com.berakahnd.notebook.ui.viewmodel.MainViewModel
import com.berakahnd.notebook.util.Tools
import java.text.SimpleDateFormat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    mainViewModel : MainViewModel,
    onUpsetClick : () -> Unit
){
    val gridState = rememberLazyStaggeredGridState()
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = "Notebook")
            })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { onUpsetClick() } ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }
    ) { paddingValues ->
        LazyVerticalStaggeredGrid(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            columns =  StaggeredGridCells.Fixed(2),
            state = gridState,
            contentPadding = PaddingValues(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalItemSpacing = 8.dp
        ) {
            items(mainViewModel.uiState.value.data) { item ->
                GridItem(item,  onDetailsClick = {
                    onUpsetClick()
                    mainViewModel.shareNote(item)
                })
            }
        }

    }

}
@SuppressLint("SimpleDateFormat")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GridItem(item: Note, onDetailsClick : () -> Unit) {
    val formatter = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
    val formattedDate: String = formatter.format(item.date)
    Card(colors = CardDefaults.cardColors(
        contentColor = Color.Black,
        containerColor = Tools.colorFromHex(item.color)
    ),onClick = {onDetailsClick() }) {
        Column(modifier = Modifier
            //.fillMaxSize()
            .padding(16.dp)) {
            Box(modifier = Modifier){
                Text(text = item.text)
            }
            Spacer(modifier = Modifier.size(16.dp))
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = formattedDate,
                    textAlign = TextAlign.Right,
                    fontSize = MaterialTheme.typography.bodySmall.fontSize,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}