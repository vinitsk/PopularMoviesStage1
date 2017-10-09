package com.example.android.popularmoviesstage1;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.*;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by kalli on 10/4/2017.
 */

public class QueryTask extends AsyncTask<URL, Void, View> {
    String result;
    Context context = null;
    UrlReturnListener listener;
    LayoutInflater layoutInflator;
    ViewGroup viewGroup;
    private View gridMain = null;
    Activity act=null;
    MovieGrid movieGrid=null;
 //   private final ProgressDialog dialog = new ProgressDialog(getContext());

    public QueryTask(MovieUrlReturn listener, ViewGroup viewGroup, LayoutInflater layoutInflater, Activity act,MovieGrid movieGrid) {
        //  this.context=context;
        this.listener = listener;
        this.layoutInflator=layoutInflater;
        this.viewGroup=viewGroup;
        this.act=act;
        this.movieGrid=movieGrid;
    }

    @Override
    protected void onPreExecute(){
   //     this.dialog.setMessage("Processing...");
     //   this.dialog.show();
    }

    @Override
    protected View doInBackground(URL... params) {
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
                JSONObject qresult = new JSONObject(result);

                return listener.onTaskComplete(qresult,viewGroup,layoutInflator,act);
              //  return result;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }  finally {
            urlConnection.disconnect();
        }

        return null;
    }


      /* @Override
        protected void onPostExecute(String result) {
            //    super.onPostExecute(result);
            try {
                JSONObject qresult = new JSONObject(result);

                listener.onTaskComplete(qresult,viewGroup,layoutInflator,act);

                return listener;
                //      this.dialog.setMessage("Processed...");
           //     this.dialog.show();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }*/
}
