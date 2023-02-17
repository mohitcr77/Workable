package com.example.workable

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.workable.databinding.ActivityMainBinding
import com.example.workable.fragments.HomeFragment
import com.example.workable.fragments.ProfileFragment
import com.example.workable.fragments.RecentFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var bottomNavView : BottomNavigationView
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        setContentView(binding.root)


        supportActionBar?.hide()   //to hide the top bar

        bottomNavView = binding.bottomNavView

        val homeFragment = HomeFragment()
        val profileFragment = ProfileFragment()
        val recentFragment = RecentFragment()

        setThatFragment(homeFragment)

        bottomNavView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home -> {
                setThatFragment(homeFragment)
                }
                R.id.post ->{
                    setThatFragment(recentFragment)
                }
                R.id.profile ->{
                    setThatFragment(profileFragment)

                }
            }
            true
        }



    }

    private fun setThatFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frame_layout,fragment)
            commit()
        }
}