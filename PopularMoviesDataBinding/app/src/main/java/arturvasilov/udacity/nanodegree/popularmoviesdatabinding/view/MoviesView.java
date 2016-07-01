package arturvasilov.udacity.nanodegree.popularmoviesdatabinding.view;

import android.support.annotation.NonNull;

import java.util.List;

import arturvasilov.udacity.nanodegree.popularmoviesdatabinding.model.Movie;

/**
 * @author Artur Vasilov
 */
public interface MoviesView extends LoadingView {

    void showMovies(@NonNull List<Movie> movies);

}
