package com.herdal.foodsbook.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.herdal.foodsbook.R
import com.herdal.foodsbook.databinding.FragmentFoodDetailsBinding
import com.herdal.foodsbook.viewmodel.FoodDetailsViewModel
import kotlinx.android.synthetic.main.food_recycler_row.*
import kotlinx.android.synthetic.main.fragment_food_details.*

class FoodDetailsFragment : Fragment() {

    private lateinit var viewModel: FoodDetailsViewModel
    private var foodId = 0
    private lateinit var dataBinding: FragmentFoodDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dataBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_food_details,container,false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            foodId = FoodDetailsFragmentArgs.fromBundle(it).foodId // test
        }

        viewModel = ViewModelProviders.of(this).get(FoodDetailsViewModel::class.java)
        viewModel.getRoomData(foodId)

        observeLiveData()
    }

    fun observeLiveData() {
        viewModel.foodLiveData.observe(viewLifecycleOwner, Observer { food ->
            food?.let {
                dataBinding.choosenFood = it
            }
        })
    }
}