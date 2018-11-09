package com.android.project.jsonparsinglistviewusingvolley.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.project.jsonparsinglistviewusingvolley.MainActivity;
import com.android.project.jsonparsinglistviewusingvolley.R;
import com.android.project.jsonparsinglistviewusingvolley.model.Movie;
import com.android.volley.toolbox.NetworkImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    private ArrayList<Movie> movieList;
    private Context context;

    public MyAdapter(ArrayList<Movie> movieList, Context applicationContext) {
        this.movieList = movieList;
        this.context = applicationContext;
    }

    @Override
    public int getCount() {
        return movieList.size();
    }

    @Override
    public Object getItem(int i) {
        return movieList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater= LayoutInflater.from(context);


        View    v = inflater.inflate(R.layout.indiview,null);


        ImageView thumbNail = v.findViewById(R.id.thumbnail);
        TextView title = v.findViewById(R.id.title);
        TextView rating = v.findViewById(R.id.rating);
        TextView genre = v.findViewById(R.id.genre);
        TextView year = v.findViewById(R.id.releaseYear);

        title.setText(movieList.get(i).getTitle());
        rating.setText(String.valueOf(movieList.get(i).getRating()));
        year.setText(String.valueOf(movieList.get(i).getYear()));

        String genreStr = "";
        for (String s:movieList.get(i).getGenre()) {
            genreStr += s+",";
        }
        genreStr = genreStr.length() > 0 ? genreStr.substring(0, genreStr.length() - 2) : genreStr;
        genre.setText(genreStr);

        Picasso.with(context).load(movieList.get(i).getThumbnailUrl()).into(thumbNail);


        return v;
    }
}
