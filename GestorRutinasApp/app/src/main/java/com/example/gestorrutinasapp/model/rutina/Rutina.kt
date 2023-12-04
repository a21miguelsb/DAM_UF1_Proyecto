package com.example.gestorrutinasapp.model.rutina

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.gestorrutinasapp.model.Days
import com.example.gestorrutinasapp.model.exercice.Exercice
import javax.inject.Inject

@Entity()
data class Rutina (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,


    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "day")
    val day: Days,


    )