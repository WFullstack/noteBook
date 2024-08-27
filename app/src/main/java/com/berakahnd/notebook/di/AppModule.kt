package com.berakahnd.notebook.di

import android.content.Context
import androidx.room.Room
import com.berakahnd.notebook.data.local.NoteDao
import com.berakahnd.notebook.data.local.NoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context : Context) : NoteDatabase{
        return Room.databaseBuilder(
            context,
            NoteDatabase::class.java,
            "note.db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideDao(db : NoteDatabase) : NoteDao = db.getNoteDao()
}