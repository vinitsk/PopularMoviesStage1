package com.example.android.popularmoviesstage1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by kalli on 9/24/2017.
 */

public class SingleMovieActivity extends AppCompatActivity {
private TextView mDisplayText;
    private ImageView mDisplayImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_movie_display);
       mDisplayText=(TextView)findViewById(R.id.individual_movie_text);
        mDisplayImage=(ImageView)findViewById(R.id.individual_movie_image);
        TextView synopsis_plot=(TextView)findViewById(R.id.plot_synopsis);
        TextView user_rating=(TextView)findViewById(R.id.user_rating);
        TextView releaseDate=(TextView)findViewById(R.id.release_date);

        Intent singleMovie=getIntent();
        if (singleMovie.hasExtra("bundleValues")){

            Bundle bundleEntered=singleMovie.getBundleExtra("bundleValues");
         mDisplayText.setText(bundleEntered.getString("originalTitle"));
            Picasso.with(getBaseContext()).load(bundleEntered.getString("posterUrl")).into(mDisplayImage);
            synopsis_plot.setText(bundleEntered.getString("plotSynopsis"));
            user_rating.setText(bundleEntered.getString("userRating"));
            releaseDate.setText(bundleEntered.getString("releaseDate"));
        }




    }

}
