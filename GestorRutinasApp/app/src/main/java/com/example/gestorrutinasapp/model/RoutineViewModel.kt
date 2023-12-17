package com.example.gestorrutinasapp.model

import android.content.Context
import android.text.Editable
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.gestorrutinasapp.model.exercice.Exercice
import com.example.gestorrutinasapp.model.exercice.ExerciceDao
import com.example.gestorrutinasapp.model.rutina.RoutineDao
import com.example.gestorrutinasapp.model.rutina.Rutina
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


class RoutineViewModel(
    private val routineDao: RoutineDao,
    private val exerciceDao: ExerciceDao,
    private val context: Context
) : ViewModel() {

    private val _rutinas = MutableLiveData<List<Rutina>>(emptyList())
    lateinit var rutina: Rutina

    lateinit var exercicesList: List<Exercice>


    private lateinit var insertedRoutine: Rutina
    val rutinas: LiveData<List<Rutina>> = _rutinas


    fun getRutinas() {
        viewModelScope.launch {
            _rutinas.value = routineDao.getAllRoutines()
        }
    }

    fun getExerciceByRoutine(id: Int) {
        viewModelScope.launch {
            var lista = async { exerciceDao.getExerciceByRoutine(id) }
            exercicesList = lista.await()
            Log.d("Exercices View", exercicesList.toString())
        }
    }


    fun getRoutineByName(name: String) {
        viewModelScope.launch {
            var rutina = async { routineDao.getRutinaByName(name) }
            insertedRoutine = rutina.await()
        }

    }

    fun isEntryValid(itemName: Editable, day: Int): Boolean {
        if (itemName.isNotBlank() || day != null) {
            return true
        }
        return false
    }

    private fun getNewItemEntry(name: String, day: Days): Rutina {
        return Rutina(
            name = name,
            day = day,
        )
    }

    fun addNewRoutine(routineName: String, dayRoutine: Days) {
        val newRoutine = getNewItemEntry(routineName, dayRoutine)
        insertarRutina(newRoutine)
    }

    fun addNewExercice(name: String, reps: Int, time: Int) {
        val newExercice =
            Exercice(name = name, repetitions = reps, time = time, id_routine = insertedRoutine.id)
        insertExercices(newExercice)
    }


    private fun insertExercices(exercice: Exercice) {
        viewModelScope.launch {
            exerciceDao.insert(exercice)
        }

    }

    fun insertarRutina(routine: Rutina) {
        viewModelScope.launch {
            routineDao.insert(routine)

        }
    }

    fun deleteRoutine(rutina: Rutina) {
        viewModelScope.launch {
            routineDao.delete(rutina)
        }

    }

    fun deleteExercice(exercice: Exercice) {
        viewModelScope.launch {
            exerciceDao.delete(exercice)
        }


    }
}

class RoutineViewModelFactory(private val routineDao: RoutineDao,private val exerciceDao: ExerciceDao,private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RoutineViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RoutineViewModel(routineDao, exerciceDao,context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}