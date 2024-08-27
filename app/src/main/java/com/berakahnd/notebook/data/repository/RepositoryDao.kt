package com.berakahnd.notebook.data.repository

import com.berakahnd.notebook.data.local.Note
import com.berakahnd.notebook.data.local.NoteDao
import javax.inject.Inject

class RepositoryDao @Inject constructor(
    private  val dao : NoteDao
){
    suspend fun upSetNote(note: Note)  = dao.addNote(note)
    suspend fun deleteNote(note: Note)  = dao.deleteNote(note)
    fun getAllNotes()  = dao.getAllNotes()
}