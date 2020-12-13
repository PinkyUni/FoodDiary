package com.pinkyuni.fooddiary.ui.day

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pinkyuni.fooddiary.databinding.FoodListItemBinding
import com.pinkyuni.fooddiary.entities.FoodHistory

class FoodAdapter : RecyclerView.Adapter<FoodAdapter.FoodHolder>() {

    private val list = arrayListOf<FoodHistory>()

    class FoodHolder(private val binding: FoodListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(foodHistory: FoodHistory) {
            with(binding) {
                tvName.text = foodHistory.food.name
                tvSize.text = foodHistory.size.toString()
                tvUnit.text = foodHistory.unit.name
            }
        }

    }

    fun add(list: List<FoodHistory>) {
        list.forEach {
            if (!this.list.contains(it)) {
                this.list.add(it)
            }
        }
        notifyDataSetChanged()
    }

    fun clear() {
        list.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodHolder {
        return FoodHolder(FoodListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: FoodHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size
}