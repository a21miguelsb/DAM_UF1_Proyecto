package com.example.gestorrutinasapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.gestorrutinasapp.databinding.FragmentNewDietBinding
import com.example.gestorrutinasapp.model.DietViewModel
import com.example.gestorrutinasapp.model.DietViewModelFactory
import com.example.gestorrutinasapp.model.dieta.Dieta


class NewDietFragment : Fragment() {

    var _binding: FragmentNewDietBinding?=null
    private val binding get()= _binding!!
    val model: DietViewModel by  activityViewModels {
        DietViewModelFactory(
            (activity?.application as GestorRutinasApp).databaseRoutine
                .getDietDao())

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentNewDietBinding.inflate(inflater, container, false)

        // Inflate the layout for this fragment
        val view = binding.root

        val btnAdd = binding.btnAdd

        btnAdd.setOnClickListener {
            Toast.makeText(requireContext(),"Nueva dieta creada.", Toast.LENGTH_SHORT).show()
            model.dieta =Dieta(name = binding.dietName.text.toString(), diet_info = binding.dietDescription.text.toString())

            model.insertarDieta(model.dieta)
            view.findNavController().navigate(R.id.action_newDietFragment_to_dietFragment)
        }








        return view
    }


}