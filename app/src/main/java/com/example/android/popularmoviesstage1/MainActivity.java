package com.example.android.popularmoviesstage1;

import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        MovieGrid mv = new MovieGrid();
        transaction.replace(R.id.fragment_id, mv);
        transaction.commit();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.sort_by_top_rating) {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            MovieGrid mv = new MovieGrid();
            Bundle bundle = new Bundle();
            bundle.putString("SortByTopRating", "sortTopRating");
            mv.setArguments(bundle);
            transaction.replace(R.id.fragment_id, mv);
            transaction.commit();
            return true;
        }
        if (id == R.id.sort_by_popularity) {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            MovieGrid mv = new MovieGrid();
            Bundle bundle = new Bundle();
            bundle.putString("SortByPopularity", "sortPopularity");
            mv.setArguments(bundle);
            transaction.replace(R.id.fragment_id, mv);
            transaction.commit();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
