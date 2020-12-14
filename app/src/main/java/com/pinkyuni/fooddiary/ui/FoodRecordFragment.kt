package com.pinkyuni.fooddiary.ui

import android.content.Context
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.text.bold
import androidx.core.text.color
import androidx.core.text.scale
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.data.*
import com.pinkyuni.fooddiary.R
import com.pinkyuni.fooddiary.databinding.FragmentFoodRecordBinding
import com.pinkyuni.fooddiary.utils.ViewUtils
import org.koin.android.viewmodel.ext.android.viewModel

class FoodRecordFragment : Fragment() {

    private lateinit var binding: FragmentFoodRecordBinding
    private val viewModel by viewModel<MainViewModel>()

    private var foodId: Long = 0
    private var mealId: Long = 0
    private var unitId: Long = 0

    companion object {

        const val TAG = "food_record"
        fun newInstance() = FoodRecordFragment()

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFoodRecordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initChart()
        initView()
    }

    private fun initView() {
        viewModel.getFoodList { foodList ->
            val list = foodList.map { it.name }
            val adapter = ArrayAdapter(
                context!!,
                R.layout.general_dropdown_item,
                list
            )
            binding.foodDropDown.setAdapter(adapter)
        }
        with(binding) {
            ViewUtils.resetTextInputErrorsOnTextChanged(lFood, lMeal, lSize, lUnit)
        }
        binding.foodDropDown.onItemClickListener =
            OnItemClickListener { _, _, position, _ ->
                foodId = position.toLong() + 1
            }
        binding.mealDropDown.onItemClickListener =
            OnItemClickListener { _, _, position, _ ->
                mealId = position.toLong() + 1
            }
        binding.unitDropDown.onItemClickListener =
            OnItemClickListener { _, _, position, _ ->
                unitId = position.toLong() + 1
            }
        binding.btnAdd.setOnClickListener {
            with(binding) {
                val isNotEmpty = ViewUtils.setErrorsIfEmpty(lFood, lMeal, lSize, lUnit)
                if (isNotEmpty) {
                    viewModel.addFoodRecord(
                        foodId,
                        binding.etSize.text.toString().toLong(),
                        unitId,
                        mealId
                    ) {
                        Toast.makeText(
                            context,
                            resources.getString(R.string.added_successfully),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    private fun initChart() {
        binding.bcChart.apply {
            val entries: MutableList<PieEntry> = ArrayList()
            entries.add(PieEntry(100f))
            val set = PieDataSet(entries, "")
            val data = PieData(set)
            val colors = resources.getIntArray(R.array.food_chart_colors).toList()
            set.colors = colors
            set.setDrawValues(false)
            holeRadius = 80f
            setUsePercentValues(true)
            setData(data)
            legend.isEnabled = false
            description.isEnabled = false
            centerText = chartCenterText(context, 0)
            invalidate()
        }
    }

    private fun chartCenterText(context: Context, calories: Int) =
        SpannableStringBuilder()
            .bold {
                scale(2f) {
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