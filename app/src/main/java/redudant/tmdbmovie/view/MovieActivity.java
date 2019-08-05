package redudant.tmdbmovie.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import redudant.tmdbmovie.R;
import redudant.tmdbmovie.model.moviepopular.Movie;

public class MovieActivity extends AppCompatActivity {

    private Movie movie;

    private ImageView movieImage;

    private String image;

    private TextView movieTitle, movieSynopsis, movieRating, movieReleaseDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        //toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //tanda panah back
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getActionBar().setHomeButtonEnabled(true);

        movieImage = (ImageView) findViewById(R.id.ivMovieLarge);
        movieTitle = (TextView) findViewById(R.id.tvMovieTitle);
        movieRating = (TextView) findViewById(R.id.tvMovieRating);
        movieReleaseDate = (TextView) findViewById(R.id.tvReleaseDate);
        movieSynopsis = (TextView) findViewById(R.id.tvPlotsynopsis);


        Intent intent = getIntent();

        if (intent.hasExtra("movie")) {

            movie = getIntent().getParcelableExtra("movie");

            Toast.makeText(getApplicationContext(), movie.getOriginalTitle(), Toast.LENGTH_LONG).show();

            //image
            image = movie.getPosterPath();

            String posterImage = "https://image.tmdb.org/t/p/w500/" + image;

            Glide.with(MovieActivity.this)
                    .load(posterImage)
                    .placeholder(R.drawable.loading)
                    .into(movieImage);

            //title
            getSupportActionBar().setTitle(movie.getTitle());

            movieTitle.setText(movie.getTitle());

            //movieRating.setText(Double.toString(movie.getVoteAverage()));
            movieRating.setText(String.format("%.0f%%", movie.getVoteAverage() * 10));


            /**
            if (!movieRating.getText().toString().equals("")) {
                double amount = Double.parseDouble(movieRating.getText().toString());
                int res = (int) (amount * 10);
                //double res = (amount / 10) * 100;
                //float fix = (float) res;
                Toast.makeText(getApplicationContext(), "" + res, Toast.LENGTH_SHORT).show();
                //movieRating.setText(res);

            }
             */


            movieReleaseDate.setText(movie.getReleaseDate());
            //movieRating.setText(Double.toString(movie.getVoteCount()));
            movieSynopsis.setText(movie.getOverview());

        }
    }
}
