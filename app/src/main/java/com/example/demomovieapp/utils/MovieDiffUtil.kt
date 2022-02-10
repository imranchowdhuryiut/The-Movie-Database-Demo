package com.example.demomovieapp.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.demomovieapp.data.model.Movie

/**
 * Created by Imran Chowdhury on 2/9/2022.
 */
object MovieDiffUtil : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}