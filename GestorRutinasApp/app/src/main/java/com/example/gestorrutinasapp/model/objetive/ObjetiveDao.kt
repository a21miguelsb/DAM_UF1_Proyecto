package com.example.gestorrutinasapp.model.objetive

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.gestorrutinasapp.model.Days
import kotlinx.coroutines.flow.Flow
@Dao
interface ObjetiveDao {
    @Query("SELECT * FROM Objetive")
    fun getAllObjetives(): Flow<List<Objetive>>

    @Query("SELECT * FROM Objetive Where name= :name")
    suspend fun getObjetiveById(name: String): Objetive

    @Insert
    suspend fun insert(objetivo: Objetive)
}