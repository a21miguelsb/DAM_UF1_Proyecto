package com.example.gestorrutinasapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gestorrutinasapp.databinding.FragmentObjetivesBinding
import com.example.gestorrutinasapp.model.ObjetiveViewModel
import com.example.gestorrutinasapp.model.ObjetiveViewModelFactory
import com.example.gestorrutinasapp.model.objetive.Objetive
class ObjetivesFragment : Fragment() {

    private var _binding: FragmentObjetivesBinding? = null
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
        // Inflate the layout for this fragment
        _binding=FragmentObjetivesBinding.inflate(inflater, container, false)
        val view = binding.root
        model.getObjetives()
        initRecyclerView()
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
    fun initRecyclerView(){
        model.objetives.observe(viewLifecycleOwner) {
            val recyclerVIew =  binding.rvObjetives
            var listaObjetivos: List<Objetive> = emptyList()
            listaObjetivos = it
            val adapter = view?.let { it1 -> ObjetiveAdapter(listaObjetivos,model, it1,viewLifecycleOwner) }
            if (listaObjetivos.isNotEmpty()){
                recyclerVIew.adapter = adapter
                recyclerVIew.layoutManager = LinearLayoutManager(this.context)
                adapter?.notifyDataSetChanged()

            }
        }

    }


}