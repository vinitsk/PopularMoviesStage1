package com.example.android.popularmoviesstage1;

import android.view.View;

/**
 * Created by kalli on 10/6/2017.
 */

public interface UrlReturnListener<T,M,S,A> {

    public View onTaskComplete(T result, M viewGroup, S layoutInflator,A activity);
}
