package com.pinkyuni.fooddiary.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.bottomappbar.BottomAppBar
import com.pinkyuni.fooddiary.R
import com.pinkyuni.fooddiary.databinding.ActivityMainBinding
import com.pinkyuni.fooddiary.ui.day.DayFragment
import com.pinkyuni.fooddiary.ui.profile.ProfileFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
//    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        initView()
        supportFragmentManager.beginTransaction()
            .replace(R.id.flContainer, DayFragment.newInstance()).commit()
    }

    private fun initView() {
        binding.bottomAppBar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.miProfile -> {
                    binding.bottomAppBar.apply {
                        fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END
                        replaceMenu(R.menu.bottom_profile_menu)
                    }
                    binding.fab.apply {
                        setImageDrawable(
                            ContextCompat.getDrawable(
                                this@MainActivity,
                                R.drawable.ic_reply
                            )
                        )
                        setOnClickListener {
                            popLastFragment()
                        }
                    }
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.flContainer, ProfileFragment.newInstance())
                        .addToBackStack(ProfileFragment.TAG)
                        .commit()
                }
            }
            return@setOnMenuItemClickListener true
        }
    }

    private fun popLastFragment() {
        binding.bottomAppBar.apply {
            fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
            replaceMenu(R.menu.bottom_nav_menu)
        }
        binding.fab.apply {
            setImageDrawable(
                ContextCompat.getDrawable(
                    this@MainActivity,
                    R.drawable.ic_add
                )
            )
            setOnClickListener {
                Toast.makeText(this@MainActivity, "add clicked", Toast.LENGTH_SHORT).show()
            }
        }
        supportFragmentManager.popBackStack()
    }

}