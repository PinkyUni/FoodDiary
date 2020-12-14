package com.pinkyuni.fooddiary.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.pinkyuni.fooddiary.R
import com.pinkyuni.fooddiary.databinding.FragmentFoodRecordBinding
import org.koin.android.viewmodel.ext.android.viewModel

class FoodRecordFragment : Fragment() {

    private lateinit var binding: FragmentFoodRecordBinding
    private val viewModel by viewModel<MainViewModel>()

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
        binding.bcChart.apply {
            val proteinCount: MutableList<BarEntry> = ArrayList()
            proteinCount.add(BarEntry(0f, 10f))
            val proteinSet = BarDataSet(proteinCount, resources.getString(R.string.protein))
            proteinSet.color = ContextCompat.getColor(context, R.color.green_light)

            val fatCount = ArrayList<BarEntry>()
            fatCount.add(BarEntry(1f, 80f))
            val fatSet = BarDataSet(fatCount, resources.getString(R.string.fat))
            fatSet.color = ContextCompat.getColor(context, R.color.green_dark)

            val carboCount = ArrayList<BarEntry>()
            carboCount.add(BarEntry(2f, 40f))
            val carboSet = BarDataSet(carboCount, resources.getString(R.string.carbohydrates))
            carboSet.color = ContextCompat.getColor(context, R.color.green)

            val data = BarData()
            data.addDataSet(proteinSet)
            data.addDataSet(fatSet)
            data.addDataSet(carboSet)

            data.barWidth = 0.9f // set custom bar width
            setData(data)
            setFitBars(true) // make the x-axis fit exactly all bars
            setDrawGridBackground(false)
            axisLeft.isEnabled = false
            axisRight.isEnabled = false
            xAxis.setDrawGridLines(false)
            xAxis.setDrawLabels(false)
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            description.isEnabled = false
            invalidate() // refresh
        }
    }

}