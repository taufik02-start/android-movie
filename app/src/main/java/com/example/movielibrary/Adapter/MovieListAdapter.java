package com.example.movielibrary.Adapter;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.movielibrary.Model.MovieData;
import com.example.movielibrary.Network.Constan;
import com.example.movielibrary.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MoviewViewHolder> {
    private List<MovieData> movieDatas;
    private Context context;
    private OnMovieItemSelectedListener onMovieItemSelectedListener;
    public MovieListAdapter(Context context){
        this.context = context;
        movieDatas = new ArrayList<>();
    }
    public void add(MovieData item){
        movieDatas.add(item);
        notifyItemInserted(movieDatas.size()-1);
    }
    public void addAll(List<MovieData> movieDatas){
        for(MovieData movieData:movieDatas){
            add(movieData);
        }
    }

    public void remove(MovieData item){
        int position = movieDatas.indexOf(item);
        if(position>-1){
            movieDatas.remove(position);
            notifyItemRemoved(position);
        }
    }
    public void clear(){
        while(getItemCount()>0){
            remove(getItem(0));
        }
    }
    private MovieData getItem(int i){
        return movieDatas.get(i);
    }

    @NonNull
    @Override
    public MoviewViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item,viewGroup,false);
        final MoviewViewHolder moviewViewHolder = new MoviewViewHolder(view);
        moviewViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPos = moviewViewHolder.getAdapterPosition();
                if(adapterPos != RecyclerView.NO_POSITION){
                    if(onMovieItemSelectedListener != null){
                        onMovieItemSelectedListener.onItemClick(moviewViewHolder.itemView,adapterPos);
                    }
                }
            }
        });
        return moviewViewHolder;
    }

    public void setOnMovieItemSelectedListener(OnMovieItemSelectedListener onMovieItemSelectedListener){
        this.onMovieItemSelectedListener = onMovieItemSelectedListener;
    }

    @Override
    public void onBindViewHolder(@NonNull MoviewViewHolder moviewViewHolder, int i) {
        final MovieData movieData = movieDatas.get(i);
        moviewViewHolder.bind(movieData);
    }

    @Override
    public int getItemCount() {
        return movieDatas.size();
    }

    public class MoviewViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        public MoviewViewHolder(@NonNull View itemView) {
            super(itemView);
            img = (ImageView)itemView.findViewById(R.id.img_preview);
        }

        public void bind(MovieData movieData) {
            Picasso.get()
                    .load(Constan.IMG_URL+movieData.getPosterPath())
                    .into(img);
        }
    }

    private interface OnMovieItemSelectedListener {
        void onItemClick(View v,int position);
    }
}
