package com.example.gestorrutinasapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gestorrutinasapp.databinding.FragmentRoutineBinding
import com.example.gestorrutinasapp.model.RoutineViewModel
import com.example.gestorrutinasapp.model.RoutineViewModelFactory
import com.example.gestorrutinasapp.model.rutina.Rutina

class RoutineFragment : Fragment() {
    private var _binding: FragmentRoutineBinding? = null

    private val binding get()= _binding!!

    val model: RoutineViewModel by  activityViewModels {
        RoutineViewModelFactory(
            (activity?.application as GestorRutinasApp).databaseRoutine
                .getRoutineDao(),
            (activity?.application as GestorRutinasApp).databaseRoutine.getExerciceDao(),
            requireContext()
        )
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentRoutineBinding.inflate(inflater,container,false)
        val view = binding.root
        model.getRutinas()
        initRecyclerView()
        val btnAdd = binding.newRoutine
        btnAdd.setOnClickListener {
            view.findNavController()
                .navigate(R.id.action_routineFragment_to_newRoutineFragment)
        }
        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }

    fun initRecyclerView(){
        model.rutinas.observe(viewLifecycleOwner) {
            val recyclerVIew =  binding.rvRoutines
            var listaRutinas: List<Rutina> = emptyList()
            listaRutinas = it
            val adapter = view?.let { it1 -> RoutineAdapter(listaRutinas,model, it1,viewLifecycleOwner) }
            if (listaRutinas.isNotEmpty()){
                recyclerVIew.adapter = adapter
                recyclerVIew.layoutManager = LinearLayoutManager(this.context)
                adapter?.notifyDataSetChanged()

            }
        }

    }
}