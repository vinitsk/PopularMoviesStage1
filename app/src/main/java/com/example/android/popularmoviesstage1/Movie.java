package com.example.android.popularmoviesstage1;

/**
 * Created by kalli on 9/9/2017.
 */

public class Movie {
    String movieID;
    String originalTitle;
    String posterPath;
    String plotSynopsis;
    String userRating;
    String releaseDate;


    public Movie(String movieID,String posterPath,String originalTitle,String plotSynopsis,String userRating,String releaseDate){
        this.movieID=movieID;
        this.posterPath=posterPath;
        this.originalTitle=originalTitle;
        this.plotSynopsis=plotSynopsis;
        this.userRating=userRating;
        this.releaseDate=releaseDate;
    }

}
