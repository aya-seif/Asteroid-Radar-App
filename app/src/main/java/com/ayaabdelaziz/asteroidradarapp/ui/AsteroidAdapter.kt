package com.ayaabdelaziz.asteroidradarapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ayaabdelaziz.asteroidradarapp.databinding.AsteroidItemBinding
import com.ayaabdelaziz.asteroidradarapp.pojo.Asteroid

class AsteroidAdapter() :
    ListAdapter<Asteroid, AsteroidAdapter.AsteroidViewHolder>(DiffCallBack()) {
    class AsteroidViewHolder(var binding: AsteroidItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(asteroid: Asteroid) {
            binding.asteroid = asteroid
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AsteroidViewHolder {
        return AsteroidViewHolder(
            AsteroidItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AsteroidViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)

        holder.itemView.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(currentItem)
            val navController = Navigation.findNavController(holder.itemView)
            navController.navigate(action)
        }
    }

    class DiffCallBack : DiffUtil.ItemCallback<Asteroid>() {
        override fun areItemsTheSame(oldItem: Asteroid, newItem: Asteroid) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Asteroid, newItem: Asteroid) = oldItem == newItem
    }


}