package com.example.movielist.network;

import com.example.movielist.models.categories.Result;
import com.example.movielist.models.movies.MovieDetails;
import com.example.movielist.models.movies.MoviesRoot;
import com.example.movielist.models.series.SeriesDetails;
import com.example.movielist.models.series.SeriesRoot;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GetDataService {

    String API_KEY = RetrofitClientInstance.API_KEY;

    // Api Calls for Movies
    @GET("movie/now_playing?api_key="+API_KEY+"&language=en&page=1")
    Call<MoviesRoot> getNowPlayingMovies();

    @GET("movie/popular?api_key="+API_KEY+"&language=en&page=1")
    Call<MoviesRoot> getPopularMovies();

    @GET("movie/top_rated?api_key="+API_KEY+"&language=en&page=1")
    Call<MoviesRoot> getTopRatedMovies();

    @GET("movie/upcoming?api_key="+API_KEY+"&language=en&page=1")
    Call<MoviesRoot> getUpcomingMovies();

    @GET("genre/movie/list?api_key="+API_KEY+"&language=en")
    Call<Result> getMoviesCategories();

    @GET("movie/{movieId}?api_key="+API_KEY+"&language=en")
    Call<MovieDetails> getMovieDetails(@Path("movieId") int movieId);

    @GET("movie/{movieId}/recommendations?api_key="+API_KEY+"&language=en-US&page=1")
    Call<MoviesRoot> getRecommendedMovies(@Path("movieId") int movieId);

    @GET("movie/{movieId}/similar?api_key="+API_KEY+"&language=en-US&page=1")
    Call<MoviesRoot> getSimilarMovies(@Path("movieId") int movieId);

    // Api Calls for Series
    @GET("genre/tv/list?api_key="+API_KEY+"&language=en")
    Call<Result> getSeriesCategories();

    @GET("tv/airing_today?api_key="+API_KEY+"&language=en")
    Call<SeriesRoot> getSeriesNowPlaying();

    @GET("tv/popular?api_key="+API_KEY+"&language=en&page=1")
    Call<SeriesRoot> getSeriesPopular();

    @GET("tv/top_rated?api_key="+API_KEY+"&language=en&page=1")
    Call<SeriesRoot> getSeriesTopRated();

    @GET("tv/airing_today?api_key="+API_KEY+"&language=en&page=1")
    Call<SeriesRoot> getSeriesUpcoming();

    @GET("tv/{tvId}?api_key="+API_KEY+"&language=en")
    Call<SeriesDetails> getSeriesDetails(@Path("tvId") String tvId);
}
