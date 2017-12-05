package com.shobhit.movieapp.mainModule;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.MalformedJsonException;

import com.shobhit.movieapp.R;
import com.shobhit.movieapp.Utils.Constants;
import com.shobhit.movieapp.Utils.ProgressController;
import com.shobhit.movieapp.rest.ApiService;
import com.shobhit.movieapp.rest.model.SortResponse;

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

        if(isNetworkAvailable()) {
            loadApiForPopularMovies();
        }
        else {
            mView.showNoInternetConnection();
        }
    }

    @Override
    public void fetchMovieByRating() {
        if(isNetworkAvailable()) {
            loadApiForRatedMovies();
        }
        else {
            mView.showNoInternetConnection();
        }
    }

    private void loadApiForPopularMovies() {
        ProgressController.getInstance().showProgress(context, context.getString(R.string.loading));
        Observable loginResponseObservable = apiService.getMovieList(Constants.API_KEY, Constants.POPULARITY);
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
                                   ProgressController.getInstance().m_Dialog.dismiss();
                                   if (response != null) {
                                       mView.showMovieByPopularity(response.getResults());
                                   }
                               }
                           }

                );

    }

    private void loadApiForRatedMovies() {

        ProgressController.getInstance().showProgress(context, context.getString(R.string.loading));
        Observable loginResponseObservable = apiService.getMovieList(Constants.API_KEY, Constants.RATING);
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
                                   if(e instanceof MalformedJsonException) {
                                       mView.showNoInternetConnection();
                                   }
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

    public boolean isNetworkAvailable() {
        try{
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getNetworkInfo(0);
            if (netInfo != null && netInfo.getState()==NetworkInfo.State.CONNECTED) {
                return true;
            }else {
                netInfo = cm.getNetworkInfo(1);
                if(netInfo!=null && netInfo.getState()==NetworkInfo.State.CONNECTED)
                    return true;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;

    }



}
