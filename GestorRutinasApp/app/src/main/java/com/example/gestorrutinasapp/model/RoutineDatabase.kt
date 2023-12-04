package com.example.gestorrutinasapp.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.gestorrutinasapp.model.dieta.Dieta
import com.example.gestorrutinasapp.model.dieta.DietaDao
import com.example.gestorrutinasapp.model.exercice.Exercice
import com.example.gestorrutinasapp.model.exercice.ExerciceDao
import com.example.gestorrutinasapp.model.objetive.Objetive
import com.example.gestorrutinasapp.model.objetive.ObjetiveDao
import com.example.gestorrutinasapp.model.rutina.RoutineDao
import com.example.gestorrutinasapp.model.rutina.Rutina

@Database(entities = [Rutina::class,Exercice::class,Dieta::class,Objetive::class], version = 1)
abstract class RoutineDatabase: RoomDatabase() {
    abstract fun getRoutineDao(): RoutineDao
    abstract fun getExerciceDao(): ExerciceDao
    abstract fun getDietDao(): DietaDao
    abstract fun getObjetiveDao(): ObjetiveDao

    companion object{
        @Volatile
        private var INSTANCE: RoutineDatabase? = null
        fun getDatabase(context: Context): RoutineDatabase {
            return  INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RoutineDatabase::class.java,
                    "rutina_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE =instance
                return instance
            }
        }
    }}