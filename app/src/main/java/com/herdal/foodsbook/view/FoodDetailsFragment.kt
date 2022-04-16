package com.herdal.foodsbook.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.herdal.foodsbook.R
import com.herdal.foodsbook.viewmodel.FoodDetailsViewModel
import kotlinx.android.synthetic.main.fragment_food_details.*

class FoodDetailsFragment : Fragment() {

    private lateinit var viewModel: FoodDetailsViewModel
    private var foodId = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_food_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(FoodDetailsViewModel::class.java)
        viewModel.getRoomData()

        arguments?.let {
            foodId = FoodDetailsFragmentArgs.fromBundle(it).foodId // test
        }

        observeLiveData()
    }

    fun observeLiveData() {
        viewModel.foodLiveData.observe(viewLifecycleOwner, Observer { food ->
            food?.let {
                textView.text = it.name
                textView2.text = it.calorie
                textView3.text = it.carbohydrate
                textView4.text = it.protein
                textView5.text = it.fat
            }
        })
    }
}