package com.example.demomovieapp.views.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.demomovieapp.R
import com.example.demomovieapp.data.model.MovieCastResponse
import com.example.demomovieapp.data.model.MovieDetailsResponse
import com.example.demomovieapp.data.network.ApiResponse
import com.example.demomovieapp.data.network.LiveDataResource
import com.example.demomovieapp.databinding.FragmentMovieDetailsBinding
import com.example.demomovieapp.utils.Constants
import com.example.demomovieapp.viewModels.MovieViewModel
import java.text.SimpleDateFormat

class MovieDetailsFragment : Fragment() {

    private var _binding: FragmentMovieDetailsBinding? = null

    private val mViewModel by viewModels<MovieViewModel>()

    private val dateFormat by lazy { SimpleDateFormat("yyyy") }
    private val dateFormatGiven by lazy { SimpleDateFormat("yyyy-mm-dd") }

    private val args by navArgs<MovieDetailsFragmentArgs>()

    private var mMovieCastLiveData: LiveData<LiveDataResource<ApiResponse<MovieCastResponse>>>? =
        null
    private val mMovieCastLiveDataObserver: Observer<LiveDataResource<ApiResponse<MovieCastResponse>>> =
        Observer { data ->
            data?.let { resource ->

                when (resource.status) {
                    LiveDataResource.Status.SUCCESS -> {
                        _binding?.progressView?.visibility = View.GONE
                        populateCastView(resource.data?.data)
                    }
                    LiveDataResource.Status.ERROR -> {
                        _binding?.progressView?.visibility = View.GONE
                    }
                    LiveDataResource.Status.LOADING -> {
                        _binding?.progressView?.visibility = View.VISIBLE
                    }
                }
            }
        }

    private fun populateCastView(data: MovieCastResponse?) {
        data?.let {
            val list = it.cast ?: mutableListOf()
            if (list.isNotEmpty() and (list.size > 6)) {
                _binding?.tvCastPlus?.text = "${(list.size - 5)}+"
                _binding?.imgCast1?.let { it1 ->
                    Glide.with(requireContext())
                        .load(Constants.IMAGE_URL + list[0].profilePath)
                        .placeholder(R.drawable.progress_animation)
                        .into(it1)
                }
                _binding?.imgCast2?.let { it1 ->
                    Glide.with(requireContext())
                        .load(Constants.IMAGE_URL + list[1].profilePath)
                        .placeholder(R.drawable.progress_animation)
                        .into(it1)
                }
                _binding?.imgCast3?.let { it1 ->
                    Glide.with(requireContext())
                        .load(Constants.IMAGE_URL + list[2].profilePath)
                        .placeholder(R.drawable.progress_animation)
                        .into(it1)
                }
                _binding?.imgCast4?.let { it1 ->
                    Glide.with(requireContext())
                        .load(Constants.IMAGE_URL + list[3].profilePath)
                        .placeholder(R.drawable.progress_animation)
                        .into(it1)
                }
                _binding?.imgCast5?.let { it1 ->
                    Glide.with(requireContext())
                        .load(Constants.IMAGE_URL + list[4].profilePath)
                        .placeholder(R.drawable.progress_animation)
                        .into(it1)
                }
            }
        }
    }

    private var mMovieDetailsLiveData: LiveData<LiveDataResource<ApiResponse<MovieDetailsResponse>>>? =
        null
    private val mMovieDetailsLiveDataObserver: Observer<LiveDataResource<ApiResponse<MovieDetailsResponse>>> =
        Observer { data ->
            data?.let { resources ->
                when (resources.status) {
                    LiveDataResource.Status.SUCCESS -> {
                        _binding?.progressView?.visibility = View.GONE
                        updateView()
                        populateView(resources.data?.data)
                    }
                    LiveDataResource.Status.ERROR -> {
                        _binding?.progressView?.visibility = View.GONE
                    }
                    LiveDataResource.Status.LOADING -> {
                        _binding?.progressView?.visibility = View.VISIBLE
                    }
                }
            }
        }

    private fun populateView(data: MovieDetailsResponse?) {
        data?.let {
            _binding?.imgMoviePoster?.let { it1 ->
                Glide.with(requireContext())
                    .load(Constants.IMAGE_URL + it.posterPath)
                    .placeholder(R.drawable.progress_animation)
                    .into(it1)
            }
            _binding?.tvMovieName?.text = it.title
            _binding?.tvRating?.text = ((it.voteAverage ?: 0.0) * 5 * 10).div(100).toString()
            _binding?.ratingBar?.rating = ((it.voteAverage ?: 0.0) * 5 * 10).div(100).toFloat()
            _binding?.ratingBar?.setBackgroundColor(Color.WHITE)
            _binding?.tvMovieDescription?.text = it.overview
            val random1 = (0..18).random()
            val random2 = (0..18).random()
            _binding?.tvMovieInfo?.text =
                dateFormat.format(dateFormatGiven.parse(it.releaseDate)) + " | " +
                        Constants.genres[random1] + ", " + Constants.genres[random2] + " | " +
                        "${(it.runtime ?: 0).div(60)}h ${(it.runtime ?: 0).mod(60)}min"
        }
    }

    private fun updateView() {
        _binding?.rlMovie?.visibility = View.VISIBLE
        _binding?.rlBottomBar?.visibility = View.VISIBLE
        _binding?.tvCastPrompt?.visibility = View.VISIBLE
        _binding?.rlCast?.visibility = View.VISIBLE
        _binding?.imgSaveIcon?.visibility = View.VISIBLE
        _binding?.tvMovieDescription?.visibility = View.VISIBLE
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        getMovieDetails()
        getMovieCast()
    }

    private fun getMovieCast() {
        mMovieCastLiveData?.removeObserver(mMovieCastLiveDataObserver)
        mMovieCastLiveData = mViewModel.getMovieCast(args.movieId)
        mMovieCastLiveData?.observe(viewLifecycleOwner, mMovieCastLiveDataObserver)
    }

    private fun getMovieDetails() {
        mMovieDetailsLiveData?.removeObserver(mMovieDetailsLiveDataObserver)
        mMovieDetailsLiveData = mViewModel.getMovieDetails(args.movieId)
        mMovieDetailsLiveData?.observe(viewLifecycleOwner, mMovieDetailsLiveDataObserver)
    }

    private fun showNotImplementedToast() {
        Toast.makeText(requireContext(), "Not Implemented", Toast.LENGTH_SHORT).show()
    }

    private fun initView() {
        _binding?.apply {
            progressView.visibility = View.GONE
            rlMovie.visibility = View.GONE
            rlBottomBar.visibility = View.GONE
            tvCastPrompt.visibility = View.GONE
            rlCast.visibility = View.GONE
            imgSaveIcon.visibility = View.GONE
            tvMovieDescription.visibility = View.GONE
            btnBack.setOnClickListener {
                findNavController().navigateUp()
            }
            imgSaveIcon.setOnClickListener {
                showNotImplementedToast()
            }
            imgSave.setOnClickListener {
                showNotImplementedToast()
            }
            imgWatchNow.setOnClickListener {
                showNotImplementedToast()
            }
        }
    }
}