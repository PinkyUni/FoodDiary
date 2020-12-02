package com.pinkyuni.fooddiary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.pinkyuni.fooddiary.data.DiaryDatabase
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var db: DiaryDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initFood()
    }

    private fun initFood() {
        Observable.fromCallable {
            db = DiaryDatabase.getAppDataBase(context = this)
            val foodDao = db?.foodDao()
            foodDao?.getFoodVitamins()
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError {
                Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
            }
            .doOnNext { list ->
                var finalString = ""
                list?.map { finalString += it.food.name + " : " + it.vitamins }
                tvMain.text = finalString
            }.subscribe()
    }

}