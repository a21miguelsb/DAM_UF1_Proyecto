package com.example.gestorrutinasapp.model.objetive

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
@Dao
interface ObjetiveDao {
    @Query("SELECT * FROM Objetive")
    suspend fun getAllObjetives(): List<Objetive>

    @Query("SELECT * FROM Objetive Where id= :id")
    suspend fun getObjetiveById(id: Int): Objetive

    @Insert
    suspend fun insert(objetivo: Objetive)
    @Delete
    suspend fun delete(objetivo: Objetive)


}