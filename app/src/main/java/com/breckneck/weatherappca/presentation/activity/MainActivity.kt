package com.breckneck.weatherappca.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.breckneck.weatherappca.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonSetCity : Button = findViewById(R.id.buttonSetCity)
        val cityEditText : EditText = findViewById(R.id.cityEditText)

        buttonSetCity.setOnClickListener {

        }
    }
}