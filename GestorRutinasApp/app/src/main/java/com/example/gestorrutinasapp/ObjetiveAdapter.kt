package com.example.gestorrutinasapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.gestorrutinasapp.model.ObjetiveViewModel
import com.example.gestorrutinasapp.model.objetive.Objetive
import kotlinx.coroutines.launch

class ObjetiveAdapter (
    var objetivos: List<Objetive>,
    val model: ObjetiveViewModel,
    val view: View,
    val viewLifecycleOwner: LifecycleOwner
): RecyclerView.Adapter<ObjetiveAdapter.ObjetiveViewHolder>(){
    inner class ObjetiveViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val objetiveNameTextView = itemView.findViewById<TextView>(R.id.nombre_objetivo)
        val objetiveIdView = itemView.findViewById<ImageButton>(R.id.btnObjetive_info)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ObjetiveViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.objetives_list,parent,false)
        return ObjetiveViewHolder(view)
    }
    override fun getItemCount(): Int {
        return objetivos.size
    }
    override fun onBindViewHolder(holder: ObjetiveViewHolder, position: Int) {

        holder.itemView.apply {
            holder.objetiveNameTextView.text = objetivos[position].name
            holder.objetiveIdView.setOnClickListener {
                model.objetivo= model.objetives.value!![position];
                viewLifecycleOwner.lifecycleScope.launch {
                    repeat(2){
                        model.getObjetiveById(model.objetivo.id)
                    }
                }
                view.findNavController().navigate(R.id.action_objectiveFragment_to_objetiveInfoFragment)
            }
        }
    }
}