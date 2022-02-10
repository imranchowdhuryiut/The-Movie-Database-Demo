package com.example.demomovieapp.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.demomovieapp.data.model.Movie
import com.example.demomovieapp.data.network.ApiResponse
import com.example.demomovieapp.data.network.LiveDataResource
import com.example.demomovieapp.databinding.FragmentHomeBinding
import com.example.demomovieapp.utils.Constants
import com.example.demomovieapp.utils.OnItemClickCallback
import com.example.demomovieapp.viewModels.MovieViewModel
import com.example.demomovieapp.views.adapter.GenreListAdapter
import com.example.demomovieapp.views.adapter.MovieListAdapter
import com.example.demomovieapp.views.adapter.SearchListAdapter

class HomeFragment : Fragment(), OnItemClickCallback<Movie> {

    private var _binding: FragmentHomeBinding? = null

    private val mViewModel by viewModels<MovieViewModel>()

    private val mAdapter: MovieListAdapter = MovieListAdapter(this)

    private val mGenreAdapter: GenreListAdapter = GenreListAdapter()

    private val mSearchAdapter: SearchListAdapter = SearchListAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        getMovieList()
    }

    private var mMovieListLiveData: LiveData<LiveDataResource<ApiResponse<List<Movie>>>>? = null
    private val mMovieListLiveDataObserver: Observer<LiveDataResource<ApiResponse<List<Movie>>>> = Observer {data ->
        data?.let { resources ->
            when (resources.status) {
                LiveDataResource.Status.SUCCESS -> {
                    _binding?.progressView?.visibility = View.GONE
                    _binding?.viewPrompt?.visibility = View.VISIBLE
                    mAdapter.submitList(resources.data?.data)
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

    private fun getMovieList() {
        mMovieListLiveData?.removeObserver(mMovieListLiveDataObserver)
        mMovieListLiveData = mViewModel.getMovieList()
        mMovieListLiveData?.observe(viewLifecycleOwner, mMovieListLiveDataObserver)
    }

    private fun initView() {
        _binding?.apply {
            btnCancel.setOnClickListener {
                etSearchView.setText("")
                etSearchView.clearFocus()
                rvSearchList.visibility = View.GONE
                rvGenreList.visibility = View.GONE
                viewPrompt.visibility = View.VISIBLE
                rvMovieList.visibility = View.VISIBLE
            }
            rvGenreList.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            rvGenreList.adapter = mGenreAdapter
            mGenreAdapter.submitList(Constants.genres)
            rvSearchList.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            rvSearchList.adapter = mSearchAdapter
            rvMovieList.layoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
            rvMovieList.adapter = mAdapter
            viewPrompt.visibility = View.GONE
            rvGenreList.visibility = View.GONE
            progressView.visibility = View.GONE
            rvSearchList.visibility = View.GONE
            imgBottomBar.setOnClickListener {
                Toast.makeText(requireContext(), "Not Implemented", Toast.LENGTH_SHORT).show()
            }
            etSearchView.addTextChangedListener {
                if (it.isNullOrEmpty()) {
                    _binding?.rvSearchList?.visibility = View.GONE
                    _binding?.rvGenreList?.visibility = View.GONE
                    _binding?.viewPrompt?.visibility = View.VISIBLE
                    _binding?.rvMovieList?.visibility = View.VISIBLE
                } else {
                    _binding?.rvSearchList?.visibility = View.VISIBLE
                    _binding?.rvGenreList?.visibility = View.VISIBLE
                    _binding?.viewPrompt?.visibility = View.GONE
                    _binding?.rvMovieList?.visibility = View.GONE
                    searchMovies(it.toString())
                }
            }
        }
    }

    private fun searchMovies(query: String) {
        mViewModel.searchMovie(query).observe(viewLifecycleOwner, {
            mSearchAdapter.submitList(it)
        })
    }

    override fun onClick(model: Movie) {
        findNavController().navigate(
            HomeFragmentDirections.actionHomeFragmentToMovieDetailsFragment(model.id ?: 0)
        )
    }
}