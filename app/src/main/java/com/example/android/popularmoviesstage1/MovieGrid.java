package com.example.android.popularmoviesstage1;


import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;

import android.os.Bundle;
import android.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


/**
 * Created by kalli on 9/9/2017.
 */

public class MovieGrid extends Fragment {


    ArrayList<Movie> movies = new ArrayList<Movie>();

    private GridView movieGridView = null;
    private View gridMain = null;

    private MovieAdapter movieAdapter = null;


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ConnectivityManager cm =
                (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();


        if (netInfo != null && netInfo.isConnected()) {

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
            URL url = null;
            Uri builtUri = Uri.parse(jsonString);
            try {
                url = new URL(builtUri.toString());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            new QueryTask() {
                @Override
                protected void onPostExecute(ArrayList<Movie> result) {
                    super.onPostExecute(result);
                    try {

                        movies = result;
                        movieAdapter = new MovieAdapter(getActivity(), movies);
                        movieGridView.setAdapter(movieAdapter);
                        movieAdapter.notifyDataSetChanged();

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }

            }.execute(url);

        } else {
            Toast.makeText(getActivity(), "There is no internet connection", Toast.LENGTH_LONG).show();

        }

    }


    @Override
    public View onCreateView(LayoutInflater layoutInflator, ViewGroup viewGroup, Bundle savedInstanceState) {


        int gridViewMain = R.layout.grid_view_main;
        gridMain = layoutInflator.inflate(gridViewMain, viewGroup, false);

        movieAdapter = new MovieAdapter(getActivity(), movies);

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
                Intent singleMovie = new Intent(getActivity(), SingleMovieActivity.class);
                singleMovie.putExtra("bundleValues", movieValues);
                startActivity(singleMovie);


            }
        });

        return gridMain;

    }
}



