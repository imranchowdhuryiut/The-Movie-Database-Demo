package com.example.demomovieapp.views.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.demomovieapp.R
import com.example.demomovieapp.data.model.Movie
import com.example.demomovieapp.utils.Constants
import com.example.demomovieapp.utils.MovieDiffUtil
import com.example.demomovieapp.utils.OnItemClickCallback
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_movie_search.view.*

/**
 * Created by Imran Chowdhury on 2/10/2022.
 */


class SearchListAdapter (
    private var mCallback: OnItemClickCallback<Movie>
): ListAdapter<Movie, SearchViewHolder>(MovieDiffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder.createViewHolder(parent, mCallback)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

class SearchViewHolder (
    override val containerView: View,
    private val mCallback: OnItemClickCallback<Movie>? = null
): LayoutContainer, RecyclerView.ViewHolder(containerView) {

    private var mModel: Movie? = null

    init {
        containerView.containerView.setOnClickListener {
            mModel?.let { it1 -> mCallback?.onClick(it1) }
        }
        containerView.imgWatchNow.setOnClickListener {
            Toast.makeText(containerView.context, "Not Implemented", Toast.LENGTH_SHORT).show()
        }
    }

    fun bind(item: Movie?) {
        mModel = item
        Glide.with(containerView.context)
            .load(Constants.IMAGE_URL + item?.posterPath)
            .placeholder(R.drawable.progress_animation)
            .into(containerView.imgMoviePoster)
        containerView.tvName.text = item?.title

        val random1 = (0..18).random()
        val random2 = (0..18).random()
        containerView.tvSubText.text = Constants.genres[random1] + ", " + Constants.genres[random2]
        containerView.tvRating.text = item?.voteAverage?.toString()
    }


    companion object {
        fun createViewHolder(
            parent: ViewGroup,
            listener: OnItemClickCallback<Movie>? = null
        ): SearchViewHolder {
            val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_movie_search, parent, false)
            return SearchViewHolder(itemView, listener)
        }
    }
}
