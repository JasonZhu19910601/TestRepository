package com.leading.mvvmtest.view;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.leading.mvvmtest.R;
import com.leading.mvvmtest.databinding.MovieItemBinding;
import com.leading.mvvmtest.model.entity.Movie;
import com.leading.mvvmtest.viewModel.MovieViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZJ
 * 2017/5/2 10:26
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.BindingHolder> {
    private List<Movie> movies;

    public MovieAdapter() {
        this.movies = new ArrayList<>();
    }

    @Override
    public MovieAdapter.BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MovieItemBinding itemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.movie_item, parent, false);
        return new BindingHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(MovieAdapter.BindingHolder holder, int position) {
        MovieViewModel movieViewModel = new MovieViewModel(movies.get(position));
        holder.itemBinding.setViewModel(movieViewModel);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public void addItem(Movie movie) {
        movies.add(movie);
        notifyItemInserted(movies.size() - 1);
    }

    public void clearItems() {
        movies.clear();
        notifyDataSetChanged();
    }

    public class BindingHolder extends RecyclerView.ViewHolder {
        private MovieItemBinding itemBinding;

        public BindingHolder(MovieItemBinding itemBinding) {
            super(itemBinding.cardView);
            this.itemBinding = itemBinding;
        }
    }
}
