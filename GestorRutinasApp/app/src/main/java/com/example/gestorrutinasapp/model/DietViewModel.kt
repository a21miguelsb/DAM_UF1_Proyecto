package com.example.gestorrutinasapp.model

import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.gestorrutinasapp.model.dieta.Dieta
import com.example.gestorrutinasapp.model.dieta.DietaDao
import com.example.gestorrutinasapp.model.rutina.Rutina
import kotlinx.coroutines.launch

class DietViewModel (
    private val dietaDao: DietaDao,
) : ViewModel() {

    private val _dietas= MutableLiveData<List<Dieta>>(emptyList())

    lateinit var dieta: Dieta
    lateinit var dietaInfo: Dieta
    private lateinit var insertedRoutine: Rutina
    val dietas: LiveData<List<Dieta>> = _dietas






    fun getDietas() {
        viewModelScope.launch {
            _dietas.value= dietaDao.getAllDiets()
        }
    }

    fun getObjetiveById(id:Int) {
        viewModelScope.launch {
            dietaInfo= dietaDao.getDietById(id)
        }
    }

    fun isEntryValid(itemName: Editable, day: Int): Boolean {
        if (itemName.isNotBlank() || day!=null) {
            return true
        }
        return false
    }


    fun insertarDieta(dieta: Dieta) {
        viewModelScope.launch {
            dietaDao.insert(dieta)

        }
    }

    fun deleteObjetive(dieta: Dieta) {
        viewModelScope.launch {
            dietaDao.delete(dieta)
        }

    }


}
class DietViewModelFactory(private val dietaDao: DietaDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DietViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DietViewModel(dietaDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}