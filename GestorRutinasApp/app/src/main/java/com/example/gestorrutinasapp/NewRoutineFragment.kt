package com.example.gestorrutinasapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.example.gestorrutinasapp.databinding.FragmentNewRoutineBinding
import com.example.gestorrutinasapp.model.Days
import com.example.gestorrutinasapp.model.RoutineViewModel
import com.example.gestorrutinasapp.model.RoutineViewModelFactory
import com.example.gestorrutinasapp.model.rutina.Rutina
import kotlinx.coroutines.launch

class NewRoutineFragment : Fragment() {


    private var _binding: FragmentNewRoutineBinding? = null
    val model: RoutineViewModel by activityViewModels {
        RoutineViewModelFactory(
            (activity?.application as GestorRutinasApp).databaseRoutine
                .getRoutineDao(),
            (activity?.application as GestorRutinasApp).databaseRoutine.getExerciceDao(),
            requireContext()
        )
    }
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewRoutineBinding.inflate(inflater, container, false)
        val view = binding.root

        val btn_Next = binding.btnNext


        btn_Next.setOnClickListener {
            if (isEntryValid()) {
                model.rutina =Rutina(name=binding.nameText.text.toString(),  day = Days.values()[binding.spinnerDias.selectedItemPosition])

                model.addNewRoutine(model.rutina.name, model.rutina.day)
                viewLifecycleOwner.lifecycleScope.launch {
                    model.getRoutineByName(model.rutina.name)
                    view.findNavController().navigate(R.id.action_newRoutineFragment_to_newRoutineExerciceFragment)
                }


            } else {
                Toast.makeText(requireContext(), "Debes completar los datos!", Toast.LENGTH_SHORT)
                    .show()

            }
        }
        return view
    }

    private fun isEntryValid(): Boolean {
        return model.isEntryValid(
            binding.nameText.text,
            binding.spinnerDias.selectedItemPosition
        )
    }
}