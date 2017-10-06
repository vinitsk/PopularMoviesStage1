package com.example.android.popularmoviesstage1;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;

import android.os.Bundle;
import android.os.StrictMode;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Created by kalli on 9/9/2017.
 */

public class MovieGrid extends Fragment {


    private JSONObject result = null;
    private JSONArray resultArray = null;
    ArrayList<Movie> movies = new ArrayList<Movie>();
    private String jsonString = null;
    private GridView movieGridView = null;
    private View gridMain = null;


    public void MovieGrid() {

        Uri builtUri = Uri.parse(jsonString);

        URL url = null;

        try {
            url = new URL(builtUri.toString());

            result = new JSONObject(new QueryTask().execute(url).get());
            //result = new JSONObject(new QueryTask().doInBackground(url));
            resultArray = result.getJSONArray("results");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    @Override
    public View onCreateView(LayoutInflater layoutInflator, ViewGroup viewGroup, Bundle savedInstanceState) {


        ConnectivityManager cm =
                (ConnectivityManager) viewGroup.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();


        if (netInfo != null && netInfo.isConnected()) {
            // StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            // StrictMode.setThreadPolicy(policy);
            String jsonString = "http://api.themoviedb.org/3/movie/popular?api_key=";
            Bundle bundle = this.getArguments();


            if (bundle != null) {
                if (bundle.getString("SortByTopRating") == "sortTopRating") {
                    jsonString = "http://api.themoviedb.org/3/movie/top_rated?api_key=";

                }

                if (bundle.getString("SortByPopularity") == "sortPopularity") {
                    jsonString = "http://api.themoviedb.org/3/movie/popular?api_key=";
                }


            }

            Uri builtUri = Uri.parse(jsonString);

            URL url = null;
            try {
                url = new URL(builtUri.toString());
                result = new JSONObject(new QueryTask().execute(url).get());
                //   result = new JSONObject(new QueryTask().doInBackground(url));
                resultArray = result.getJSONArray("results");
            } catch (Exception e) {
                e.printStackTrace();
            }

            for (int n = 0; n < resultArray.length(); n++) {
                try {
                    JSONObject object = resultArray.getJSONObject(n);
                    Movie m = new Movie(object.getString("id"), object.getString("poster_path"), object.getString("original_title"), object.getString("overview"), object.getString("vote_average"), object.getString("release_date"));
                    movies.add(m);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            int gridViewMain = R.layout.grid_view_main;
            gridMain = layoutInflator.inflate(gridViewMain, viewGroup, false);

            final MovieAdapter movieAdapter = new MovieAdapter(getContext(), movies);

            movieGridView = (GridView) gridMain.findViewById(R.id.movies_grid);
            movieGridView.setAdapter(movieAdapter);
            movieGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Movie ls = movieAdapter.getMovie(position);
                    Bundle movieValues = new Bundle();
                    movieValues.putString("posterUrl", "http://image.tmdb.org/t/p/w342/" + ls.posterPath);
                    movieValues.putString("originalTitle", ls.originalTitle);
                    movieValues.putString("plotSynopsis", ls.plotSynopsis);
                    movieValues.putString("userRating", ls.userRating);
                    movieValues.putString("releaseDate", ls.releaseDate);
                    Intent singleMovie = new Intent(getContext(), SingleMovieActivity.class);
                    singleMovie.putExtra("bundleValues", movieValues);
                    startActivity(singleMovie);


                }
            });

            return gridMain;
        } else {
            Toast.makeText(viewGroup.getContext(), "There is no internet connection", Toast.LENGTH_LONG).show();
            return null;
        }


    }
}


