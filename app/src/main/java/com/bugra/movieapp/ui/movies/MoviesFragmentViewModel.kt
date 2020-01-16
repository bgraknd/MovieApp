package com.bugra.movieapp.ui.movies

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bugra.movieapp.data.remote.ApiDataSource
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MoviesFragmentViewModel : ViewModel() {

    private val apiDataSource = ApiDataSource()

    private val moviesLiveData: MutableLiveData<MoviesFragmentViewState> = MutableLiveData()

    init {
        loadMoviesPage()
    }

    fun getMoviesLiveData(): LiveData<MoviesFragmentViewState> = moviesLiveData

    @SuppressLint("CheckResult")
    fun loadMoviesPage() {
        Observable
            .combineLatest(
                apiDataSource.fetchPopularMovies(),
                apiDataSource.fetchNowPlayingMovies(),
                MoviePageCombiner()
            )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { moviesLiveData.value = it }
    }
}
