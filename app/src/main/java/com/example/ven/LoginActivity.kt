package com.example.ven
// C:\Users\felix\AndroidStudioProjects\ven\app\src\main\java\com\example\ven\MainActivity.kt
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.ven.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val auth = FirebaseAuth.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginBtn.setOnClickListener {
            val email = binding.emailEt.text.toString()
            val password = binding.passwordEt.text.toString()



            auth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    Log.e("login success",email)
                    Log.e("login success",password)

//                    val intent  = Intent(this, MainActivity::class.java)
                    val intent = Intent(this, RegisterActivity::class.java)
                    startActivity(intent)
                    finish() // to finish login, disipear login.

                }
                .addOnFailureListener {
                    Log.e("login erro",email)
                    Log.e("login failure",password)

                    AlertDialog.Builder(this).apply {
                        setTitle("error")
                        setMessage(it.message)
                        setPositiveButton("Aceptar", null)
                    }.show()
                }
        }



        initRecyclerView()


    }

    private fun initRecyclerView() {
/*
        binding.recyclerSuperHero.layoutManager=LinearLayoutManager(this)
        binding.recyclerSuperHero.adapter=SuperHeroAdapter(SuperHeroprovider.superheroList)*/
    }
}