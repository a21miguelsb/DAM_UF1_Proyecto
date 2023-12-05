package com.example.gestorrutinasapp.model.rutina

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.gestorrutinasapp.model.Days

@Dao
interface RoutineDao {
    @Query("SELECT * FROM Rutina")
    suspend fun getAllRoutines(): List<Rutina>

    @Query("SELECT * FROM Rutina Where name= :name ")
    suspend fun getRutinaByName(name: String):Rutina

    @Insert
    suspend fun insert(rutina: Rutina)
}