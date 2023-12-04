package com.example.gestorrutinasapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.gestorrutinasapp.databinding.FragmentNewDietBinding


class NewDietFragment : Fragment() {

    var _binding: FragmentNewDietBinding?=null
    private val binding get()= _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentNewDietBinding.inflate(inflater, container, false)

        // Inflate the layout for this fragment
        val view = binding.root








        return view
    }


}