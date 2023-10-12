package com.example.wastemanagerapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton

class IntroductionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_introduction)

        val next = findViewById<AppCompatButton>(R.id.btn_firstscreen)
        next.setOnClickListener {
            val intent = Intent(applicationContext , AccountOptionsActivity::class.java)
            startActivity(intent)
        }
    }
}