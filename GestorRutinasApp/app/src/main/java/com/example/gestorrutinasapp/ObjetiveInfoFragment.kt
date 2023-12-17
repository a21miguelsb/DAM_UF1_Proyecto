package com.example.gestorrutinasapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.gestorrutinasapp.databinding.FragmentObjetiveInfoBinding
import com.example.gestorrutinasapp.model.ObjetiveViewModel
import com.example.gestorrutinasapp.model.ObjetiveViewModelFactory

class ObjetiveInfoFragment : Fragment() {

    private var _binding: FragmentObjetiveInfoBinding? = null
    private val binding get()= _binding!!

    val model: ObjetiveViewModel by  activityViewModels {
        ObjetiveViewModelFactory(
            (activity?.application as GestorRutinasApp).databaseRoutine
                .getObjetiveDao()
        )
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentObjetiveInfoBinding.inflate(inflater, container, false)
        val view = binding.root
        val objName = binding.objetiveInfoName
        val objDescription = binding.ojetivoDescripcion
        val btnCompleted = binding.btnEliminarObjetivo
        objName.text = model.objetiveInfo.name
        objDescription.text = model.objetiveInfo.objetive_info
        btnCompleted.setOnClickListener {
            Toast.makeText(requireContext(),"Enhorabuena, has cumplito un objetivo!",Toast.LENGTH_SHORT).show()
            model.deleteObjetive(model.objetiveInfo)
            view.findNavController().navigate(R.id.action_objetiveInfoFragment_to_objectiveFragment)
        }
        return view
    }

}