package com.shobhit.movieapp.rest;

import com.shobhit.movieapp.rest.model.SortResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by abcplusd on 04/12/17.
 */

public interface ApiService {

    @Headers("Content-Type: application/json")
    @GET("discover/movie")
    Observable<SortResponse> getMovieList(@Query("api_key") String apiKey, @Query("sort_by") String sortBy);
}
