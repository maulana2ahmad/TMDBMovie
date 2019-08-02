package redudant.tmdbmovie.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Gravity;

import java.util.ArrayList;

import redudant.tmdbmovie.R;
import redudant.tmdbmovie.adapter.MovieAdapter;
import redudant.tmdbmovie.model.moviepopular.Movie;
import redudant.tmdbmovie.model.moviepopular.MovieDBresponse;
import redudant.tmdbmovie.service.Client;
import redudant.tmdbmovie.service.MovieDataService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Movie> movies;

    private RecyclerView recyclerView;

    private MovieAdapter movieAdapter;

    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //title ActionBar
        getSupportActionBar().setTitle("Moviade Popular");

        //end title ActionBar


        getPopularMoview();

        //start refresh
        swipeRefreshLayout = findViewById(R.id.swiperefresh);
        //swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary, R.color.Orange, R.color.Blue, R.color.Green);
        swipeRefreshLayout.setColorSchemeResources(R.color.Red, R.color.Orange, R.color.Blue, R.color.Green);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                getPopularMoview();
            }
        });
        //end refresh
    }

    private void getPopularMoview() {

        MovieDataService movieDataService = Client.getService();

        Call<MovieDBresponse> call = movieDataService.getMoviepopular(MainActivity.this.getString(R.string.api_Key));

        call.enqueue(new Callback<MovieDBresponse>() {
            @Override
            public void onResponse(Call<MovieDBresponse> call, Response<MovieDBresponse> response) {

                MovieDBresponse movieDBresponse = response.body();

                if (movieDBresponse != null && movieDBresponse.getMovies() != null){

                    movies = (ArrayList<Movie>) movieDBresponse.getMovies();

                    //creat method show on recyclerView
                    showOnRecyclerView();
                }

            }

            @Override
            public void onFailure(Call<MovieDBresponse> call, Throwable t) {

            }
        });
    }

    private void showOnRecyclerView() {

        recyclerView = (RecyclerView) findViewById(R.id.rcMovies);

        movieAdapter = new MovieAdapter(MainActivity.this, movies);

        //opstion portrait an landscap
        if (MainActivity.this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
        {

            //set GridLayou
            recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));

        } else if (MainActivity.this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {

            //set GridLayou
            recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 4));
        }

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(movieAdapter);
        movieAdapter.notifyDataSetChanged();
    }
}
