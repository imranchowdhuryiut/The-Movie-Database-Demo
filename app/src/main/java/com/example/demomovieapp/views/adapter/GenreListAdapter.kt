package com.example.demomovieapp.views.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.demomovieapp.R
import com.example.demomovieapp.utils.StringDiffUtil
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_genre.view.*

/**
 * Created by Imran Chowdhury on 2/10/2022.
 */
class GenreListAdapter: ListAdapter<String, GenreViewHolder>(StringDiffUtil) {

    var selectedItem = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        return GenreViewHolder.createViewHolder(parent)
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.containerView.tvGenreName.text = getItem(position)
        if (selectedItem == position) {
            holder.containerView.viewDot.visibility = View.VISIBLE
            holder.containerView.tvGenreName.setTextColor(Color.RED)
        } else {
            holder.containerView.viewDot.visibility = View.GONE
            holder.containerView.tvGenreName.setTextColor(Color.WHITE)
        }
        holder.containerView.containerViewGenre.setOnClickListener {
            val temp = selectedItem
            selectedItem = position
            notifyItemChanged(temp)
            notifyItemChanged(position)
            Toast.makeText(holder.itemView.context, "Not Implemented", Toast.LENGTH_SHORT).show()
        }
    }
}

class GenreViewHolder(
    override val containerView: View
): LayoutContainer, RecyclerView.ViewHolder(containerView) {

    companion object {
        fun createViewHolder(
            parent: ViewGroup
        ): GenreViewHolder {
            val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_genre, parent, false)
            return GenreViewHolder(itemView)
        }
    }

}