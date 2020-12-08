package com.pinkyuni.fooddiary.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.pinkyuni.fooddiary.data.model.FoodInfo
import com.pinkyuni.fooddiary.databinding.ActivityMainBinding
import com.pinkyuni.fooddiary.ui.base.ResultObserver
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), ResultObserver<FoodInfo> {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getFoodInfo(4)
        viewModel.result.observe(this, this)
    }

    override fun onDataLoaded(data: FoodInfo) {
        binding.tvMain.text = data.toString()
    }

    override fun onTaskComplete() {
        Toast.makeText(this, "completed", Toast.LENGTH_SHORT).show()
    }

    override fun onError(message: String) {
        binding.tvMain.text = "Error occurred :/\nTry again later"
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}