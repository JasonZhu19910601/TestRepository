package com.leading.mvvmtest.viewModel;

import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.leading.mvvmtest.R;
import com.leading.mvvmtest.model.entity.Movie;

/**
 * Created by ZJ
 * 2017/5/2 10:45
 */

public class MovieViewModel extends BaseObservable {
    private Movie movie;

    public MovieViewModel(Movie movie) {
        this.movie = movie;
    }

    @BindingAdapter({"app:imageUrl"})
    public static void loadImage(ImageView imageView, String url) {
        Glide.with(imageView.getContext())
                .load(url)
                .placeholder(R.drawable.cover)
                .error(R.mipmap.ic_launcher_round)
                .into(imageView);
    }

    public String getCoverUrl() {
        return movie.getImages().getSmall();
    }

    public String getTitle() {
        return movie.getTitle();
    }

    public float getRating() {
        return movie.getRating().getAverage();
    }

    public String getRatingText() {
        return String.valueOf(movie.getRating().getAverage());
    }

    public String getYear() {
        return movie.getYear();
    }

    public String getMovieType() {
        StringBuilder builder = new StringBuilder();
        for (String s : movie.getGenres()) {
            builder.append(s).append(" ");
        }
        return builder.toString();
    }

    public String getImageUrl() {
        return movie.getImages().getSmall();
    }
}
