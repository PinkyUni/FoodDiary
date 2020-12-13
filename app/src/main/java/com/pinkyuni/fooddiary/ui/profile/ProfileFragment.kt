package com.pinkyuni.fooddiary.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.pinkyuni.fooddiary.R
import com.pinkyuni.fooddiary.databinding.FragmentProfileBinding
import com.pinkyuni.fooddiary.ui.MainViewModel
import com.pinkyuni.fooddiary.ui.dialog.DatePicker
import com.pinkyuni.fooddiary.utils.toEditable
import org.koin.android.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*

class ProfileFragment private constructor() : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private val viewModel by viewModel<MainViewModel>()

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
        initObservers()
    }

    private fun initObservers() {
        viewModel.isLoading.observe(viewLifecycleOwner, {
            binding.pbLoading.visibility = if (it) View.VISIBLE else View.GONE
        })
        viewModel.getUser(10) { user ->
            binding.apply {
                tvName.text = user.name.toEditable()
                tvHeight.text = user.height.toString().toEditable()
                btnBirth.text = formatDate(user.birth)
            }
        }
    }

    private fun formatDate(date: Date): String {
        val pattern = "dd.MM.yyyy"
        val simpleDateFormat = SimpleDateFormat(pattern, Locale.getDefault())
        return simpleDateFormat.format(date)
    }

    private fun initView() {
        binding.btnBirth.setOnClickListener {
            val dialog = DatePicker {
                binding.btnBirth.text = formatDate(it.time)
            }
            dialog.show(childFragmentManager, DatePicker.TAG)
        }
        binding.targetDropdown.setOnClickListener {
            viewModel.getTargets { targets ->
                val list = targets.map { it.name }
                val adapter = ArrayAdapter(
                    context!!,
                    R.layout.gender_dropdown_item,
                    list
                )
                binding.targetDropdown.setAdapter(adapter)
            }
        }
        binding.genderDropDown.setOnClickListener {
            viewModel.getGenders { genders ->
                val list = genders.map { it.name }
                val adapter = ArrayAdapter(
                    context!!,
                    R.layout.gender_dropdown_item,
                    list
                )
                binding.genderDropDown.setAdapter(adapter)
            }
        }
        binding.activityDropdown.setOnClickListener {
            viewModel.getActivities { activities ->
                val list = activities.map { it.name }
                val adapter = ArrayAdapter(
                    context!!,
                    R.layout.gender_dropdown_item,
                    list
                )
                binding.activityDropdown.setAdapter(adapter)
            }
        }
    }

}