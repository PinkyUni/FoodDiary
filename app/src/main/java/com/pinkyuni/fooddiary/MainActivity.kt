package com.pinkyuni.fooddiary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.pinkyuni.entities.associative.FoodVitaminCrossRef
import com.pinkyuni.entities.core.Food
import com.pinkyuni.entities.core.Gender
import com.pinkyuni.entities.core.Vitamin
import com.pinkyuni.fooddiary.data.DiaryDatabase
import com.pinkyuni.usecases.GenderDao
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var db: DiaryDatabase? = null
    private var genderDao: GenderDao? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initFood()
    }

    private fun initFood() {
        Observable.fromCallable {
            val vitamin = Vitamin(1, "Ca")
            val vitamin2 = Vitamin(2, "Mg")
            val food = Food(1, "Milk")
            val fv = ArrayList<FoodVitaminCrossRef>()
            fv.add(FoodVitaminCrossRef(food.id, vitamin.id))
            fv.add(FoodVitaminCrossRef(food.id, vitamin2.id))
            DiaryDatabase.destroyDataBase()
            db = DiaryDatabase.getAppDataBase(context = this)
            val foodDao = db?.foodDao()
            with(foodDao) {
                this?.insert(vitamin)
                this?.insert(vitamin2)
                this?.insert(food)
                this?.addFoodVitamins(fv)
            }
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

    private fun initGender() {
        Observable.fromCallable {
            DiaryDatabase.destroyDataBase()
            db = DiaryDatabase.getAppDataBase(context = this)
            genderDao = db?.genderDao()

            val gender1 = Gender(name = "Male", id = 1)
            val gender2 = Gender(name = "Female", id = 2)

            with(genderDao) {
                this?.insertGender(gender1)
                this?.insertGender(gender2)
            }
            genderDao?.getGenders()
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext { list ->
                var finalString = ""
                list?.map { finalString += it.name + " - " }
                tvMain.text = finalString
            }.subscribe()
    }
}