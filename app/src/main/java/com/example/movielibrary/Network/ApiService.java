package com.example.movielibrary.Network;

import com.example.movielibrary.BuildConfig;

import java.io.IOException;
import java.sql.Time;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {
    private Apiinterface apiinterface;

    public  ApiService(){
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl(Constan.BASE_URL)
                .client(builder())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiinterface = retrofit.create(Apiinterface.class);
    }
    private OkHttpClient builder(){
        OkHttpClient.Builder okHttpClient = new OkHttpClient().newBuilder();
        okHttpClient.connectTimeout(20, TimeUnit.SECONDS);
        okHttpClient.writeTimeout(20, TimeUnit.SECONDS);
        okHttpClient.readTimeout(20, TimeUnit.SECONDS);
        if(BuildConfig.DEBUG){
            okHttpClient.addInterceptor(interceptor());
        }
        okHttpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept( Chain chain) throws IOException {
                Request request = chain.request();
                HttpUrl url = request.url()
                        .newBuilder()
                        .addQueryParameter("api_key",Constan.API_KEY)
                        .addQueryParameter("language",Constan.LANG_EN)
                        .build();
                request = request.newBuilder().url(url).build();
                return chain.proceed(request);
            }
        });
        return okHttpClient.build();
    }
    private HttpLoggingInterceptor interceptor(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }
    public void getPopularMoview(int page, Callback callback){
        apiinterface.popularMovies(page).enqueue(callback);
    }
}
