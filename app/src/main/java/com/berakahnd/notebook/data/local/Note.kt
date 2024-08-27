package com.berakahnd.notebook.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.berakahnd.notebook.util.Tools.lightColors
import java.util.Date

@Entity(tableName = "note_table")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val text : String ="",
    val date : Date = Date(),
    val color : String = lightColors.random()
)
