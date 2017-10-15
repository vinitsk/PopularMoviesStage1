package com.example.android.popularmoviesstage1;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by kalli on 10/6/2017.
 */

public class MovieUrlReturn implements UrlReturnListener {
    private JSONArray resultArray = null;

    ArrayList<Movie> movies = new ArrayList<Movie>();
    private JSONObject result = null;

    public MovieUrlReturn(JSONObject result) {
        this.result = result;
    }


    @Override
    public ArrayList<Movie> onTaskComplete() {

        try {
            resultArray = result.getJSONArray("results");
        } catch (Exception ex) {
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


        return movies;


    }
}
