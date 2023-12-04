package com.example.gestorrutinasapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.gestorrutinasapp.databinding.FragmentNewObjetiveBinding

class NewObjetiveFragment : Fragment() {

    private var _binding: FragmentNewObjetiveBinding? = null

    private val binding get()= _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentNewObjetiveBinding.inflate(inflater, container, false)
        val view = binding.root
        val textName = binding.nameText.text
        val btnAdd = binding.btnAdd

        btnAdd.setOnClickListener {
            if (textName.isEmpty()){
                Toast.makeText(requireContext(),"Debes completar los datos!",Toast.LENGTH_SHORT,).show()
            }
            else{
                view.findNavController().navigate(R.id.action_newObjetiveFragment_to_objectiveFragment)
            }
        }
        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }

}








