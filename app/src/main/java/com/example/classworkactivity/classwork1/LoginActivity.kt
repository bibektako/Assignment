package com.example.classworkactivity.classwork1

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.classworkactivity.R
import com.example.classworkactivity.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    lateinit var sharedPreferences: SharedPreferences
    lateinit var loginBinding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        loginBinding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(loginBinding.root)

        sharedPreferences = getSharedPreferences("SignUp", MODE_PRIVATE)

        loginBinding.SignUp.setOnClickListener {
            intent = Intent(this@LoginActivity, RegisterScreenActivity::class.java)
            startActivity(intent)
            finish()
        }

        loginBinding.ButtonLogin.setOnClickListener {
            sharedPreferences = getSharedPreferences("SignUp", MODE_PRIVATE)
            var email = sharedPreferences.getString("email", "")
            var pass = sharedPreferences.getString("password", "")

            if (email == loginBinding.LogEmail.text.toString() && pass == loginBinding.LogPassword.text.toString()) {
                intent = Intent(this@LoginActivity, TabBarActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this@LoginActivity, "Invalid Credentials", Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onPause() {
        if (loginBinding.checkBoxLogin.isChecked) {
            sharedPreferences.edit().putString("email", loginBinding.LogEmail.text.toString())
                .apply()
            sharedPreferences.edit().putString("password", loginBinding.LogPassword.text.toString())
                .apply()
            Toast.makeText(this@LoginActivity, "Saved", Toast.LENGTH_SHORT).show()
            super.onPause()
        }
        else{
            Toast.makeText(this@LoginActivity, "Not Saved", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onResume() {
        super.onResume()
        loginBinding.LogEmail.setText(sharedPreferences.getString("email", ""))
        loginBinding.LogPassword.setText(sharedPreferences.getString("password", ""))
        Toast.makeText(this@LoginActivity, "Resumed", Toast.LENGTH_SHORT).show()
    }
}