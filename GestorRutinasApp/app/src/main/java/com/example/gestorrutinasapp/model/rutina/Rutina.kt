package com.example.gestorrutinasapp.model.rutina

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.gestorrutinasapp.model.Days

@Entity("Rutina", indices = [Index(value = ["name"], unique = true)])
data class Rutina (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "name")
    @NonNull
    val name: String,

    @ColumnInfo(name = "day")
    val day: Days,


    ) {

}
