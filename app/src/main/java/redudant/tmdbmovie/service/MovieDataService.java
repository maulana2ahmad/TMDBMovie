package redudant.tmdbmovie.service;

import redudant.tmdbmovie.model.moviepopular.MovieDBresponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieDataService {

    @GET("movie/popular")
    Call<MovieDBresponse> getMoviepopular(@Query("api_key") String apiKey);

    @GET("/movie/{movie_id}/videos")
    Call<MovieDBresponse> getVideos (@Path("movie_id") String movieId);
}
