package com.example.gestorrutinasapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.example.gestorrutinasapp.databinding.FragmentRoutineInfoBinding
import com.example.gestorrutinasapp.model.RoutineViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


class RoutineInfoFragment : Fragment() {

    private var _binding: FragmentRoutineInfoBinding? = null
    private val binding get()= _binding!!
    val model: RoutineViewModel by viewModels(
        ownerProducer = {this.requireActivity()}
    )


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRoutineInfoBinding.inflate(inflater,container,false)
        val view = binding.root
        Log.d("Rutina Info ",model.rutina.toString())


        viewLifecycleOwner.lifecycleScope.launch {
            var lista = async {model.getExerciceByRoutine(model.rutina.id) }
            var exercicesList = lista.await()
            Log.d("Exercices View",exercicesList.toString())
        }
        binding.routineInfoName.text = model.rutina.name.uppercase()+"("+model.rutina.day+")"

        if (model.exercicesList.isNotEmpty()){

            binding.exerciceName1.text = model.exercicesList[0].name
            binding.exerciceReps1.text = model.exercicesList[0].repetitions.toString()
            binding.exerciceTime1.text = model.exercicesList[0].time.toString()

            binding.exerciceName2.text = model.exercicesList[1].name
            binding.exerciceReps2.text = model.exercicesList[1].repetitions.toString()
            binding.exerciceTime2.text = model.exercicesList[1].time.toString()

            binding.exerciceName3.text = model.exercicesList[2].name
            binding.exerciceReps3.text = model.exercicesList[2].repetitions.toString()
            binding.exerciceTime3.text = model.exercicesList[2].time.toString()

            binding.exerciceName4.text = model.exercicesList[3].name
            binding.exerciceReps4.text = model.exercicesList[3].repetitions.toString()
            binding.exerciceTime4.text = model.exercicesList[3].time.toString()

            binding.exerciceName5.text = model.exercicesList[4].name
            binding.exerciceReps5.text = model.exercicesList[4].repetitions.toString()
            binding.exerciceTime5.text = model.exercicesList[4].time.toString()
        }



        val btnDelete = binding.btnEliminarRutina

        btnDelete.setOnClickListener {
            try
            {
                model.deleteRoutine(model.rutina)
                for (exercice in model.exercicesList){
                    model.deleteExercice(exercice)
                }
                view.findNavController().navigate(R.id.action_routineInfoFragment_to_routineFragment)
            }
            catch (e:Exception){
                Log.d("Error",e.toString())
            }

        }













        return view
    }


}