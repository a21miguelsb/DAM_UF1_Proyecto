package com.example.gestorrutinasapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
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
    private lateinit var adapter: ArrayAdapter<String>
    private val itemList = ArrayList<String>()

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
        _binding= FragmentRoutineBinding.inflate(inflater,container,false)
        val view = binding.root
        var listaRutinas: List<Rutina> = emptyList()
        val adapter = RoutineAdapter(listaRutinas)
        adapter.notifyDataSetChanged()
        adapter.notifyItemChanged(listaRutinas.size-1)
        model.rutinas.observe(viewLifecycleOwner) {
            listaRutinas = it

        }



            val recyclerVIew =  binding.rvRoutines
            recyclerVIew.adapter = adapter
            recyclerVIew.layoutManager = LinearLayoutManager(requireContext())

            for (rutina in listaRutinas) {
                if (!itemList.contains(rutina.name)) itemList.add(rutina.name)
            }

                //model.routineInfo= model.listaRutinas.get(position)

                view.findNavController()
                    .navigate(R.id.action_routineFragment_to_routineInfoFragment)










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
}