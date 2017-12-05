package com.shobhit.movieapp.mainModule

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import com.shobhit.movieapp.MovieApp
import com.shobhit.movieapp.R
import com.shobhit.movieapp.rest.model.Result
import android.view.View
import android.widget.*
import com.shobhit.movieapp.rest.ApiService
import javax.inject.Inject




class MainActivity : AppCompatActivity(), MainPresenter.View, AdapterView.OnItemSelectedListener {

    @Inject
    lateinit var apiService: ApiService
    private lateinit var gridlayoutManager: GridLayoutManager
    private lateinit var toolbar: android.support.v7.widget.Toolbar
    private lateinit var mPresenter: MovieListPresenter
    private lateinit var spinner: Spinner
    private lateinit var gridView: RecyclerView
    private lateinit var gridAdapter: GridViewAdapter
    private var list: MutableList<Result> = mutableListOf()
    private var spinnerList: MutableList<String> = mutableListOf()
    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        if (position == 0) {
            mPresenter.fetchMovieByPopularity()
        } else {
            mPresenter.fetchMovieByRating()
        }
    }

    override fun showNoInternetConnection() {
        showSnackBar()
    }

    private fun showSnackBar(){
        val snackbar = Snackbar
                .make(findViewById(R.id.parent_layout), getString(R.string.no_internet_connection), Snackbar.LENGTH_LONG)
        snackbar.show()

    }

    override fun showMovieByPopularity(mResult: MutableList<Result>) {
        list.clear()
        list.addAll(mResult)
        gridAdapter.notifyDataSetChanged()
    }

    override fun showMovieByRating(mResult: MutableList<Result>) {
        list.clear()
        list.addAll(mResult)
        gridAdapter.notifyDataSetChanged()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        spinnerList.add(getString(R.string.sort_by_popularity));
        spinnerList.add(getString(R.string.sort_by_rating));

        gridlayoutManager = GridLayoutManager(this, 2)
        (application as MovieApp).appComponent.inject(this)
        initViews()
        mPresenter = MovieListPresenter(this, this, apiService);
        mPresenter.fetchMovieByPopularity()
    }

    private fun initViews() {
        spinner = findViewById(R.id.spinner)
        gridView = findViewById(R.id.gridview)
        gridAdapter = GridViewAdapter(this, list)
        gridView.layoutManager = GridLayoutManager(this, 2)
        gridView.adapter = gridAdapter
        spinner.onItemSelectedListener = this
        val dataAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

    }

}
