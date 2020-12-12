package com.pinkyuni.fooddiary.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pinkyuni.fooddiary.R
import com.pinkyuni.fooddiary.databinding.ActivityMainBinding
import com.pinkyuni.fooddiary.ui.day.DayFragment
import org.koin.android.viewmodel.ext.android.viewModel

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
        supportFragmentManager.beginTransaction().replace(R.id.flContainer, DayFragment.newInstance()).commit()
    }

}