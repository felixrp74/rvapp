package com.example.ven
// C:\Users\felix\AndroidStudioProjects\ven\app\src\main\java\com\example\ven\MainActivity.kt
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ven.adapter.SuperHeroAdapter
import com.example.ven.data.datasources.SuperHeroprovider
import com.example.ven.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()


    }

    private fun initRecyclerView() {

        binding.recyclerSuperHero.layoutManager=LinearLayoutManager(this)
        binding.recyclerSuperHero.adapter=SuperHeroAdapter(SuperHeroprovider.superheroList)
    }
}