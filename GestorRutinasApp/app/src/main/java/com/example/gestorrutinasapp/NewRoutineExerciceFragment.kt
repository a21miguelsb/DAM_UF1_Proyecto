package com.example.gestorrutinasapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.gestorrutinasapp.databinding.FragmentNewRoutineExerciceBinding
import com.example.gestorrutinasapp.model.RoutineViewModel
import com.example.gestorrutinasapp.model.exercice.Exercice

class NewRoutineExerciceFragment : Fragment() {

    private var _binding: FragmentNewRoutineExerciceBinding? = null
    val model: RoutineViewModel by viewModels(
        ownerProducer = {this.requireActivity()}
    )
    private val binding get()= _binding!!
    private lateinit var adapter: ArrayAdapter<String>
    private val itemList = ArrayList<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentNewRoutineExerciceBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.routineName.text = model.rutina.name.uppercase()+"("+model.rutina.day+")"
        val btnAdd = binding.addRoutine

        btnAdd.setOnClickListener {

            try {
                val name1 = binding.exerciceName1.text.toString()
                val reps1 = binding.exerciceReps1.text.toString().toInt()
                val time1 = binding.exerciceTime1.text.toString().toInt()
                model.addNewExercice(name1,reps1,time1)


                val name2 = binding.exerciceName2.text.toString()
                val reps2 = binding.exerciceReps2.text.toString().toInt()
                val time2 = binding.exerciceTime2.text.toString().toInt()
                model.addNewExercice(name2,reps2,time2)


                val name3 = binding.exerciceName3.text.toString()
                val reps3 = binding.exerciceReps3.text.toString().toInt()
                val time3 = binding.exerciceTime3.text.toString().toInt()
                model.addNewExercice(name3,reps3,time3)



                val name4 = binding.exerciceName4.text.toString()
                val reps4 = binding.exerciceReps4.text.toString().toInt()
                val time4 = binding.exerciceTime4.text.toString().toInt()
                model.addNewExercice(name4,reps4,time4)


                val name5 = binding.exerciceName5.text.toString()
                val reps5 = binding.exerciceReps5.text.toString().toInt()
                val time5 = binding.exerciceTime5.text.toString().toInt()
                model.addNewExercice(name5,reps5,time5)

                Toast.makeText(requireContext(), "Rutina añadida correctamente", Toast.LENGTH_SHORT).show()
                view.findNavController().navigate(R.id.action_newRoutineExerciceFragment_to_routineFragment)



            }catch (exception: Exception){
                Toast.makeText(requireContext(), "Debes cubrir los datos de los ejercicios", Toast.LENGTH_SHORT).show()

            }

        }




        return view
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}