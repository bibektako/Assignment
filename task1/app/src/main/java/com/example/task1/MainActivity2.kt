package com.example.task1

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar

class MainActivity2 : AppCompatActivity() {


        lateinit var editText: EditText
        lateinit var textView: TextView
        lateinit var radioButton1: RadioButton
        lateinit var radioButton2:RadioButton
        lateinit var imageView: ImageView
        lateinit var spinner: Spinner
        lateinit var autoCompleteTextView: AutoCompleteTextView
        lateinit var button: Button
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            enableEdgeToEdge()

            setContentView(R.layout.activity_main)
            imageView = findViewById(R.id.star)
            editText = findViewById(R.id.EditText);
            textView = findViewById(R.id.textView);
            radioButton1 = findViewById(R.id.radioButton1);
            radioButton2 = findViewById(R.id.radioButton2);
            spinner = findViewById(R.id.spinner);
            autoCompleteTextView = findViewById(R.id.autoCompleteTextView);
            button = findViewById(R.id.button);

            val spinnerAdapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                arrayOf("Basketball", "Baseball", "Cricket")
            )
            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.setAdapter(spinnerAdapter)

            val autoCompleteAdapter = ArrayAdapter(
                this,
                android.R.layout.simple_dropdown_item_1line,
                arrayOf("Apple", "Banana", "Orange")
            )
            autoCompleteTextView.setAdapter(autoCompleteAdapter)

            button.setOnClickListener { v ->
                textView.text = editText.getText().toString()


                if (radioButton1.isChecked) {
                    textView.append("\nRadio Button 1 Selected")
                } else if (radioButton2.isChecked) {
                    textView.append("\nRadio Button 2 Selected")
                }

                // Displaying selected Spinner item
                textView.append(
                    """
                    
                    Spinner Item Selected: ${spinner.getSelectedItem()}
                    """.trimIndent()
                )


                textView.append(
                    """
                    
                    AutoCompleteTextView Item Selected: ${autoCompleteTextView.getText()}
                    """.trimIndent()
                )

                // Showing a Toast
                Toast.makeText(this@MainActivity2, "Button Clicked", Toast.LENGTH_SHORT).show()


                val builder: android.app.AlertDialog.Builder = android.app.AlertDialog.Builder(this@MainActivity2)
                builder.setTitle("Alert Dialog")
                    .setMessage("This is an AlertDialog")
                    .setPositiveButton("OK", null)
                    .show()


                Snackbar.make(v, "This is a Snackbar", Snackbar.LENGTH_LONG)
                    .setAction("Action", null)
                    .show()
            }

        }
    }
