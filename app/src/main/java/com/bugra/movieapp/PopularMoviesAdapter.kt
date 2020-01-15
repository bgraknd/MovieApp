package com.bugra.movieapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bugra.movieapp.databinding.ItemMovieBinding
import com.bugra.movieapp.model.MovieResults


class PopularMoviesAdapter : RecyclerView.Adapter<PopularMoviesAdapter.MovieItemViewHolder>() {

    private var movieList = arrayListOf<MovieResults>()

    fun setMovieList(movieList: List<MovieResults>) {
        this.movieList.clear()
        this.movieList.addAll(movieList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder =
        MovieItemViewHolder.create(parent)

    override fun getItemCount(): Int = movieList.size

    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) =
        holder.bind(movieList[position])

    class MovieItemViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movieItem: MovieResults) {
            binding.viewState = MoviesItemViewState(movieItem)
            binding.executePendingBindings()
            //binding.txtMovieTitle.text = movieItem.title
            //Picasso.get().load("https://image.tmdb.org/t/p/w500" + movieItem.poster_path).into(binding.imageViewFilmPoster)
        }

        companion object {
            fun create(parent: ViewGroup): MovieItemViewHolder {
                val binding = DataBindingUtil.inflate<ItemMovieBinding>(
                    LayoutInflater.from(parent.context), R.layout.item_movie, parent, false
                )
                return MovieItemViewHolder(binding)
            }
        }
    }
}