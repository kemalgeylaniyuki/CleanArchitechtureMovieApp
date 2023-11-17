package com.example.udemymovie.cleanarchitecture.presentation.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.udemymovie.cleanarchitecture.data.remote.dto.Result
import com.example.udemymovie.databinding.MoviesItemBinding

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.MoviesHolder>() {

    var movieList : List<Result>? = emptyList()

    fun setlist(movieList : List<Result>){
        this.movieList = movieList
        notifyDataSetChanged()
    }

    class MoviesHolder(val binding : MoviesItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data : Result){

            binding.txtTitle.text = data.original_title
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

    override fun getItemCount(): Int {
        if (movieList == null){
            return 0
        }
        else{
            return movieList!!.size
        }
    }

    override fun onBindViewHolder(holder: MoviesHolder, position: Int) {
        holder.bind(movieList!!.get(position))
    }

}