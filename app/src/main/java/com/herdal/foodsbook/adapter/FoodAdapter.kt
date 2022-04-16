package com.herdal.foodsbook.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.herdal.foodsbook.R
import com.herdal.foodsbook.model.Food
import com.herdal.foodsbook.view.FoodListFragmentDirections
import kotlinx.android.synthetic.main.food_recycler_row.view.*

class FoodAdapter(private val foodList: ArrayList<Food>) : RecyclerView.Adapter<FoodAdapter.RecyclerViewHolder>() {
    inner class RecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.food_recycler_row,parent,false)
        return RecyclerViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.itemView.foodName.text = foodList.get(position).name
        holder.itemView.foodCalorie.text = foodList.get(position).calorie
        // todo : add image
        holder.itemView.setOnClickListener {
            val action = FoodListFragmentDirections.actionFoodListFragmentToFoodDetailsFragment(0)
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return foodList.size
    }

    fun updateFoodList(newFoodList: List<Food>) {
        foodList.clear()
        foodList.addAll(newFoodList)
        notifyDataSetChanged()
    }
}

