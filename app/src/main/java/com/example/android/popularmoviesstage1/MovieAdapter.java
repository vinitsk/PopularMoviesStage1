package com.example.android.popularmoviesstage1;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kalli on 9/9/2017.
 */

public class MovieAdapter extends ArrayAdapter<Movie> {
    final static String baseUrl="http://api.themoviedb.org/3/movie/popular?api_key=";

    final static String imageURL="http://image.tmdb.org/t/p/w154/";

    public MovieAdapter(Context context, ArrayList<Movie> movieNames)
    {
       super(context,R.layout.movie_display,movieNames);
    }

    public interface GridItemClickListener{
        void onGridItemClick(int clickeItemIndex);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup Parent)
    {
        Movie mov=getItem(position);
        String imageURLToPass=imageURL+mov.posterPath;

        if(convertView==null){
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.movie_display,Parent,false);
        }

        Log.d("letssee",imageURLToPass);
        ImageView iconView = (ImageView) convertView.findViewById(R.id.movie_image);
        Picasso.with(Parent.getContext()).load(imageURLToPass).into(iconView);

   //     TextView versionNameView = (TextView) convertView.findViewById(R.id.movie_text);
    //    versionNameView.setText(mov.itemName
       //         + " - " + mov.price );

        return convertView;
    }
}
