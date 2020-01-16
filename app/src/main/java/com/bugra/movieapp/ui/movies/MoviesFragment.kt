package com.bugra.movieapp.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bugra.movieapp.R
import com.bugra.movieapp.databinding.FragmentMoviesBinding

class MoviesFragment : Fragment() {

    private lateinit var binding: FragmentMoviesBinding

    private val popularMoviesAdapter = PopularMoviesAdapter()
    private val nowPlayingMoviesAdapter = PopularMoviesAdapter()

    private lateinit var viewModel: MoviesFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MoviesFragmentViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_movies, container, false
        )

        /*      // added layout manager
              binding.recyclerViewNowPlaying.layoutManager =
                  LinearLayoutManager(context!!, LinearLayoutManager.HORIZONTAL, false)

              binding.recyclerViewPopularMovies.layoutManager =
                  LinearLayoutManager(context!!, LinearLayoutManager.HORIZONTAL, false)
      */

        binding.recyclerViewPopularMovies.adapter = popularMoviesAdapter
        binding.recyclerViewNowPlaying.adapter = nowPlayingMoviesAdapter

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.getMoviesLiveData().observe(this, Observer {
            popularMoviesAdapter.setMovieList(it.getPopularMovies())
            nowPlayingMoviesAdapter.setMovieList(it.getNowPlayingMovies())
            binding.viewState = it
            binding.executePendingBindings()
        })
    }

    companion object {
        fun newInstance() = MoviesFragment()
    }
}
/*   private fun renderUI(moviesFragmentViewState: MoviesFragmentViewState) {
       when (moviesFragmentViewState.popularMovies.status) {
           SUCCESS -> {
               Log.e(
                   "Succes Data : ",
                   moviesFragmentViewState.popularMovies.data!!.results!!.toString()
               )
               binding.progressBarPopularMovies.visibility = View.GONE
               popularMoviesAdapter.setMovieList(moviesFragmentViewState.popularMovies.data.results!!)
           }
           LOADING -> {
               binding.progressBarPopularMovies.visibility = View.VISIBLE
           }
/*            ERROR -> Log.e("ERROR : ", moviesFragmentViewState.popularMovies.message.toString())
           else -> Log.e("Else Case : ", "Wrong Section")
*/
       }
       when (moviesFragmentViewState.nowPlayingMovies.status) {
           SUCCESS -> {
               Log.e(
                   "Succes Data : ",
                   moviesFragmentViewState.nowPlayingMovies.data!!.results!!.toString()
               )
               binding.progressBarNowPlayingMovies.visibility = View.GONE
               nowPlayingMoviesAdapter.setMovieList(moviesFragmentViewState.nowPlayingMovies.data.results!!)
           }
           LOADING -> {
               binding.progressBarNowPlayingMovies.visibility = View.VISIBLE
           }
/*            ERROR -> Log.e("ERROR : ", moviesFragmentViewState.nowPlayingMovies.message.toString())
           else -> Log.e("Else Case : ", "Wrong Section")
*/
       }
   }

   companion object {
       fun newInstance() = MoviesFragment()
   }
}
*/