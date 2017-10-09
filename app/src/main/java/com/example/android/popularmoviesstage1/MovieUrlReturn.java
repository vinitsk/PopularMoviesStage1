package com.example.android.popularmoviesstage1;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by kalli on 10/6/2017.
 */

public class MovieUrlReturn implements UrlReturnListener<JSONObject,ViewGroup,LayoutInflater,Activity> {
    private JSONArray resultArray=null;
    private GridView movieGridView = null;
    private View gridMain = null;
    ArrayList<Movie> movies = new ArrayList<Movie>();


    @Override
    public View onTaskComplete(JSONObject result, final ViewGroup viewGroup, LayoutInflater layoutInflator, final Activity act) {

        try {
            resultArray = result.getJSONArray("results");
        }catch (Exception ex)
        {
            ex.printStackTrace();
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

        final MovieAdapter movieAdapter = new MovieAdapter(viewGroup.getContext(), movies);

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
                Intent singleMovie = new Intent(viewGroup.getContext(), SingleMovieActivity.class);
                singleMovie.putExtra("bundleValues", movieValues);
                act.startActivity(singleMovie);


            }
        });

      return gridMain;
      //  return null;

    }
}
