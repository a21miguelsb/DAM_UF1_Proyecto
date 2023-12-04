package com.example.gestorrutinasapp.model.objetive

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class Objetive(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int=0,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "objetive_info")
    val objetive_info: String,
    )
