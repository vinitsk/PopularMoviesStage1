package com.example.android.popularmoviesstage1;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by kalli on 10/4/2017.
 */

public class QueryTask extends AsyncTask<URL, Void, String> {
    String result;

    @Override
    protected String doInBackground(URL... params) {
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
                return result;
            } else {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }

        return null;
    }


    //  @Override
    // protected void onPostExecute(String result){
    //      return result;
    //}
}
