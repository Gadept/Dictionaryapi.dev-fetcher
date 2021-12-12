package com.g_adept.intelliastesttask.view.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.g_adept.intelliastesttask.R
import com.g_adept.intelliastesttask.data.entities.Definitions

class DefinitionsAdapter(private val definitions: List<Definitions>) :
    RecyclerView.Adapter<DefinitionsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvDefinition: TextView = view.findViewById(R.id.textViewDefinition)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.definition_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvDefinition.text = definitions[position].definition
    }

    override fun getItemCount(): Int = definitions.size
}