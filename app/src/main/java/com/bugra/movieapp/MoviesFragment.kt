package com.bugra.movieapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MoviesFragment : Fragment() {

    val apiDataSource = ApiDataSource()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        fetchMoviePage()
    }

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
            }
    }

    companion object {
        fun newInstance() = MoviesFragment()
    }
}
