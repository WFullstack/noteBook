package com.berakahnd.notebook.ui.viewmodel
import com.berakahnd.notebook.data.local.Note

data class MainUiState(
    val isLoading : Boolean = false,
    val data : List<Note> = emptyList(),
    val errorMessage : String = ""
)