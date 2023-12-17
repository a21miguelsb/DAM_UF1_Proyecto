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
import com.example.gestorrutinasapp.model.DietViewModel
import com.example.gestorrutinasapp.model.dieta.Dieta
import kotlinx.coroutines.launch

class DietAdapter (
    var objetivos: List<Dieta>,
    val model: DietViewModel,
    val view: View,
    val viewLifecycleOwner: LifecycleOwner
): RecyclerView.Adapter<DietAdapter.DietViewHolder>(){

    inner class DietViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val objetiveNameTextView = itemView.findViewById<TextView>(R.id.nombre_objetivo)
        val objetiveIdView = itemView.findViewById<ImageButton>(R.id.btnObjetive_info)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DietViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.objetives_list,parent,false)
        return DietViewHolder(view)
    }


    override fun getItemCount(): Int {
        return objetivos.size
    }

    override fun onBindViewHolder(holder: DietViewHolder, position: Int) {

        holder.itemView.apply {
            holder.objetiveNameTextView.text = objetivos[position].name
            holder.objetiveIdView.setOnClickListener {
                model.dieta= model.dietas.value!![position];
                viewLifecycleOwner.lifecycleScope.launch {
                    repeat(2){
                        model.getObjetiveById(model.dieta.id)
                    }
                }
                view.findNavController().navigate(R.id.action_dietFragment_to_dietInfoFragment)
            }
        }
    }
}