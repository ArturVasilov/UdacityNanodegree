package arturvasilov.udacity.nanodegree.popularmoviesdatabinding.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.view.MenuItem;
import android.view.View;

import arturvasilov.udacity.nanodegree.popularmoviesdatabinding.R;
import arturvasilov.udacity.nanodegree.popularmoviesdatabinding.databinding.ActivityMovieDetailsBinding;
import arturvasilov.udacity.nanodegree.popularmoviesdatabinding.model.Movie;
import arturvasilov.udacity.nanodegree.popularmoviesdatabinding.viewmodel.MovieDetailsViewModel;

public class MovieDetailsActivity extends AppCompatActivity {

    public static final String IMAGE = "image";
    public static final String EXTRA_MOVIE = "extraMovie";

    public static void navigate(@NonNull AppCompatActivity activity, @NonNull View transitionImage,
                                @NonNull Movie movie) {
        Intent intent = new Intent(activity, MovieDetailsActivity.class);
        intent.putExtra(EXTRA_MOVIE, movie);

        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, transitionImage, IMAGE);
        ActivityCompat.startActivity(activity, intent, options.toBundle());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prepareTransition();
        setContentView(R.layout.activity_movie_details);

        ViewCompat.setTransitionName(findViewById(R.id.app_bar), IMAGE);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        ActivityMovieDetailsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_details);
        Movie movie = getIntent().getParcelableExtra(EXTRA_MOVIE);
        MovieDetailsViewModel model = new MovieDetailsViewModel(movie);
        binding.setModel(model);
        binding.setMovie(movie);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //TODO
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void prepareTransition() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Slide transition = new Slide();
            transition.excludeTarget(android.R.id.statusBarBackground, true);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            getWindow().setEnterTransition(transition);
            getWindow().setReturnTransition(transition);
        }
    }
}
