package com.example.parkingapp.presentation

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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

        setSupportActionBar(binding.tbProfile)

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

    // Создайте меню тулбара
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    // Обработайте нажатие пункта меню
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.complain -> {
                // Перейдите на фрагмент ComplaintFragment
                replaceFragment(ComplainFragment())
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.content_container, fragment)
        fragmentTransaction.commit()
    }
}