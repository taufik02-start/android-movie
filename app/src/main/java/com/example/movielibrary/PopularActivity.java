package com.example.movielibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.movielibrary.Adapter.MovieListAdapter;
import com.example.movielibrary.Model.Movie;
import com.example.movielibrary.Network.ApiService;

import java.net.SocketTimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PopularActivity extends AppCompatActivity {
    ApiService apiService;
    private MovieListAdapter movieListAdapter;
    private int page = 1;
    private GridLayoutManager gridLayoutManager;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popular);
        movieListAdapter = new MovieListAdapter(this);
        recyclerView = (RecyclerView)findViewById(R.id.rv_movies);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(movieListAdapter);
        loadData();
    }

    private void loadData(){
        apiService = new ApiService();
        apiService.getPopularMoview(page, new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Movie movie = (Movie) response.body();
                if(movie != null){
                    movieListAdapter.addAll(movie.getResults());
                }else{
                    Toast.makeText(getBaseContext(),"No Data",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                if(t instanceof SocketTimeoutException){
                    Toast.makeText(getBaseContext(),"Request Timeout",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getBaseContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
