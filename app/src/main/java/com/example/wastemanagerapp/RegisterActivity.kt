package com.example.wastemanagerapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.wastemanagerapp.helpers.PrefsHelper

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        val next : TextView = findViewById(R.id.next)
        val firstName : EditText = findViewById(R.id.firstName)
        val lastName : EditText = findViewById(R.id.lastName)
        val email : EditText = findViewById(R.id.email)
        val link : TextView = findViewById(R.id.Quality)
        link.setOnClickListener {
            val intent = Intent(applicationContext , LoginActivity::class.java)
            startActivity(intent)
        }


        next.setOnClickListener {
            if ( firstName.text.isEmpty() || lastName.text.isEmpty() || email.text.isEmpty()){
                Toast.makeText(applicationContext, "Please fill in all the fields", Toast.LENGTH_LONG).show()
            }else{
                PrefsHelper.savePrefs(this,"firstName",firstName.text.toString())
                PrefsHelper.savePrefs(this,"lastName",lastName.text.toString())
                PrefsHelper.savePrefs(this,"email",email.text.toString())
                val intent = Intent(applicationContext , Register2::class.java)
                startActivity(intent)
            }


        }
    }
}