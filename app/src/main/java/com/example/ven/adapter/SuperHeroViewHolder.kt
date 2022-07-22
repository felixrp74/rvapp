package com.example.ven.adapter

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ven.databinding.ItemSuperheroBinding
import com.example.ven.domain.SuperHero

class SuperHeroViewHolder(view:View) : RecyclerView.ViewHolder(view) {

    private val binding  = ItemSuperheroBinding.bind(view)

    fun render(superHero: SuperHero){
        binding.tvRealName.text = superHero.realName
        binding.tvSuperHeroName.text = superHero.superhero
        Glide.with(binding.ivSuperHero.context).load(superHero.photo).into(binding.ivSuperHero)

        itemView.setOnClickListener{
            Toast.makeText(
                binding.ivSuperHero.context,
                binding.tvSuperHeroName.text,
                Toast.LENGTH_SHORT
            ).show()
        }

    }

}