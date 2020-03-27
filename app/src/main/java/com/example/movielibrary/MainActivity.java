package com.example.movielibrary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    Button btnPopular, btnTrending;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnPopular = (Button)findViewById(R.id.popular);
        btnTrending = (Button)findViewById(R.id.trending);
        btnPopular.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.popular:
                Intent intentPopular = new Intent(this,PopularActivity.class);
                startActivity(intentPopular);
                break;
            case R.id.trending:
                Intent intentTrending = new Intent(this,TrendingActivity.class);
                startActivity(intentTrending);
                break;

        }
    }
}
