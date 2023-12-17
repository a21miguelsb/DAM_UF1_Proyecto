package com.example.gestorrutinasapp.model.exercice

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ExerciceDao {
    @Query("SELECT * FROM exercice ORDER BY name DESC ")
    fun getAllExercices(): Flow<List<Exercice>>

    @Insert
    suspend fun insert(exercice: Exercice)
    @Delete
    suspend fun delete(exercice: Exercice)
    @Query("SELECT * from exercice WHERE id = :id")
    fun getExerciceById(id: Int): Flow<Exercice>

    @Query("SELECT * from exercice WHERE id_routine = :id")
    suspend fun getExerciceByRoutine(id: Int): List<Exercice>
}