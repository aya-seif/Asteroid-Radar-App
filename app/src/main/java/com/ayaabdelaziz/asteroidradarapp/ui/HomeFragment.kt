package com.ayaabdelaziz.asteroidradarapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.ayaabdelaziz.asteroidradarapp.viewmodels.MainViewModel
import com.ayaabdelaziz.asteroidradarapp.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {



    private lateinit var binding: FragmentHomeBinding
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


        return binding.root
    }

}