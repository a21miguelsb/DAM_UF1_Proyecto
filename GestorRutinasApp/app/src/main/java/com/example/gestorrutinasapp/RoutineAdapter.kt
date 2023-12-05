package com.example.gestorrutinasapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gestorrutinasapp.model.rutina.Rutina

class RoutineAdapter(
    var rutinas: List<Rutina>
):RecyclerView.Adapter<RoutineAdapter.RoutineViewHolder>(){
    inner class RoutineViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val routineNameTextView = itemView.findViewById<TextView>(R.id.texto_rutinas)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoutineViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.routines_list,parent,false)
        return RoutineViewHolder(view)
    }

    override fun getItemCount(): Int {
        return rutinas.size
    }

    override fun onBindViewHolder(holder: RoutineViewHolder, position: Int) {

        holder.itemView.apply {
            holder.routineNameTextView.text = rutinas[position].name
        }
    }


}