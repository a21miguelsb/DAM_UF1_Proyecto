package com.example.gestorrutinasapp.model.dieta

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class Dieta(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int=0,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "diet_info")
    val diet_info: String,
)
