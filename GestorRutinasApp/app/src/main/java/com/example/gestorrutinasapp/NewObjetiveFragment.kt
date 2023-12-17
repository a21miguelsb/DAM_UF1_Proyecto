package com.example.gestorrutinasapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.gestorrutinasapp.databinding.FragmentNewObjetiveBinding
import com.example.gestorrutinasapp.model.ObjetiveViewModel
import com.example.gestorrutinasapp.model.ObjetiveViewModelFactory
import com.example.gestorrutinasapp.model.objetive.Objetive

class NewObjetiveFragment : Fragment() {

    private var _binding: FragmentNewObjetiveBinding? = null

    private val binding get()= _binding!!
    val model: ObjetiveViewModel by  activityViewModels {
        ObjetiveViewModelFactory(
            (activity?.application as GestorRutinasApp).databaseRoutine
                .getObjetiveDao())

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentNewObjetiveBinding.inflate(inflater, container, false)
        val view = binding.root
        val textName = binding.nameText.text
        val objetiveInfo = binding.objetiveInfo.text
        val btnAdd = binding.btnAdd

        btnAdd.setOnClickListener {
            if (textName.isEmpty()){
                Toast.makeText(requireContext(),"Debes completar los datos!",Toast.LENGTH_SHORT,).show()
            }
            else{
                Toast.makeText(requireContext(),"Nuevo objetivo creado.",Toast.LENGTH_SHORT,).show()
                model.objetivo = Objetive(name = textName.toString(), objetive_info =objetiveInfo.toString())
                model.insertarObjetivo(model.objetivo)
                view.findNavController().navigate(R.id.action_newObjetiveFragment_to_objectiveFragment)            }
        }
        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }

}








