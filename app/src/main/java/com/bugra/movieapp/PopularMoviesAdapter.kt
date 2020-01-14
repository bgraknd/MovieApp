package com.bugra.movieapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bugra.movieapp.databinding.ItemMovieBinding
import com.bugra.movieapp.model.PopularMovieResults
import com.squareup.picasso.Picasso


class PopularMoviesAdapter : RecyclerView.Adapter<PopularMoviesAdapter.MovieItemViewHolder>() {

    private var movieList = arrayListOf<PopularMovieResults>()

    fun setMovieList(movieList: List<PopularMovieResults>) {
        this.movieList = movieList as ArrayList<PopularMovieResults>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder =
        MovieItemViewHolder.create(parent)

    override fun getItemCount(): Int = movieList.size

    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) =
        holder.bind(movieList[position])

    class MovieItemViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: PopularMovieResults) {
            binding.txtMovieTitle.text = movie.title
            Picasso.get().load(movie.poster_path).into(binding.imageViewFilmPoster)
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