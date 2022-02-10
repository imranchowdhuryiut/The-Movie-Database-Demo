package com.example.demomovieapp.views.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.demomovieapp.R
import com.example.demomovieapp.data.model.Movie
import com.example.demomovieapp.utils.Constants
import com.example.demomovieapp.utils.MovieDiffUtil
import com.example.demomovieapp.utils.OnItemClickCallback
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_movie.view.*

/**
 * Created by Imran Chowdhury on 2/9/2022.
 */
class MovieListAdapter (
    private var mCallback: OnItemClickCallback<Movie>
): ListAdapter<Movie, MovieViewHolder> (MovieDiffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder.createViewHolder(parent, mCallback)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

class MovieViewHolder (
    override val containerView: View,
    private val mCallback: OnItemClickCallback<Movie>? = null
): LayoutContainer, RecyclerView.ViewHolder(containerView) {

    private var mModel: Movie? = null

    init {
        containerView.containerView.setOnClickListener {
            mModel?.let { it1 -> mCallback?.onClick(it1) }
        }
    }

    fun bind(item: Movie?) {
        mModel = item
        Glide.with(containerView.context)
            .load(Constants.IMAGE_URL + item?.posterPath)
            .placeholder(R.drawable.progress_animation)
            .into(containerView.imgPoster)
        containerView.tvMovieName.text = item?.title

        val random1 = (0..18).random()
        val random2 = (0..18).random()
        containerView.tvMovieGenre.text = Constants.genres[random1] + ", " + Constants.genres[random2]
    }


    companion object {
        fun createViewHolder(
            parent: ViewGroup,
            listener: OnItemClickCallback<Movie>? = null
        ): MovieViewHolder {
            val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_movie, parent, false)
            return MovieViewHolder(itemView, listener)
        }
    }
}