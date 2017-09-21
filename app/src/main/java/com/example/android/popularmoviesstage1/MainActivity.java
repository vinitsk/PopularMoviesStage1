package com.example.android.popularmoviesstage1;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.StrictMode;
import android.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id=item.getItemId();

        if (id==R.id.action_settings){
            FragmentTransaction transaction=getFragmentManager().beginTransaction();
            MovieGrid lt=(MovieGrid) getFragmentManager().findFragmentById(R.id.fragment_id);
         //   getSupportFragmentManager().beginTransaction().remove(lt).commitAllowingStateLoss();

            Fragment mv=new MovieGrid();
            Bundle bundle=new Bundle();
            bundle.putString("SortByPopularity","sortPopularity");
            Log.d("bundle","lets go");
            mv.setArguments(bundle);
            transaction.replace(R.id.fragment_id,mv);
            transaction.commit();

          /*  Context context=MainActivity.this;
           Class destinationActivity=MovieGrid.;
           Intent sortByPopularity=new Intent(MainActivity.this,destinationActivity);
         sortByPopularity.putExtra("SortByPopularity","sortPopularity");
         startActivity(sortByPopularity);*/
       // String message="Search Clicked";
       //     Toast.makeText(context,message, Toast.LENGTH_LONG).show();

            return true;
        }
        return super.onOptionsItemSelected(item);
}
}
