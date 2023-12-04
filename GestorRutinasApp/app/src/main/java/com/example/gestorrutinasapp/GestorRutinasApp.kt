package com.example.gestorrutinasapp

import android.app.Application
import com.example.gestorrutinasapp.model.RoutineDatabase


class GestorRutinasApp: Application(){
    val databaseRoutine: RoutineDatabase by lazy{ RoutineDatabase.getDatabase(this)}
}
