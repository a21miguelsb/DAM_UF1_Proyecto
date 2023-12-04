package com.example.gestorrutinasapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import com.example.gestorrutinasapp.databinding.FragmentObjetivesBinding


class ObjetivesFragment : Fragment() {

    private var _binding: FragmentObjetivesBinding? = null
    private val binding get()= _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding=FragmentObjetivesBinding.inflate(inflater, container, false)
        val view = binding.root

        val btnNext = binding.newObjetive
        btnNext.setOnClickListener{
            view.findNavController().navigate(R.id.action_objectiveFragment_to_newObjetiveFragment)
        }

        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }


}