package com.pinkyuni.fooddiary.ui.day

import android.content.Context
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.text.bold
import androidx.core.text.color
import androidx.core.text.scale
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.pinkyuni.fooddiary.R
import com.pinkyuni.fooddiary.databinding.FragmentDayBinding
import com.pinkyuni.fooddiary.entities.core.*
import com.pinkyuni.fooddiary.ui.MainViewModel
import com.pinkyuni.fooddiary.utils.getDateMillis
import org.koin.android.viewmodel.ext.android.viewModel
import kotlin.collections.ArrayList

class DayFragment private constructor() : Fragment() {

    private lateinit var binding: FragmentDayBinding
    private val viewModel by viewModel<MainViewModel>()
    private lateinit var breakfastAdapter: FoodAdapter
    private lateinit var lunchAdapter: FoodAdapter
    private lateinit var dinnerAdapter: FoodAdapter
    private lateinit var snackAdapter: FoodAdapter

    companion object {

        fun newInstance() = DayFragment()

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObservers()
    }

    private fun initView() {
        val entries: MutableList<PieEntry> = ArrayList()
        entries.add(PieEntry(18.5f, "Green"))
        entries.add(PieEntry(26.7f, "Yellow"))
        entries.add(PieEntry(24.0f, "Red"))
        entries.add(PieEntry(30.8f, "Blue"))
        val set = PieDataSet(entries, "Election Results")
        val data = PieData(set)
        val colors = resources.getIntArray(R.array.main_chart_colors).toList()
        set.colors = colors
        binding.dayChart.apply {
            this.data = data
            centerText = chartCenterText(context, 0L)
            legend.isEnabled = false
            description.isEnabled = false
            invalidate()
        }
        with(binding.rvBreakfastHistory) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            breakfastAdapter = FoodAdapter()
            adapter = breakfastAdapter
        }
        binding.rvLunchHistory.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            lunchAdapter = FoodAdapter()
            adapter = lunchAdapter
        }
        binding.rvDinnerHistory.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            dinnerAdapter = FoodAdapter()
            adapter = dinnerAdapter
        }
        binding.rvSnackHistory.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            snackAdapter = FoodAdapter()
            adapter = snackAdapter
        }
    }

    private fun initObservers() {
        viewModel.isLoading.observe(viewLifecycleOwner, {
            binding.pbLoading.visibility = if (it) View.VISIBLE else View.GONE
        })
        viewModel.error.observe(this, {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        })
    }

    override fun onResume() {
        super.onResume()
        val today = getDateMillis()
        viewModel.getTotalCalories(today) {
            if (it != null)
                binding.dayChart.apply {
                    centerText = chartCenterText(context, it)
                    invalidate()
                }
        }
        viewModel.getDayHistory(today) { mealHistory ->
            mealHistory.forEach {
                when (it.meal.id) {
                    BREAKFAST -> {
                        breakfastAdapter.add(it.foodList)
                        binding.tvBreakfastTime.text = it.time
                    }
                    LUNCH -> {
                        lunchAdapter.add(it.foodList)
                        binding.tvLunchTime.text = it.time
                    }
                    DINNER -> {
                        dinnerAdapter.add(it.foodList)
                        binding.tvDinnerTime.text = it.time
                    }
                    SNACK -> {
                        snackAdapter.add(it.foodList)
                        binding.tvSnackTime.text = it.time
                    }
                }
            }
        }
    }

    private fun chartCenterText(context: Context, calories: Long) =
        SpannableStringBuilder()
            .bold {
                scale(3f) {
                    color(
                        ContextCompat.getColor(
                            context,
                            R.color.grey_dark
                        )
                    ) { append(calories.toString()) }
                }
            }
            .append(resources.getString(R.string.num_calories))

}