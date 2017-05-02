package com.leading.mvvmtest.model.data;

import com.leading.mvvmtest.model.entity.Movie;
import com.leading.mvvmtest.model.entity.Response;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by ZJ
 * 2017/5/2 10:01
 */

public interface DouBanMovieService {
    String BASE_URL = "https://api.douban.com/v2/movie/";

    @GET("top250")
    Observable<Response<List<Movie>>> getMovies(@Query("start") int start, @Query("count") int count);
}
