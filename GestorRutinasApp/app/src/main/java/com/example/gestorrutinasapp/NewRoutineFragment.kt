package com.example.gestorrutinasapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.gestorrutinasapp.databinding.FragmentNewRoutineBinding
import com.example.gestorrutinasapp.model.RoutineViewModel
import com.example.gestorrutinasapp.model.RoutineViewModelFactory

class NewRoutineFragment : Fragment() {


    private var _binding: FragmentNewRoutineBinding? = null
    val model: RoutineViewModel by activityViewModels{
        RoutineViewModelFactory(
            (activity?.application as GestorRutinasApp).databaseRoutine
                .getRoutineDao(),
            (activity?.application as GestorRutinasApp).databaseRoutine.getExerciceDao()
        )
    }
    private val binding get()= _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=FragmentNewRoutineBinding.inflate(inflater,container,false)
        val view = binding.root

        val btn_Next = binding.btnNext

        val nameRoutine =binding.nameText.text

        btn_Next.setOnClickListener {
            if (isEntryValid()){
                model.addNewRoutine(binding.nameText.text.toString(),binding.spinnerDias.selectedItemPosition)

                view.findNavController().navigate(R.id.action_newRoutineFragment_to_newRoutineExerciceFragment)

            }
            else{
                Toast.makeText(requireContext(),"Debes completar los datos!", Toast.LENGTH_SHORT).show()

            }
        }
        return view
    }
    private fun isEntryValid():Boolean{
        return model.isEntryValid(
            binding.nameText.text,
            binding.spinnerDias.selectedItemPosition
        )
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}