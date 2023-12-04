package com.example.gestorrutinasapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.gestorrutinasapp.databinding.FragmentRoutineInfoBinding
import com.example.gestorrutinasapp.model.RoutineViewModel


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







        return view
    }


}