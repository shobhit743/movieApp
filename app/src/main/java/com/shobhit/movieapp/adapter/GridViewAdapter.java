package com.shobhit.movieapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.shobhit.movieapp.R;
import com.shobhit.movieapp.Utils.Constants;
import com.shobhit.movieapp.activity.DetailActivity;
import com.shobhit.movieapp.rest.model.Result;

import java.io.Serializable;
import java.util.List;

/**
 * Created by abcplusd on 04/12/17.
 */

public class GridViewAdapter extends RecyclerView.Adapter<GridViewAdapter.ViewHolder> implements Serializable {

    private Context context;
    private final List<Result> resultValues;
    LayoutInflater layoutInflater;

    public GridViewAdapter(Context context, List<Result> resultValues) {

        this.context = context;
        this.resultValues = resultValues;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view1 = layoutInflater.inflate(R.layout.grid_item_layout, parent, false);
        ViewHolder holder = new ViewHolder(view1);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Glide.with(context).load(Constants.IMAGE_BASE_URL + resultValues.get(position).getPosterPath()).placeholder(R.mipmap.ic_launcher).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setOnClick(resultValues.get(holder.getAdapterPosition()));
            }
        });

    }

    @Override
    public int getItemCount() {
        return resultValues.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_movie);
        }
    }




    public void setOnClick(Result result) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(DetailActivity.DESC_KEY, result);
        context.startActivity(intent);
    }

    public void setData(List<Result> mResult) {
        resultValues.clear();
        resultValues.addAll(mResult);
        notifyDataSetChanged();
    }
}

