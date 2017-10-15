package com.example.android.popularmoviesstage1;

import android.os.AsyncTask;

import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by kalli on 10/4/2017.
 */

public class QueryTask extends AsyncTask<URL, Void, ArrayList<Movie>> {
    String result;

    UrlReturnListener listener;


    @Override
    protected void onPreExecute() {

    }

    @Override
    protected ArrayList<Movie> doInBackground(URL... params) {
        URL url = params[0];

        HttpURLConnection urlConnection = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();


            InputStream in = urlConnection.getInputStream();
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");
            boolean hasInput = scanner.hasNext();

            if (hasInput) {
                result = scanner.next();
                //JSONObject qresult = new JSONObject(result);

                JSONObject qresult = new JSONObject(result);
                listener = new MovieUrlReturn(qresult);
                ArrayList<Movie> movies = (ArrayList<Movie>) listener.onTaskComplete();
                return movies;

            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }

        return null;
    }


}
