package com.capstone.skinsavvy.view.signin

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.capstone.skinsavvy.databinding.ActivitySignInBinding
import com.capstone.skinsavvy.view.main.MainActivity
import com.capstone.skinsavvy.view.signup.SignUpActivity

class SignInActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySignInBinding
    private val signInViewModel : SigninViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signUpButton.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
            finish()
        }
        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        signInViewModel.signInResult.observe(this, Observer { result ->
            if (result) {
                Toast.makeText(this, "Sign in successful", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Sign in failed", Toast.LENGTH_SHORT).show()
            }
        })

    }
}