package com.shobhit.movieapp;

import android.content.Context;
import com.shobhit.movieapp.Presenter.MovieListPresenter;
import com.shobhit.movieapp.Utils.Constants;
import com.shobhit.movieapp.activity.MainPresenter;
import com.shobhit.movieapp.rest.ApiService;
import com.shobhit.movieapp.rest.model.SortResponse;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by abcplusd on 05/12/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class MainPresenterTest {

    @Mock
    ApiService apiService;

    @Mock
    private SortResponse sortResponse;

    MovieListPresenter mPresenter;

    MainPresenter.View mMockView;

    MovieListPresenter movieListPresenter;

    @Mock
    Context context;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mMockView = mock(MainPresenter.View.class);
        movieListPresenter = new MovieListPresenter(mMockView, context,apiService);
    }

    @Test
    public void CheckForApiCall() {
        when(apiService.getMovieList(Constants.API_KEY,"popularity.desc")).thenReturn(io.reactivex.Observable.<SortResponse>empty());
        verify(mMockView, never()).fetchingDataCompleted();
    }



}
