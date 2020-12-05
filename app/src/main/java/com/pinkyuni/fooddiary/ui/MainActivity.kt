package com.pinkyuni.fooddiary.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.pinkyuni.fooddiary.R
import com.pinkyuni.fooddiary.data.model.FoodInfo
import com.pinkyuni.fooddiary.ui.base.ResultObserver
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), ResultObserver<FoodInfo> {

    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.getFoodInfo(4)
        viewModel.result.observe(this, this)
    }

    override fun onDataLoaded(data: FoodInfo) {
        tvMain.text = data.toString()
    }

    override fun onTaskComplete() {
        Toast.makeText(this, "completed", Toast.LENGTH_SHORT).show()
    }

    override fun onError(message: String) {
        tvMain.text = "Error occurred :/\nTry again later"
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}