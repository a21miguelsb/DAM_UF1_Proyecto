package com.example.gestorrutinasapp.model.rutina

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import com.example.gestorrutinasapp.model.Days
import com.example.gestorrutinasapp.model.exercice.Exercice
import kotlinx.coroutines.flow.Flow

@Dao
interface RoutineDao {
    @Query("SELECT * FROM Rutina")
    fun getAllRoutines(): Flow<List<Rutina>>

    @Query("SELECT * FROM Rutina Where name= :name and day = :day ")
    suspend fun getRutinaByNameDay(name: String,day: Days):Rutina

    @Insert
    suspend fun insert(rutina: Rutina)
}