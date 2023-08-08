package com.cgonzalez.totalplayprueba.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cgonzalez.totalplayprueba.R
import com.cgonzalez.totalplayprueba.model.network.response.ArrayReference

class ReferencesAdapter(
    private val list: List<ArrayReference>
) : RecyclerView.Adapter<ReferencesAdapter.ViewHolder>() {
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_referencies,
            parent, false
        )
        context = parent.context
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.tvReferencesTitle.text = item.aliasbank
        holder.tvReferencesRef.text = item.reference
        Glide.with(context)
            .load(if (item.images.isNotEmpty()) item.images[1].url4X4 else "")
            .fitCenter()
            .into(holder.ivLogo)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvReferencesTitle: TextView = view.findViewById(R.id.tv_references_title)
        val tvReferencesRef: TextView = view.findViewById(R.id.tv_references_ref)
        val ivLogo: ImageView = view.findViewById(R.id.img_references_logo)
    }
}