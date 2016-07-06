package arturvasilov.udacity.nanodegree.popularmoviesdatabinding.fragment;

import android.app.Fragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import arturvasilov.udacity.nanodegree.popularmoviesdatabinding.R;
import arturvasilov.udacity.nanodegree.popularmoviesdatabinding.activity.MovieDetailsActivity;
import arturvasilov.udacity.nanodegree.popularmoviesdatabinding.databinding.FragmentMovieDetailsBinding;
import arturvasilov.udacity.nanodegree.popularmoviesdatabinding.databinding.viewmodel.MovieDetailsViewModel;
import arturvasilov.udacity.nanodegree.popularmoviesdatabinding.model.content.Movie;

/**
 * @author Artur Vasilov
 */
public class MovieDetailsFragment extends Fragment {

    @NonNull
    public static MovieDetailsFragment create(@NonNull Movie movie) {
        MovieDetailsFragment fragment = new MovieDetailsFragment();
        Bundle args = new Bundle();
        args.putParcelable(MovieDetailsActivity.EXTRA_MOVIE, movie);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentMovieDetailsBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_movie_details, container, false);

        Movie movie = getArguments().getParcelable(MovieDetailsActivity.EXTRA_MOVIE);
        //noinspection ConstantConditions
        MovieDetailsViewModel model = new MovieDetailsViewModel(getActivity(), getLoaderManager(), movie);
        binding.setModel(model);
        binding.setMovie(movie);
        binding.executePendingBindings();

        model.init();

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() instanceof MovieDetailsActivity) {
            ((MovieDetailsActivity) getActivity()).showTransition();
        } else {
            //noinspection ConstantConditions
            getView().findViewById(R.id.toolbar).setVisibility(View.GONE);
        }
    }
}
