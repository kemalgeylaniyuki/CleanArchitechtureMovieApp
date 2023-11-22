package com.example.udemymovie.cleanarchitecture.presentation.movie_detail.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.udemymovie.cleanarchitecture.presentation.movie_detail.MovieDetailViewModel
import com.example.udemymovie.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val movieDetailViewModel: MovieDetailViewModel by viewModels()
    private var job: Job? = null

    private var id = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            id = DetailFragmentArgs.fromBundle(it).id
        }

        movieDetailViewModel.getMovieDetail(id)

        observeViewModel()

    }


    fun observeViewModel() {

        job = viewLifecycleOwner.lifecycleScope.launch {
            movieDetailViewModel.state.collect{

                it.movie?.let {
                    binding.textViewTitleView.text = "Name : " + it.original_title
                    binding.textViewOverView.text = it.overview
                    binding.textViewLanguageView.text = "Language : " + it.original_language
                    binding.textViewReleaseView.text = "Release Date : " + it.release_date
                    binding.textViewVoteAverage.text = "IMDB : " + it.vote_average.toString()

                    Glide.with(binding.imageView)
                        .load("https://image.tmdb.org/t/p/w342/" + it.poster_path)
                        .into(binding.imageView)
                }

                if (it.error.isNotBlank()){
                    binding.errorTextView.text = it.error
                    binding.errorTextView.visibility = View.VISIBLE
                }
                else {
                    binding.errorTextView.visibility = View.GONE
                }

                if (it.isLoading){
                    binding.progressBar2.visibility = View.VISIBLE
                }
                else {
                    binding.progressBar2.visibility = View.GONE
                }

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        job?.cancel()
        _binding = null
    }

}