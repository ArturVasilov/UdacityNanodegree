package arturvasilov.udacity.nanodegree.popularmoviesdatabinding.testutils;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import arturvasilov.udacity.nanodegree.popularmoviesdatabinding.api.MoviesRepository;
import arturvasilov.udacity.nanodegree.popularmoviesdatabinding.api.MoviesRepositoryImpl;
import arturvasilov.udacity.nanodegree.popularmoviesdatabinding.model.Movie;
import arturvasilov.udacity.nanodegree.popularmoviesdatabinding.model.contracts.MoviesProvider;
import rx.Observable;

/**
 * @author Artur Vasilov
 */
public class TestMoviesRepository implements MoviesRepository {

    private final List<Movie> mMovies;

    public TestMoviesRepository() {
        mMovies = new ArrayList<>();
    }

    public void setMovies(@NonNull List<Movie> movies) {
        mMovies.clear();
        mMovies.addAll(movies);
    }

    @NonNull
    @Override
    public Observable<List<Movie>> loadMovies(@NonNull MoviesProvider.Type type) {
        return Observable.just(mMovies);
    }
}
