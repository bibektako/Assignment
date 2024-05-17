package com.example.taskloginsignup

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.taskloginsignup.databinding.ActivityWeek5Binding

class Week5Activity : AppCompatActivity() {
    lateinit var loginBinding: ActivityWeek5Binding
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        loginBinding = ActivityWeek5Binding.inflate(layoutInflater)
        val view = loginBinding.root
        setContentView(view)
        sharedPreferences = this.getSharedPreferences("MyPrefs", MODE_PRIVATE)

        loginBinding.signup.setOnClickListener() {
            if(loginBinding.Remberme.isChecked) {
                signup()
            }
        }

        loginBinding.login.setOnClickListener() {
            login()
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun signup() {

        val email = loginBinding.TextEmailAddress.text.toString()
        val password = loginBinding.TextNumberPassword.text.toString()
        if (sharedPreferences.contains(email)) {
            Toast.makeText(this, "Email already used", Toast.LENGTH_SHORT).show()
        } else {
            val editor = sharedPreferences.edit()
            editor.putString(email, password)
            editor.apply()
            Toast.makeText(this, "Account created", Toast.LENGTH_SHORT).show()
        }
    }
    private fun login(){
        val email = loginBinding.TextEmailAddress.text.toString()
        val password = loginBinding.TextNumberPassword.text.toString()
        if (sharedPreferences.contains(email)) {
            val savedPassword = sharedPreferences.getString(email, "")
            if (savedPassword == password) {
                Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this,LoginCicked::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Password Incorrect", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Unable to find the user", Toast.LENGTH_SHORT).show()
        }
    }
}