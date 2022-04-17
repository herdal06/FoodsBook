package com.herdal.foodsbook.view

import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.herdal.foodsbook.R
import com.herdal.foodsbook.adapter.FoodAdapter
import com.herdal.foodsbook.viewmodel.FoodListViewModel
import kotlinx.android.synthetic.main.fragment_food_list.*

class FoodListFragment : Fragment() {

    private lateinit var viewModel: FoodListViewModel
    private val adapter = FoodAdapter(arrayListOf())




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_food_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(FoodListViewModel::class.java)
        viewModel.refreshData()

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        swipeRefreshLayout.setOnRefreshListener {
            progressBar.visibility = View.VISIBLE
            textViewErrorMessage.visibility = View.GONE
            recyclerView.visibility = View.GONE
            viewModel.refreshFromInternet()
            swipeRefreshLayout.isRefreshing = false
        }

        observeLiveData()
    }

    fun observeLiveData() {
        viewModel.foods.observe(viewLifecycleOwner, Observer { foods ->
            foods?.let {
                recyclerView.visibility = View.VISIBLE
                adapter.updateFoodList(foods)
            }
        })
        viewModel.errorMessage.observe(viewLifecycleOwner, Observer { error ->
            error?.let {
                if(it) {
                    textViewErrorMessage.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                }
                else {
                    textViewErrorMessage.visibility = View.GONE
                }
            }
        })
        viewModel.loadingBar.observe(viewLifecycleOwner, Observer { loading ->
            loading?.let {
                if(it) {
                    progressBar.visibility = View.VISIBLE
                    textViewErrorMessage.visibility = View.GONE
                    recyclerView.visibility = View.GONE
                }
                else {
                    progressBar.visibility = View.GONE
                }
            }
        })
    }
}