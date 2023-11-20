package com.example.udemymovie.cleanarchitecture.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.udemymovie.cleanarchitecture.presentation.Adapter.MoviesAdapter
import com.example.udemymovie.cleanarchitecture.presentation.movies.MoviesEvent
import com.example.udemymovie.cleanarchitecture.presentation.movies.MoviesViewModel
import com.example.udemymovie.databinding.FragmentMoviesBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MoviesFragment : Fragment() {

    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!

    private lateinit var moviesAdater : MoviesAdapter
    private val moviesViewModel : MoviesViewModel by viewModels()
    private var job : Job? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        moviesAdater = MoviesAdapter()
        binding.recyclerView.adapter = moviesAdater

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // Kullanıcı arama butonuna tıkladığında çalışır.
                // Bu kısmı boş bırakılabilir, çünkü otomatik arama yapmak isteniyor.
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Kullanıcı arama sorgusunu her değiştirdiğinde çalışır
                if (!newText.isNullOrBlank()){
                    moviesViewModel.onEvent(MoviesEvent.Search(newText,"1"))
                }
                return true
            }

        })

        binding.searchView.setOnCloseListener { true }

        observeViewModel()

    }

    fun observeViewModel(){

        job = viewLifecycleOwner.lifecycleScope.launch {
            moviesViewModel.state.collect { state ->
                // Update UI based on the state
                binding.progressBar.isVisible = state.isLoading
                binding.errorView.isVisible = state.error.isNotBlank()
                binding.errorView.text = state.error

                // Update movie list
                moviesAdater.setlist(state.movies)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        job?.cancel()
        _binding = null
    }

}