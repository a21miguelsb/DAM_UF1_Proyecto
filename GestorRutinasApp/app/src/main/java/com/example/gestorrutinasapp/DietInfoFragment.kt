package com.example.gestorrutinasapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.gestorrutinasapp.databinding.FragmentDietInfoBinding
import com.example.gestorrutinasapp.model.DietViewModel
import com.example.gestorrutinasapp.model.DietViewModelFactory


class DietInfoFragment : Fragment() {


    private var _binding: FragmentDietInfoBinding? = null

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

        _binding = FragmentDietInfoBinding.inflate(inflater,container,false)
        val view = binding.root
        val objName = binding.dietaInfoName
        val objDescription = binding.dietaDescripcion
        val btnCompleted = binding.btnEliminarDieta
        objName.text = model.dieta.name
        objDescription.text = model.dieta.diet_info
        btnCompleted.setOnClickListener {
            Toast.makeText(requireContext(),"Dieta eliminada correctamente.", Toast.LENGTH_SHORT).show()
            model.deleteObjetive(model.dietaInfo)
            view.findNavController().navigate(R.id.action_dietInfoFragment_to_dietFragment)
        }






        return view
    }


}