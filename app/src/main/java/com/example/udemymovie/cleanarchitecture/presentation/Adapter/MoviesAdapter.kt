package com.example.udemymovie.cleanarchitecture.presentation.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.udemymovie.cleanarchitecture.data.remote.dto.Result
import com.example.udemymovie.cleanarchitecture.domain.model.Movie
import com.example.udemymovie.databinding.MoviesItemBinding

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.MoviesHolder>() {

    var movieList : List<Movie> = emptyList()

    fun setlist(movieList : List<Movie>){
        this.movieList = movieList
        notifyDataSetChanged()
    }

    class MoviesHolder(val binding : MoviesItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data : Movie){

            binding.txtTitle.text = data.title
            binding.txtReleaseDate.text = data.release_date
            binding.txtVoteAverage.text = data.vote_average.toString() + "/10"

            Glide.with(binding.posterView)
                .load("https://image.tmdb.org/t/p/w342/" + data.poster_path)
                .into(binding.posterView)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesHolder {
        val view = MoviesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesHolder(view)
    }

    override fun getItemCount(): Int = movieList.size


    override fun onBindViewHolder(holder: MoviesHolder, position: Int) {
        holder.bind(movieList!!.get(position))
    }

}