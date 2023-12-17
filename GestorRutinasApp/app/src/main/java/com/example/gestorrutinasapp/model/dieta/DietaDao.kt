package com.example.gestorrutinasapp.model.dieta

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DietaDao {
    @Query("SELECT * FROM Dieta ORDER BY name DESC ")
    suspend fun getAllDiets(): List<Dieta>

    @Insert
    suspend fun insert(dieta: Dieta)
    @Delete
    suspend fun delete(dieta: Dieta)


    @Query("SELECT * from dieta WHERE id = :id")
    suspend fun getDietById(id: Int): Dieta

}