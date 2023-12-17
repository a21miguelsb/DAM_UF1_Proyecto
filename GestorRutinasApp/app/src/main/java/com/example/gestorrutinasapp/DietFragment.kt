package com.example.gestorrutinasapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gestorrutinasapp.databinding.FragmentDietBinding
import com.example.gestorrutinasapp.model.DietViewModel
import com.example.gestorrutinasapp.model.DietViewModelFactory
import com.example.gestorrutinasapp.model.dieta.Dieta

class DietFragment : Fragment() {

    private var _binding: FragmentDietBinding? = null

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
        _binding = FragmentDietBinding.inflate(inflater,container,false)
        val view = binding.root

        model.getDietas()
        initRecyclerView()
        val btnAdd = binding.newDiet

        btnAdd.setOnClickListener {
            view.findNavController().navigate(R.id.action_dietFragment_to_newDietFragment)
        }


        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
    fun initRecyclerView(){
        model.dietas.observe(viewLifecycleOwner) {
            val recyclerVIew =  binding.rvDietas
            var listaDietas: List<Dieta> = emptyList()
            listaDietas = it
            val adapter = view?.let { it1 -> DietAdapter(listaDietas,model, it1,viewLifecycleOwner) }
            if (listaDietas.isNotEmpty()){
                recyclerVIew.adapter = adapter
                recyclerVIew.layoutManager = LinearLayoutManager(this.context)
                adapter?.notifyDataSetChanged()

            }
        }

    }

}