package com.example.gestorrutinasapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
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
            (activity?.application as GestorRutinasApp).databaseRoutine.getExerciceDao()
        )

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        model.getRutinas()
        initRecyclerView()

        _binding= FragmentRoutineBinding.inflate(inflater,container,false)
        val view = binding.root
        val btnAdd = binding.newRoutine
        btnAdd.setOnClickListener {
            //model.routineInfo= model.listaRutinas.get(position)
            view.findNavController()
                .navigate(R.id.action_routineFragment_to_routineInfoFragment)
        }







        val btn_next = binding.newRoutine



        btn_next.setOnClickListener {
            view.findNavController().navigate(R.id.action_routineFragment_to_newRoutineFragment)

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
            val adapter = RoutineAdapter(listaRutinas)

            if (listaRutinas.isNotEmpty()){

                recyclerVIew.adapter = adapter
                Log.d("RutinasAdapter", adapter.itemCount.toString())

                recyclerVIew.layoutManager = LinearLayoutManager(this.context)
                adapter.notifyDataSetChanged()

            }
        }

    }
}