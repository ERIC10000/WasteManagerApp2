package com.example.wastemanagerapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton

class AccountOptionsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account_options)

        val register : AppCompatButton = findViewById(R.id.btn_register)
        register.setOnClickListener {
            val intent = Intent(applicationContext,ImageCapture::class.java)
            startActivity(intent)
        }

        val login : AppCompatButton = findViewById(R.id.btn_firstscreen)
        login.setOnClickListener {
            val intent = Intent(applicationContext , LoginActivity::class.java)
            startActivity(intent)
        }
    }
}