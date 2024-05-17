package com.example.classworkactivity.classwork1

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.classworkactivity.R
import com.example.classworkactivity.databinding.ActivityRegisterScreenBinding

class RegisterScreenActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    var data = arrayOf("Male", "Female")
    lateinit var registerScreenBinding: ActivityRegisterScreenBinding
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        registerScreenBinding = ActivityRegisterScreenBinding.inflate(layoutInflater)
        setContentView(registerScreenBinding.root)

        var adapter = ArrayAdapter(this@RegisterScreenActivity, com.google.android.material.R.layout.support_simple_spinner_dropdown_item, data)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        registerScreenBinding.gender.adapter = adapter
        registerScreenBinding.gender.onItemSelectedListener=this

        registerScreenBinding.signupRegister.setOnClickListener {
            sharedPreferences=getSharedPreferences("SignUp", MODE_PRIVATE)
            var editor=sharedPreferences.edit()
            editor.putString("name",registerScreenBinding.FullnameRegister.text.toString())
            editor.putString("email",registerScreenBinding.EmailRegister.text.toString())
            editor.putString("password",registerScreenBinding.PasswordRegister.text.toString())
            var getPass=registerScreenBinding.PasswordRegister.text.toString()
            if (registerScreenBinding.conformPasswordRegister.text.toString().equals(getPass)){
                editor.putString("confirmPass",registerScreenBinding.conformPasswordRegister.text.toString())
            }
            else{
                registerScreenBinding.conformPasswordRegister.error="Password not match"
            }
            editor.putString("gender",registerScreenBinding.gender.selectedItem.toString())
            if (registerScreenBinding.checkBoxRegister.isChecked){
                var intent= Intent(this@RegisterScreenActivity,LoginActivity::class.java)
                startActivity(intent)
                Toast.makeText(this@RegisterScreenActivity,"SignUp Successfully", Toast.LENGTH_SHORT).show()
                finish()

            }
            else{
                registerScreenBinding.checkBoxRegister.error="Please accept the terms and conditions"
            }
            editor.apply()
        }

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        if (parent != null) {
            val selectedItem = parent.getItemAtPosition(position).toString()
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}