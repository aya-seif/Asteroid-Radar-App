package com.ayaabdelaziz.asteroidradarapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.ayaabdelaziz.asteroidradarapp.R
import com.ayaabdelaziz.asteroidradarapp.databinding.FragmentDetailBinding
import com.ayaabdelaziz.asteroidradarapp.pojo.Asteroid


class DetailFragment : Fragment() {


    private val args by navArgs<HomeFragmentArgs>()
    private lateinit var binding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(inflater)
        binding.asteroid = args.asteroid

        Log.d("TAG", "onCreateView: ${args.asteroid.id}")
        return binding.root

    }


}