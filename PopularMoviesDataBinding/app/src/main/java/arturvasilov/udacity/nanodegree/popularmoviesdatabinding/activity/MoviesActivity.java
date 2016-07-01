package arturvasilov.udacity.nanodegree.popularmoviesdatabinding.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import arturvasilov.udacity.nanodegree.popularmoviesdatabinding.R;
import arturvasilov.udacity.nanodegree.popularmoviesdatabinding.databinding.ActivityMoviesBinding;
import arturvasilov.udacity.nanodegree.popularmoviesdatabinding.databinding.viewmodel.MoviesViewModel;
import arturvasilov.udacity.nanodegree.popularmoviesdatabinding.model.Movie;
import arturvasilov.udacity.nanodegree.popularmoviesdatabinding.router.MoviesRouter;
import arturvasilov.udacity.nanodegree.popularmoviesdatabinding.widget.BaseAdapter;

public class MoviesActivity extends AppCompatActivity implements BaseAdapter.OnItemClickListener<Movie> {

    private MoviesViewModel mViewModel;
    private MoviesRouter mRouter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        ActivityMoviesBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_movies);
        mViewModel = new MoviesViewModel(this, getLoaderManager());
        binding.setModel(mViewModel);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRouter = new MoviesRouter(this);

        mViewModel.init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_movies, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            mRouter.navigateToSettingsActivity();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mViewModel.onResume();
    }

    @Override
    public void onItemClick(@NonNull View view, @NonNull Movie movie) {
        ImageView imageView = (ImageView) view.findViewById(R.id.image);
        mRouter.navigateToMovieScreen(imageView, movie);
    }
}
