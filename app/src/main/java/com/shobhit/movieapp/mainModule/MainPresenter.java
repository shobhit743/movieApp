package com.shobhit.movieapp.mainModule;

import com.shobhit.movieapp.rest.model.Result;

import java.util.List;

/**
 * Created by abcplusd on 04/12/17.
 */

public class MainPresenter {
    public interface View {

        void showMovieByPopularity(List<Result> mResult);
        void showMovieByRating(List<Result> mResult);
        void showNoInternetConnection();
    }
    public interface Actions{

        void fetchMovieByPopularity();
        void fetchMovieByRating();

    }

}
