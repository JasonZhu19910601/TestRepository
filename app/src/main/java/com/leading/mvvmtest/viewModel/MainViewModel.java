package com.leading.mvvmtest.viewModel;

import android.databinding.ObservableField;
import android.util.Log;
import android.view.View;

import com.leading.mvvmtest.model.data.RetrofitHelper;
import com.leading.mvvmtest.model.entity.Movie;
import com.leading.mvvmtest.view.CompletedListener;
import com.leading.mvvmtest.view.MovieAdapter;

import rx.Subscriber;

/**
 * Created by ZJ
 * 2017/5/2 9:56
 */

public class MainViewModel {
    private static final String TAG = "MainViewModel";
    public ObservableField<Integer> contentViewVisibility;
    public ObservableField<Integer> progressBarVisibility;
    public ObservableField<Integer> errorInfoLayoutVisibility;
    public ObservableField<String> exception;
    private Subscriber<Movie> subscriber;
    private MovieAdapter movieAdapter;
    private CompletedListener completedListener;

    public MainViewModel(MovieAdapter movieAdapter, CompletedListener completedListener) {
        this.movieAdapter = movieAdapter;
        this.completedListener = completedListener;
        initData();
        getMovies();
    }

    private void getMovies() {
        subscriber = new Subscriber<Movie>() {
            @Override
            public void onCompleted() {
                Log.d("[MainViewModel]", "onCompleted");
                hideAll();
                contentViewVisibility.set(View.VISIBLE);
                completedListener.onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: ", e);
                hideAll();
                errorInfoLayoutVisibility.set(View.VISIBLE);
                exception.set(e.getMessage());
            }

            @Override
            public void onNext(Movie movie) {
                Log.i(TAG, "onNext: " + movie.toString());
                movieAdapter.addItem(movie);
            }
        };
        RetrofitHelper.getInstance().getMovies(subscriber, 0, 20);
    }

    public void refreshData() {
        getMovies();
    }

    private void initData() {
        contentViewVisibility = new ObservableField<>();
        progressBarVisibility = new ObservableField<>();
        errorInfoLayoutVisibility = new ObservableField<>();
        exception = new ObservableField<>();
        contentViewVisibility.set(View.GONE);
        errorInfoLayoutVisibility.set(View.GONE);
        progressBarVisibility.set(View.VISIBLE);
    }

    private void hideAll() {
        contentViewVisibility.set(View.GONE);
        errorInfoLayoutVisibility.set(View.GONE);
        progressBarVisibility.set(View.GONE);
    }
}
