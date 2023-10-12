package com.example.wastemanagerapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.ImageView

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Apply fade-in animation to the splash screen elements
        val fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        val textView = findViewById<ImageView>(R.id.imageView)
        textView.startAnimation(fadeInAnimation)

        Handler().postDelayed({
            val intent = Intent(applicationContext, IntroductionActivity::class.java)
                                startActivity(intent)
                                finish()
        }, 3000)
    }
}