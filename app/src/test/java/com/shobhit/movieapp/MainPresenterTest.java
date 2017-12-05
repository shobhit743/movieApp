package com.shobhit.movieapp;

import android.support.v7.widget.RecyclerView;
import com.shobhit.movieapp.mainModule.GridViewAdapter;
import com.shobhit.movieapp.rest.model.Result;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by abcplusd on 05/12/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class MainPresenterTest {

    @Mock
    RecyclerView recyclerView;

    @Mock
    GridViewAdapter gridViewAdapter;


    @Test
    public void checkItemCount() throws Exception{
        List<Result> resultList = new ArrayList<Result>();
        gridViewAdapter.setData(resultList);
        recyclerView.setAdapter(gridViewAdapter);
        Assertions.assertThat(gridViewAdapter.getItemCount()).isEqualTo(0);
    }


}
