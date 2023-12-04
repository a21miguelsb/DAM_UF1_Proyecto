package com.example.gestorrutinasapp.model.exercice

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Exercice(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int=0,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "repetitions")
    val repetitions: Int,
    @ColumnInfo(name = "time")
    val time: Int,
    @ColumnInfo(name ="id_routine")
    val id_routine: Int)



