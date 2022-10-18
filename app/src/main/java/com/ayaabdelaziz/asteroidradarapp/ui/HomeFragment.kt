package com.ayaabdelaziz.asteroidradarapp.ui

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.annotation.RequiresApi
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ayaabdelaziz.asteroidradarapp.R
import com.ayaabdelaziz.asteroidradarapp.databinding.FragmentHomeBinding
import com.ayaabdelaziz.asteroidradarapp.viewmodels.MainViewModel
import com.ayaabdelaziz.asteroidradarapp.utils.Constants


class HomeFragment : Fragment() {

    private lateinit var binding:FragmentHomeBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater)
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        binding.lifecycleOwner = this
        binding.viewmodel = mainViewModel
        binding.recyclerView.adapter = AsteroidAdapter()

        setHasOptionsMenu(true)
        return binding.root
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.show_rent_menu -> mainViewModel.displayAsteroidForToday()
            R.id.show_buy_menu -> mainViewModel.displaySavedAsteroid()
            R.id.show_all_menu -> mainViewModel.displayAsteroidForWeek()
        }
        return true
    }
}