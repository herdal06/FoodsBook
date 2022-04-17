package com.herdal.foodsbook.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.herdal.foodsbook.R
import com.herdal.foodsbook.databinding.FoodRecyclerRowBinding
import com.herdal.foodsbook.model.Food
import com.herdal.foodsbook.view.FoodListFragmentDirections
import kotlinx.android.synthetic.main.food_recycler_row.view.*

class FoodAdapter(private val foodList: ArrayList<Food>) : RecyclerView.Adapter<FoodAdapter.RecyclerViewHolder>(), FoodClickListener {
    inner class RecyclerViewHolder(var view: FoodRecyclerRowBinding) : RecyclerView.ViewHolder(view.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<FoodRecyclerRowBinding>(inflater,R.layout.food_recycler_row,parent,false)
        return RecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.view.food = foodList[position]
        holder.view.listener = this
    }

    override fun getItemCount(): Int {
        return foodList.size
    }

    fun updateFoodList(newFoodList: List<Food>) {
        foodList.clear()
        foodList.addAll(newFoodList)
        notifyDataSetChanged()
    }

    override fun foodClicked(view: View) {
        //val uid = view.food_uid.text.toString().toInt()
        val uid = view.uid.text.toString().toIntOrNull()
        uid?.let {
            val action = FoodListFragmentDirections.actionFoodListFragmentToFoodDetailsFragment(it)
            Navigation.findNavController(view).navigate(action)
        }

    }
}

