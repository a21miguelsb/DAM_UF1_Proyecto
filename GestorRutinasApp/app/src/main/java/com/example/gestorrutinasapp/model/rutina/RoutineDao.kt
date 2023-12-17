package com.example.gestorrutinasapp.model.rutina

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RoutineDao {
    @Query("SELECT * FROM Rutina")
    suspend fun getAllRoutines(): List<Rutina>

    @Query("SELECT * FROM Rutina Where name like :name ")
    suspend fun getRutinaByName(name: String):Rutina

    @Insert
    suspend fun insert(rutina: Rutina)

    @Delete
    suspend fun delete(rutina: Rutina)

}