package arturvasilov.udacity.nanodegree.popularmovies.presenter;

import android.app.LoaderManager;
import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;

import arturvasilov.udacity.nanodegree.popularmovies.R;
import arturvasilov.udacity.nanodegree.popularmovies.api.ApiFactory;
import arturvasilov.udacity.nanodegree.popularmovies.model.Movie;
import arturvasilov.udacity.nanodegree.popularmovies.model.MoviesResponse;
import arturvasilov.udacity.nanodegree.popularmovies.rx.RxLoader;
import arturvasilov.udacity.nanodegree.popularmovies.view.MoviesView;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author Artur Vasilov
 */
public class MoviesPresenter {

    private final Context mContext;
    private final MoviesView mView;
    private final LoaderManager mLm;

    public MoviesPresenter(@NonNull Context context, @NonNull MoviesView view, @NonNull LoaderManager lm) {
        mContext = context;
        mView = view;
        mLm = lm;
    }

    public void init() {
        Observable<List<Movie>> movies = Observable.defer(() -> ApiFactory.getMoviesService()
                .popularMovies()
                .doOnSubscribe(mView::showLoading)
                .doAfterTerminate(mView::hideLoading)
                .map(MoviesResponse::getMovies)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()));

        RxLoader.create(mContext, mLm, R.id.movies_loader_id, movies)
                .init(mView::showMovies, throwable -> mView.showError());
    }

    public void onItemClick(@NonNull Movie movie) {
        mView.openMovieScreen(movie);
    }
}
