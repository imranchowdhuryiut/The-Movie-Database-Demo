package com.example.demomovieapp.utils

import androidx.recyclerview.widget.DiffUtil

/**
 * Created by Imran Chowdhury on 2/10/2022.
 */
object StringDiffUtil : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }
}