package com.bugra.movieapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bugra.movieapp.BuildConfig.API_KEY
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesFragment : Fragment() {

    private val retrofitClient = RetrofitClient()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        retrofitClient
            .getMovieService()
            .getPopularMovies(API_KEY)
            .enqueue(object : Callback<List<PopularMovies>> {
                override fun onFailure(call: Call<List<PopularMovies>>, t: Throwable) {
                    Log.v("TEST", "Failed")
                }

                override fun onResponse(call: Call<List<PopularMovies>>, response: Response<List<PopularMovies>>) {
                    Log.v("TEST", "Success: ${response.body().toString()}")
                }
            })
    }

    companion object {
        fun newInstance() = MoviesFragment()
    }
}
