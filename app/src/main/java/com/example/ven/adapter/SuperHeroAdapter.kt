package com.example.ven.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ven.R
import com.example.ven.databinding.ItemSuperheroBinding
import com.example.ven.domain.SuperHero

class SuperHeroAdapter(private val superHeroList: List<SuperHero>):RecyclerView.Adapter<SuperHeroViewHolder>() {

    inner class ViewHolder(val binding: ItemSuperheroBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        return SuperHeroViewHolder(layoutInflater.inflate(R.layout.item_superhero, parent, false))
    }

    override fun onBindViewHolder(holder: SuperHeroViewHolder, position: Int) {
        val item = superHeroList[position]
        holder.render(item)
    }

    override fun getItemCount(): Int = superHeroList.size


}