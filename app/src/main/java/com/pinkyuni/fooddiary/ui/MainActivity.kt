package com.pinkyuni.fooddiary.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
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
                    popFragmentOnFabClick()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.flContainer, ProfileFragment.newInstance())
                        .addToBackStack(ProfileFragment.TAG)
                        .commit()
                }
            }
            return@setOnMenuItemClickListener true
        }
        initAddFab()
    }

    private fun popFragmentOnFabClick() {
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
    }

    private fun popLastFragment() {
        binding.bottomAppBar.apply {
            replaceMenu(R.menu.bottom_nav_menu)
        }
        initAddFab()
        supportFragmentManager.popBackStack()
    }

    private fun initAddFab() {
        binding.fab.apply {
            setImageDrawable(
                ContextCompat.getDrawable(
                    this@MainActivity,
                    R.drawable.ic_add
                )
            )
            setOnClickListener {
                addNewRecord()
            }
        }
    }

    private fun addNewRecord() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.flContainer, FoodRecordFragment.newInstance())
            .addToBackStack(FoodRecordFragment.TAG)
            .commit()
        popFragmentOnFabClick()
    }

}