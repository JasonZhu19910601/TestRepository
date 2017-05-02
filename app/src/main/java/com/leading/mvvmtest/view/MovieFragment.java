package com.leading.mvvmtest.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leading.mvvmtest.R;
import com.leading.mvvmtest.databinding.MovieFragmentBinding;
import com.leading.mvvmtest.viewModel.MainViewModel;

/**
 * MovieFragment,{@link Fragment} subclass.
 */
public class MovieFragment extends Fragment implements CompletedListener, SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = "MovieFragment";
    private MainViewModel viewModel;
    private MovieFragmentBinding movieFragmentBinding;
    private MovieAdapter movieAdapter;

    public static MovieFragment getInstance() {
        return new MovieFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.movie_fragment, container, false);
        movieFragmentBinding = MovieFragmentBinding.bind(contentView);
        initData();
        return contentView;
    }

    private void initData() {
        movieAdapter = new MovieAdapter();
        movieFragmentBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        movieFragmentBinding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        movieFragmentBinding.recyclerView.setAdapter(movieAdapter);
        movieFragmentBinding.swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark);
        movieFragmentBinding.swipeRefreshLayout.setOnRefreshListener(this);
        viewModel = new MainViewModel(movieAdapter, this);
        movieFragmentBinding.setViewModel(viewModel);

    }

    @Override
    public void onRefresh() {
        movieAdapter.clearItems();
        viewModel.refreshData();
    }

    @Override
    public void onCompleted() {
        if (movieFragmentBinding.swipeRefreshLayout.isRefreshing()) {
            movieFragmentBinding.swipeRefreshLayout.setRefreshing(false);
        }
    }
}
