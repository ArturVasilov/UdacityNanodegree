package arturvasilov.udacity.nanodegree.popularmoviesdatabinding.api;

import android.support.annotation.NonNull;

import java.util.List;

import arturvasilov.udacity.nanodegree.popularmoviesdatabinding.model.Movie;
import arturvasilov.udacity.nanodegree.popularmoviesdatabinding.model.contracts.MoviesProvider;
import rx.Observable;

/**
 * @author Artur Vasilov
 */
public interface MoviesRepository {

    @NonNull
    Observable<List<Movie>> loadMovies(@NonNull MoviesProvider.Type type);

}
