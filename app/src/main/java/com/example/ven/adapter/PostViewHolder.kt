package com.example.ven.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.view.View
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.ven.R
import com.example.ven.databinding.CardPostBinding
import com.example.ven.domain.Post
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat

class PostViewHolder(private val view : View):RecyclerView.ViewHolder(view) {

    private val binding  = CardPostBinding.bind(view)
    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    fun render(post:Post, activity: Activity){

        val likes = post.likes!!.toMutableList()
        var liked = likes.contains(auth.uid)

        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm a")
        binding.nameEtCard.text = post.userName
        binding.tvPostCard.text = post.post
        binding.dateEtCard.text = post.date?.let { sdf.format(it) }
        binding.tvLikesCard.text = " ${likes.size} likes"

        binding.btnLikeCard.setOnClickListener {
            liked = !liked
            setColor(liked, binding.btnShareCard, activity)

            if (liked) likes.add(auth.uid!!)
            else likes.remove(auth.uid)

            val doc = db.collection("posts").document(post.uid!!)
            db.runTransaction {
                it.update(doc,"likes",likes)
                null
            }


        }

        binding.btnShareCard.setOnClickListener {
            val sendIntent = Intent().apply{
                action =    Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, post.post)
                type="text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent,null)
             activity.startActivity(shareIntent)
        }

    }

    private fun setColor(liked:Boolean, likeButton:Button,  activity: Activity){
        if(liked) likeButton.setTextColor(ContextCompat.getColor(activity, R.color.purple_200))
        else likeButton.setTextColor(Color.BLACK)
    }


}