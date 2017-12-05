package com.shobhit.movieapp.detailModule;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.shobhit.movieapp.R;
import com.shobhit.movieapp.Utils.Constants;
import com.shobhit.movieapp.rest.model.Result;

public class DetailActivity extends AppCompatActivity implements DetailPresenter.Views {

    ImageView ivImage;
    TextView txtReleaseDate, txtSynopsis, txtRating;
    Button btnLike;
    Result result;
    public static String DESC_KEY = "desc_KEY";
    CollapsingToolbarLayout collapsingToolbarLayout;
    android.support.v7.widget.Toolbar toolbar;
    DetailViewPresenter detailPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        detailPresenter = new DetailViewPresenter(this, this);
        result = (Result) getIntent().getSerializableExtra(DESC_KEY);
        ivImage = findViewById(R.id.iv_image);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapse_toolbar);
        collapsingToolbarLayout.setTitleEnabled(false);
        toolbar = findViewById(R.id.toolbar);
        txtReleaseDate = findViewById(R.id.txt_release_date);
        txtSynopsis = findViewById(R.id.txt_synopsis);
        txtRating = findViewById(R.id.txt_rating);
        btnLike = findViewById(R.id.btn_like);
        btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detailPresenter.saveRealmData(result.getId());
            }
        });

        if(result!=null)
        setData();

        detailPresenter.getDataFromRealm(result.getId());
    }


    private void setData() {
        if (result.getTitle() != null) {
            toolbar.setTitle(result.getTitle().toUpperCase());
        } else {
            toolbar.setTitle(getString(R.string.n_a));
        }
        if (result.getVoteAverage() != null) {
            txtRating.setText(getString(R.string.rating) + result.getVoteAverage());
        } else {
            txtRating.setText(getString(R.string.rating) + getString(R.string.n_a));
        }
        if (result.getOverview() != null&&!result.getOverview().isEmpty()) {
            txtSynopsis.setText(getString(R.string.synopsis) + result.getOverview());
        } else {
            txtSynopsis.setText(getString(R.string.synopsis) + getString(R.string.n_a));
        }
        if (result.getReleaseDate() != null&&!result.getReleaseDate().isEmpty()) {
            txtReleaseDate.setText(getString(R.string.releasedate) + result.getReleaseDate());
        } else {
            txtReleaseDate.setText(getString(R.string.releasedate) + getString(R.string.n_a));

        }
        Glide.with(this).load(Constants.IMAGE_BASE_URL + result.getPosterPath()).placeholder(R.mipmap.ic_launcher).diskCacheStrategy(DiskCacheStrategy.NONE).into(ivImage);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        detailPresenter.closeRealm();
    }

    @Override
    public void setLikeBtn() {
        btnLike.setBackgroundColor(getResources().getColor(R.color.yellow));
        btnLike.setTextColor(getResources().getColor(R.color.black));
        btnLike.setText(getString(R.string.liked));

    }

    @Override
    public void setUnlikeBtn() {
        btnLike.setBackgroundColor(getResources().getColor(R.color.grey));
        btnLike.setText(getString(R.string.like));
        btnLike.setTextColor(getResources().getColor(R.color.white));

    }
}

