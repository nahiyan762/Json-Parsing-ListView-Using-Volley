package com.android.project.jsonparsinglistviewusingvolley;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.project.jsonparsinglistviewusingvolley.Adapter.MyAdapter;
import com.android.project.jsonparsinglistviewusingvolley.model.Movie;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final String url = "https://api.androidhive.info/json/movies.json";


        final ArrayList<Movie> movieList = new ArrayList<>();

        ListView list_item = findViewById(R.id.list_item);
        final MyAdapter adapter = new MyAdapter(movieList, getApplicationContext());
        list_item.setAdapter(adapter);


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

//                Log.d(TAG,response+"======================");

                for(int i=0; i<response.length();i++){
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        Movie movie = new Movie();
                        movie.setTitle(jsonObject.getString("title"));
                        movie.setThumbnailUrl(jsonObject.getString("image"));
                        movie.setRating(((Number) jsonObject.get("rating")).doubleValue());
                        movie.setYear(jsonObject.getInt("releaseYear"));

                        JSONArray genreArry = jsonObject.getJSONArray("genre");
                        ArrayList<String> genre = new ArrayList<>();
                        for (int j = 0; j < genreArry.length(); j++) {
                            genre.add((String) genreArry.get(j));
                        }
                        movie.setGenre(genre);

                        movieList.add(movie);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG,error+"======================");
            }
        });

        Volley.newRequestQueue(getApplicationContext()).add(jsonArrayRequest);
    }
}
