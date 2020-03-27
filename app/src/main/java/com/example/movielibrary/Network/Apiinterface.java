package com.example.movielibrary.Network;

import com.example.movielibrary.Model.Movie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Apiinterface {
    @GET(Constan.MOVIE_PATH+"/popular")
    Call<Movie> popularMovies(
            @Query("page") int page
    );
}
