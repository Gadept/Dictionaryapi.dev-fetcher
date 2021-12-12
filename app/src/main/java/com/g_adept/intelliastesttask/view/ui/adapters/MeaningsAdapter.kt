package com.g_adept.intelliastesttask.view.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.g_adept.intelliastesttask.R
import com.g_adept.intelliastesttask.data.entities.Meanings

class MeaningsAdapter(private var meanings: List<Meanings>) :
    RecyclerView.Adapter<MeaningsAdapter.ViewHolder>() {

    private var context: Context? = null

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvPartOfSpeech: TextView = view.findViewById(R.id.tvPartOfSpeech)
        val rvDefinitions: RecyclerView = view.findViewById(R.id.rvDefinitions)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.meanings_list_item, parent, false)
        context = parent.context
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvPartOfSpeech.text = meanings[position].partOfSpeech
        holder.rvDefinitions.setHasFixedSize(true)
        holder.rvDefinitions.layoutManager =
            LinearLayoutManager(context)
        val definitionsAdapter = DefinitionsAdapter(meanings[position].definitions)
        holder.rvDefinitions.adapter = definitionsAdapter
    }

    override fun getItemCount(): Int = meanings.size
}