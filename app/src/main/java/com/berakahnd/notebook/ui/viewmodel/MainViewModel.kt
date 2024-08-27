package com.berakahnd.notebook.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.berakahnd.notebook.data.local.Note
import com.berakahnd.notebook.data.repository.RepositoryDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repo : RepositoryDao
): ViewModel() {

    val uiState = mutableStateOf(MainUiState())
    var note by mutableStateOf(Note())

    init {
        getAllNotes()
    }
    fun shareNote(newNote : Note){
        note = newNote
    }

    fun getAllNotes(){
        uiState.value = uiState.value.copy(isLoading = true)
        viewModelScope.launch {
            try {
                repo.getAllNotes().collect{ result ->
                    uiState.value = uiState.value.copy(data = result)
                }
            }catch (e : Exception){
                uiState.value = uiState.value.copy(errorMessage = e.message.toString())
            }

        }
    }
    fun upSetNote(note : Note){
        viewModelScope.launch {
            repo.upSetNote(note)
        }
    }
    fun deleteNote(note : Note){
        viewModelScope.launch {
            repo.deleteNote(note)
        }
    }
}