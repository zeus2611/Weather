package com.nischay.weather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.nischay.weather.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigation = findViewById<MeowBottomNavigation>(R.id.bottomNav)
        bottomNavigation.add(MeowBottomNavigation.Model(1, R.drawable.ic_home))
        bottomNavigation.add(MeowBottomNavigation.Model(2, R.drawable.ic_search))
        bottomNavigation.add(MeowBottomNavigation.Model(3, R.drawable.ic_profile))


        bottomNavigation.setOnClickMenuListener {
            when(it.id){
                1 ->{
                    Toast.makeText(this@MainActivity, "Home", Toast.LENGTH_SHORT).show()
                }

                2 ->{
                    replaceFragment(BlankFragment.newInstance())
                }

                3 -> {
                    Toast.makeText(this@MainActivity, "Profile", Toast.LENGTH_SHORT).show()
                }
                else ->{
                    Toast.makeText(this@MainActivity, "Home", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentTransition = supportFragmentManager.beginTransaction()
        fragmentTransition.replace(R.id.fragmentContainer, fragment).addToBackStack(fragment::class.java.simpleName).commit()
    }
}