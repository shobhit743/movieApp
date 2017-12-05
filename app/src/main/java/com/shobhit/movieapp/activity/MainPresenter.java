package com.shobhit.movieapp.activity;

import com.shobhit.movieapp.rest.model.Result;

import java.util.List;

/**
 * Created by abcplusd on 04/12/17.
 */

public class MainPresenter {
    public interface View {

        void showMovieByPopularity(List<Result> mResult);
        void showMovieByRating(List<Result> mResult);
        void fetchingDataStarted();
        void fetchingDataOnError();
        void fetchingDataCompleted();


    }
    public interface Actions{

        void fetchMovieByPopularity();
        void fetchMovieByRating();

    }

}
