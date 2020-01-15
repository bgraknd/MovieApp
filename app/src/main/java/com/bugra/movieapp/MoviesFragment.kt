package com.bugra.movieapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.bugra.movieapp.Status.LOADING
import com.bugra.movieapp.Status.SUCCESS
import com.bugra.movieapp.databinding.FragmentMoviesBinding
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MoviesFragment : Fragment() {

    private lateinit var binding: FragmentMoviesBinding

    val apiDataSource = ApiDataSource()

    private val popularMoviesAdapter = PopularMoviesAdapter()
    private val nowPlayingMoviesAdapter = PopularMoviesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movies, container, false)

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

        fetchMoviePage()
    }

    @SuppressLint("CheckResult")
    private fun fetchMoviePage() {

        val popularMoviesObservable = apiDataSource.fetchPopularMovies()
        val nowPlayingMoviesObservable = apiDataSource.fetchNowPlayingMovies()

        Observable.combineLatest(
            popularMoviesObservable,
            nowPlayingMoviesObservable,
            MoviePageCombiner()
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.v(
                    "TEST",
                    it.popularMovies.status.toString() + " " + it.nowPlayingMovies.status.toString()
                )
                renderUI(it)
            }
    }

    private fun renderUI(moviesFragmentViewState: MoviesFragmentViewState) {
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
