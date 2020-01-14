package com.bugra.movieapp

import com.bugra.movieapp.BuildConfig.API_KEY
import com.bugra.movieapp.model.PopularMovies
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class ApiDataSource {

    val retrofitClient = RetrofitClient()

    fun fetchPopularMovies(): Observable<Resource<PopularMovies>> {
        return Observable.create { emitter ->

            emitter.onNext(Resource.loading())

            retrofitClient
                .getMovieService()
                .getPopularMovies(API_KEY)
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

    fun fetchNowPlayingMovies(): Observable<Resource<PopularMovies>>
    //TODO nowPlaying
    {
        return Observable.create { emitter ->

            emitter.onNext(Resource.loading())

            retrofitClient
                .getMovieService()
                .getNowPlayingMovies(API_KEY)
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
