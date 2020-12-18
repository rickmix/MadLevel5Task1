package com.example.madlevel5taks1

import android.content.Context
import androidx.lifecycle.LiveData

class NoteRepository(context: Context) {
    private val noteDao: NoteDao

    init {
        val database = NotepadRoomDatabase.getDatabase(context)
        noteDao = database!!.noteDao()
    }

    fun getNotePad(): LiveData<Note?> {
        return noteDao.getNotePad()
    }

    suspend fun updateNotepad(note: Note) {
        noteDao.updateNote(note)
    }
}