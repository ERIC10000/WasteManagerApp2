package com.example.wastemanagerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {
    private lateinit var bottomNavigation: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        bottomNavigation = findViewById(R.id.bottom_navigation)
        bottomNavigation.setOnNavigationItemSelectedListener{ menuItem ->
            when(menuItem.itemId){

                R.id.home -> {
                    replaceFragment(HomeFragment())
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.profile -> {
                    replaceFragment(NotificationFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                else -> return@setOnNavigationItemSelectedListener false

            } // end when
        }
        replaceFragment(HomeFragment())

//        bottomNavigation.getOrCreateBadge(R.id.bottom_reels).apply {
//            number = 5
//            isVisible = true
//        }




    }// end onCreate

    private fun replaceFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.fragment, fragment).commit()
    }

}