package com.example.parkingapp.presentation

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.replace
import com.example.parkingapp.R
import com.example.parkingapp.databinding.ActivityContentBinding

class ContentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityContentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bnvContent.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.fragment_select_parking_spot -> replaceFragment(SelectParkingSpotFragment())
                R.id.fragment_chosen_place -> replaceFragment(ChosenPlaceFragment())
                R.id.fragment_change_profile -> replaceFragment(ChangeProfileFragment())
                else -> {
                    
                }
            }

            true
        }

    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.content_container, fragment)
        fragmentTransaction.commit()
    }
}