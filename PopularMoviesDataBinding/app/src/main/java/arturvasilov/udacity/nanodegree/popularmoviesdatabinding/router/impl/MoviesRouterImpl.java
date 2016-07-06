package arturvasilov.udacity.nanodegree.popularmoviesdatabinding.router.impl;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import arturvasilov.udacity.nanodegree.popularmoviesdatabinding.R;
import arturvasilov.udacity.nanodegree.popularmoviesdatabinding.activity.MovieDetailsActivity;
import arturvasilov.udacity.nanodegree.popularmoviesdatabinding.activity.SettingsActivity;
import arturvasilov.udacity.nanodegree.popularmoviesdatabinding.fragment.MovieDetailsFragment;
import arturvasilov.udacity.nanodegree.popularmoviesdatabinding.model.content.Movie;
import arturvasilov.udacity.nanodegree.popularmoviesdatabinding.router.MoviesRouter;

/**
 * @author Artur Vasilov
 */
public class MoviesRouterImpl implements MoviesRouter {

    private final Activity mActivity;

    public MoviesRouterImpl(@NonNull Activity activity) {
        mActivity = activity;
    }

    @Override
    public void navigateToMovieScreen(@NonNull ImageView imageView, @NonNull Movie movie) {
        AppCompatActivity activity = (AppCompatActivity) mActivity;
        boolean isTabletLandscape = activity.getResources().getBoolean(R.bool.is_tablet)
                && activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
        if (isTabletLandscape) {
            activity.getFragmentManager().beginTransaction()
                .replace(R.id.details_container, MovieDetailsFragment.create(movie))
                .commit();
        } else {
            MovieDetailsActivity.navigate(activity, imageView, movie);
        }
    }

    @Override
    public void navigateToSettingsActivity() {
        Intent intent = new Intent(mActivity, SettingsActivity.class);
        mActivity.startActivity(intent);
    }

}
