package com.example.gestorrutinasapp.model.dieta

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface DietaDao {
    @Query("SELECT * FROM Dieta ORDER BY name DESC ")
    fun getAllDiets(): Flow<List<Dieta>>

    @Insert
    suspend fun insert(exercice: Dieta)


    @Query("SELECT * from dieta WHERE id = :id")
    fun getDietById(id: Int): Dieta

}