package com.pinkyuni.fooddiary.ui.day

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.pinkyuni.fooddiary.R
import com.pinkyuni.fooddiary.databinding.FragmentDayBinding
import com.pinkyuni.fooddiary.ui.MainViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class DayFragment private constructor() : Fragment() {

    private lateinit var binding: FragmentDayBinding
    private val viewModel by viewModel<MainViewModel>()

    companion object {

        fun newInstance() = DayFragment()

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDayBinding.inflate(inflater)
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
        val colors = resources.getIntArray(R.array.colors).toList()
        set.colors = colors
        binding.dayChart.apply {
            this.data = data
            centerText = resources.getString(R.string.day_calories, 1666)
            legend.isEnabled = false
            description.isEnabled = false
            invalidate()
        }
    }

    private fun initObservers() {
        viewModel.getDayHistory(1584144000000, 10)
        viewModel.historyInfo.observe(viewLifecycleOwner, {
            binding.tvHistory.text = it.toString()
        })
        viewModel.error.observe(this, {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        })
    }

}