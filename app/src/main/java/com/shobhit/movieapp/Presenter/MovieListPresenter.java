package com.shobhit.movieapp.Presenter;

import android.content.Context;

import com.shobhit.movieapp.MovieApp;
import com.shobhit.movieapp.Utils.Constants;
import com.shobhit.movieapp.Utils.ProgressController;
import com.shobhit.movieapp.activity.MainPresenter;
import com.shobhit.movieapp.rest.ApiService;
import com.shobhit.movieapp.rest.model.SortResponse;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by abcplusd on 04/12/17.
 */

public class MovieListPresenter implements MainPresenter.Actions {

    ApiService apiService;
    private final MainPresenter.View mView;
    private Context context;

    public MovieListPresenter(MainPresenter.View view, Context context,ApiService apiService) {
        this.mView = view;
        this.context = context;
        this.apiService = apiService;
    }

    @Override
    public void fetchMovieByPopularity() {
        loadApiForPopularMovies();
    }

    @Override
    public void fetchMovieByRating() {
        loadApiForRatedMovies();
    }

    private void loadApiForPopularMovies() {
        mView.fetchingDataStarted();
        Observable loginResponseObservable = apiService.getMovieList(Constants.API_KEY, "popularity.desc");
        loginResponseObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new DisposableObserver<SortResponse>() {
                               @Override
                               public void onComplete() {
                                   mView.fetchingDataCompleted();
                               }

                               @Override
                               public void onError(Throwable e) {
                                   mView.fetchingDataOnError();

                               }

                               @Override
                               public void onNext(SortResponse response) {

                                   if (response != null) {
                                       mView.showMovieByPopularity(response.getResults());
                                   }
                               }
                           }

                );

    }

    private void loadApiForRatedMovies() {

        ProgressController.getInstance().showProgress(context, "Loading");
        Observable loginResponseObservable = apiService.getMovieList(Constants.API_KEY, "vote_average.desc");
        loginResponseObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new DisposableObserver<SortResponse>() {
                               @Override
                               public void onComplete() {
                                   ProgressController.getInstance().m_Dialog.dismiss();
                               }

                               @Override
                               public void onError(Throwable e) {
                                   ProgressController.getInstance().m_Dialog.dismiss();
                               }

                               @Override
                               public void onNext(SortResponse response) {
                                   if (response != null) {
                                       mView.showMovieByRating(response.getResults());
                                   }
                               }
                           }

                );

    }

}
