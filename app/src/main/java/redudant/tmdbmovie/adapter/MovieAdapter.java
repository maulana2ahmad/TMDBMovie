package redudant.tmdbmovie.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import redudant.tmdbmovie.R;
import redudant.tmdbmovie.model.Movie;
import redudant.tmdbmovie.view.MovieActivity;


//4. extend RecylerView.Adapter
//5. etract implement method in RecycllerView
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private Context context;

    private ArrayList<Movie> movieArrayList;

    public MovieAdapter(Context context, ArrayList<Movie> movieArrayList) {
        this.context = context;
        this.movieArrayList = movieArrayList;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //call movie_list_item
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item, parent, false);

        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {

        //set and get
        holder.movieTitle.setText(movieArrayList.get(position).getOriginalTitle());
        //because number used Double
        holder.rate.setText(Double.toString(movieArrayList.get(position).getVoteAverage()));

        holder.releaseDate.setText(movieArrayList.get(position).getReleaseDate());

        //get Image cek in documentation or LINK web documentation = https://developers.themoviedb.org/3/getting-started/images
        String imagePath = "https://image.tmdb.org/t/p/w500/" + movieArrayList.get(position).getPosterPath();

        Glide.with(context)
                .load(imagePath)
                .placeholder(R.drawable.loading3) //loading
                .into(holder.movieImage);

    }

    @Override
    public int getItemCount() {
        return movieArrayList.size();
    }

    //1. creat class ViewHolder
    //2. extract constructor MovieViewHolder
    public class MovieViewHolder extends RecyclerView.ViewHolder {

        TextView movieTitle, rate, releaseDate;
        ImageView movieImage;

        public MovieViewHolder(@NonNull final View itemView) {
            super(itemView);

            movieImage = (ImageView) itemView.findViewById(R.id.ivMovie);
            movieTitle = (TextView) itemView.findViewById(R.id.tvTitile);
            rate = (TextView) itemView.findViewById(R.id.tvRate);
            releaseDate = (TextView) itemView.findViewById(R.id.tvReleaseDate);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int position = getAdapterPosition();

                    if (position != RecyclerView.NO_POSITION) {

                        Movie selctedMovie = movieArrayList.get(position);

                        Intent intent = new Intent(context, MovieActivity.class);
                        intent.putExtra("movie", selctedMovie);
                        context.startActivity(intent);
                    }
                }
            });
        }
    }
}
