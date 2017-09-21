package com.example.android.popularmoviesstage1;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;



/**
 * Created by kalli on 9/9/2017.
 */

public class MovieGrid extends  Fragment {

    private MovieAdapter movieAdapter;

    private JSONObject result=null;
    private JSONArray resultArray=null;
    ArrayList<Movie>movies =new ArrayList<Movie>();
    private String jsonString=null;

public void MovieGrid(){
 //      String jsonString="http://api.themoviedb.org/3/movie/popular?api_key=";
        Uri builtUri= Uri.parse(jsonString);

        URL url=null;
        try {
            url = new URL(builtUri.toString());
            result=new JSONObject(getResponseFromHttpUrl(url));
            resultArray=result.getJSONArray("results");
        } catch (Exception e) {
            e.printStackTrace();
        }
    Log.d("myTag",result.toString());

    }





    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection=(HttpURLConnection)url.openConnection();
        try{
            InputStream in =urlConnection.getInputStream();
            Scanner scanner=new Scanner(in);
            scanner.useDelimiter("\\A");
            boolean hasInput=scanner.hasNext();
            if (hasInput){
                return scanner.next();
            }else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflator, ViewGroup viewGroup, Bundle savedInstanceState){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        String jsonString="http://api.themoviedb.org/3/movie/popular?api_key=";
       Bundle bundle=this.getArguments();
        Log.d("check1","lets see");
      //  Intent selectedIntent=getActivity().getIntent();
        if (bundle!=null){
            if (bundle.getString("SortByPopularity")=="sortPopularity"){
                jsonString="http://api.themoviedb.org/3/movie/top_rated?api_key=";
                Log.d("bundle",jsonString);
            }
        }








        Uri builtUri= Uri.parse(jsonString);

        URL url=null;
        try {
            url = new URL(builtUri.toString());
            result=new JSONObject(getResponseFromHttpUrl(url));
            resultArray=result.getJSONArray("results");
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int n=0;n<resultArray.length();n++){
            try {
                JSONObject object=resultArray.getJSONObject(n);
                Movie m=new Movie(object.getString("id"),object.getString("poster_path"));
                movies.add(m);
                Log.d("vinit",object.getString("id"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        int grid_view_main=R.layout.grid_view_main;
        View grid_main=layoutInflator.inflate(grid_view_main,viewGroup,false);

        MovieAdapter movieAdapter=new MovieAdapter(getContext(), movies);

        GridView movieGridView=(GridView)grid_main.findViewById(R.id.movies_grid);
        movieGridView.setAdapter(movieAdapter);
        return grid_main;
    }


}


