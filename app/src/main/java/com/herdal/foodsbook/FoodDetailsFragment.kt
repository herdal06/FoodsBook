package com.herdal.foodsbook

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_food_details.*

class FoodDetailsFragment : Fragment() {

    private var foodId = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_food_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            foodId = FoodDetailsFragmentArgs.fromBundle(it).foodId // test
        }

        buttonFoodDetail.setOnClickListener {
            val action = FoodDetailsFragmentDirections.actionFoodDetailsFragmentToFoodListFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }
}