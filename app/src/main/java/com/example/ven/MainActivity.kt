package com.example.ven

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ven.adapter.PostAdapter
import com.example.ven.databinding.ActivityMainBinding
import com.example.ven.databinding.ActivityRegisterBinding
import com.example.ven.domain.Post
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db.collection("posts").addSnapshotListener{value, error ->
            val posts = value!!.toObjects(Post::class.java)
            posts.forEachIndexed { index, post ->
                post.uid = value.documents[index].id
            }

            binding.rv.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = PostAdapter(this@MainActivity, posts)
            }
        }





    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.logout_item -> {
                auth.signOut()
                finish()
            }
        }

        return super.onOptionsItemSelected(item)
    }


}