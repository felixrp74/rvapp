package com.example.ven

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.ven.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            val email = binding.emailEtRegister.text.toString()
            val password = binding.passwordEtRegister.text.toString()
            val name = binding.nameEtRegister.text.toString()

            auth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    val profile=UserProfileChangeRequest.Builder()
                        .setDisplayName(name)
                        .build()

                    it.user!!.updateProfile(profile)
                        .addOnSuccessListener {
                            AlertDialog.Builder(this).apply {
                                setTitle("Account created")
                                setMessage("Your account has created successfully!")
                                setPositiveButton("Accept"){ _: DialogInterface, _: Int ->
                                    finish()
                                }
                            }.show()
                        }
                        .addOnFailureListener {
                            Utils.showError(this, it.message.toString())
                        }

                }
                .addOnFailureListener {
                    Utils.showError(this, it.message.toString())
                }


        }


    }
}