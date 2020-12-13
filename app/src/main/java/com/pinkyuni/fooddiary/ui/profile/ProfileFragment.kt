package com.pinkyuni.fooddiary.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.pinkyuni.fooddiary.R
import com.pinkyuni.fooddiary.databinding.FragmentProfileBinding
import com.pinkyuni.fooddiary.ui.dialog.DatePicker
import java.text.SimpleDateFormat
import java.util.*

class ProfileFragment private constructor() : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    companion object {

        const val TAG = "profile"
        fun newInstance() = ProfileFragment()

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.btnBirth.setOnClickListener {
            val dialog = DatePicker {
                val pattern = "dd.MM.yyyy"
                val simpleDateFormat = SimpleDateFormat(pattern, Locale.getDefault())
                val date: String = simpleDateFormat.format(it.time)
                binding.btnBirth.text = date
            }
            dialog.show(childFragmentManager, DatePicker.TAG)
        }

        val COUNTRIES = arrayOf("Item 1", "Item 2", "Item 3", "Item 4")
        val adapter = ArrayAdapter(
            context!!,
            R.layout.gender_dropdown_item,
            COUNTRIES
        )
        binding.genderDropDown.setAdapter(adapter)
    }

}