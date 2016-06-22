package arturvasilov.udacity.nanodegree.popularmovies.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

import arturvasilov.udacity.nanodegree.popularmovies.R;
import arturvasilov.udacity.nanodegree.popularmovies.model.Movie;
import arturvasilov.udacity.nanodegree.popularmovies.presenter.MoviesPresenter;
import arturvasilov.udacity.nanodegree.popularmovies.router.MoviesRouter;
import arturvasilov.udacity.nanodegree.popularmovies.utils.LoadingDialog;
import arturvasilov.udacity.nanodegree.popularmovies.view.MoviesView;

public class MoviesActivity extends AppCompatActivity implements MoviesView {

    private LoadingDialog mProgressDialog;

    private MoviesPresenter mPresenter;
    private MoviesRouter mRouter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgressDialog = LoadingDialog.create(R.string.movies_progress);

        mRouter = new MoviesRouter(this);

        mPresenter = new MoviesPresenter(this, this, getLoaderManager());
        mPresenter.init();
    }

    @Override
    public void showMovies(@NonNull List<Movie> movies) {
        //TODO
    }

    @Override
    public void openMovieScreen(@NonNull Movie movie) {
       mRouter.openMovieScreen(movie);
    }

    @Override
    public void showLoading() {
        mProgressDialog.show(getFragmentManager());
    }

    @Override
    public void hideLoading() {
        mProgressDialog.cancel();
    }

    @Override
    public void showError() {
        //Do nothing - gridview will handle it
    }
}
