package arturvasilov.udacity.nanodegree.popularmovies.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;

import java.util.ArrayList;
import java.util.List;

import arturvasilov.udacity.nanodegree.popularmovies.R;
import arturvasilov.udacity.nanodegree.popularmovies.model.Movie;
import arturvasilov.udacity.nanodegree.popularmovies.presenter.MoviesPresenter;
import arturvasilov.udacity.nanodegree.popularmovies.router.MoviesRouter;
import arturvasilov.udacity.nanodegree.popularmovies.utils.LoadingDialog;
import arturvasilov.udacity.nanodegree.popularmovies.view.MoviesView;
import arturvasilov.udacity.nanodegree.popularmovies.widget.BaseAdapter;
import arturvasilov.udacity.nanodegree.popularmovies.widget.EmptyRecyclerView;
import arturvasilov.udacity.nanodegree.popularmovies.widget.MoviesAdapter;

public class MoviesActivity extends AppCompatActivity implements MoviesView, BaseAdapter.OnItemClickListener<Movie> {

    private LoadingDialog mProgressDialog;

    private MoviesAdapter mAdapter;

    private MoviesPresenter mPresenter;
    private MoviesRouter mRouter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mProgressDialog = LoadingDialog.create(R.string.movies_progress);

        EmptyRecyclerView recyclerView = (EmptyRecyclerView) findViewById(R.id.recyclerView);
        int columns = getResources().getInteger(R.integer.columns_count);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), columns);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setEmptyView(findViewById(R.id.empty));

        int imageWidth = getResources().getDisplayMetrics().widthPixels / columns;

        TypedValue typedValue = new TypedValue();
        getResources().getValue(R.dimen.rows_count, typedValue, true);
        float rowsCount = typedValue.getFloat();
        int actionBarHeight = getTheme().resolveAttribute(R.attr.actionBarSize, typedValue, true)
                ? TypedValue.complexToDimensionPixelSize(typedValue.data, getResources().getDisplayMetrics())
                : 0;

        int imageHeight = (int) ((getResources().getDisplayMetrics().heightPixels - actionBarHeight) / rowsCount);
        mAdapter = new MoviesAdapter(new ArrayList<>(), imageWidth, imageHeight);
        mAdapter.attachToRecyclerView(recyclerView);
        mAdapter.setOnItemClickListener(this);

        mRouter = new MoviesRouter(this);

        mPresenter = new MoviesPresenter(this, this, getLoaderManager());
        mPresenter.init();
    }

    @Override
    public void onItemClick(@NonNull Movie item) {
        mPresenter.onItemClick(item);
    }

    @Override
    public void showMovies(@NonNull List<Movie> movies) {
        mAdapter.setNewValues(movies);
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
        //Do nothing - recyclerview will handle it
    }
}
