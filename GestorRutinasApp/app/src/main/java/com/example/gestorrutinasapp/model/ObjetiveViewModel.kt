package com.example.gestorrutinasapp.model

import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.gestorrutinasapp.model.objetive.Objetive
import com.example.gestorrutinasapp.model.objetive.ObjetiveDao
import com.example.gestorrutinasapp.model.rutina.Rutina
import kotlinx.coroutines.launch

class ObjetiveViewModel (
    private val objetiveDao: ObjetiveDao,
) : ViewModel() {

    private val _objetives= MutableLiveData<List<Objetive>>(emptyList())

    lateinit var objetivo: Objetive
    lateinit var objetiveInfo:Objetive
    private lateinit var insertedRoutine: Rutina
    val objetives: LiveData<List<Objetive>> = _objetives






    fun getObjetives() {
        viewModelScope.launch {
            _objetives.value= objetiveDao.getAllObjetives()
        }
    }

    fun getObjetiveById(id:Int) {
        viewModelScope.launch {
            objetiveInfo= objetiveDao.getObjetiveById(id)
        }
    }

    fun isEntryValid(itemName: Editable, day: Int): Boolean {
        if (itemName.isNotBlank() || day!=null) {
            return true
        }
        return false
    }


    fun insertarObjetivo(objetive: Objetive) {
        viewModelScope.launch {
            objetiveDao.insert(objetive)

        }
    }

    fun deleteObjetive(objetivo: Objetive) {
        viewModelScope.launch {
            objetiveDao.delete(objetivo)
        }

    }


}
class ObjetiveViewModelFactory(private val objetiveDao: ObjetiveDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ObjetiveViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ObjetiveViewModel(objetiveDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}