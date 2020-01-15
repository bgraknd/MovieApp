package com.bugra.movieapp

import com.bugra.movieapp.model.MovieResults
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class ApiDataSource {

    val retrofitClient = RetrofitClient()

    fun fetchPopularMovies(): Observable<Resource<List<MovieResults>>> {
        return Observable.create { emitter ->

            emitter.onNext(Resource.loading())

            retrofitClient
                .getMovieService()
                .getPopularMovies()
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { movieList ->
                        emitter.onNext(Resource.success(movieList))
                        emitter.onComplete()
                    },
                    { error ->
                        emitter.onNext(Resource.error(error.message ?: ""))
                        emitter.onComplete()
                    })
        }
    }

    fun fetchNowPlayingMovies(): Observable<Resource<List<MovieResults>>>
    //TODO nowPlaying
    {
        return Observable.create { emitter ->

            emitter.onNext(Resource.loading())

            retrofitClient
                .getMovieService()
                .getNowPlayingMovies()
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { movieList ->
                        emitter.onNext(Resource.success(movieList))
                        emitter.onComplete()
                    },
                    { error ->
                        emitter.onNext(Resource.error(error.message ?: ""))
                        emitter.onComplete()
                    })
        }
    }
}
