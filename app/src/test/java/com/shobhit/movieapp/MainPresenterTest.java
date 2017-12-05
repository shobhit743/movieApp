package com.shobhit.movieapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.shobhit.movieapp.Presenter.MovieListPresenter;
import com.shobhit.movieapp.Utils.Constants;
import com.shobhit.movieapp.activity.MainPresenter;
import com.shobhit.movieapp.adapter.GridViewAdapter;
import com.shobhit.movieapp.rest.ApiService;
import com.shobhit.movieapp.rest.model.Result;
import com.shobhit.movieapp.rest.model.SortResponse;

import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

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

    @Mock
    RecyclerView recyclerView;

    @Mock
    GridViewAdapter gridViewAdapter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void checkItemCount() throws Exception{
        List<Result> resultList = new ArrayList<Result>();
        gridViewAdapter.setData(resultList);
        recyclerView.setAdapter(gridViewAdapter);
        Assertions.assertThat(gridViewAdapter.getItemCount()).isEqualTo(0);
    }


}
