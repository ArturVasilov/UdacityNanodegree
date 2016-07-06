package arturvasilov.udacity.nanodegree.popularmoviesdatabinding.fragment;

import android.app.Fragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import arturvasilov.udacity.nanodegree.popularmoviesdatabinding.R;
import arturvasilov.udacity.nanodegree.popularmoviesdatabinding.databinding.FragmentMoviesBinding;
import arturvasilov.udacity.nanodegree.popularmoviesdatabinding.databinding.viewmodel.MoviesViewModel;
import arturvasilov.udacity.nanodegree.popularmoviesdatabinding.router.MoviesRouter;
import arturvasilov.udacity.nanodegree.popularmoviesdatabinding.router.impl.MoviesRouterImpl;

/**
 * @author Artur Vasilov
 */
public class MoviesFragment extends Fragment {

    private MoviesViewModel mViewModel;
    private MoviesRouter mRouter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentMoviesBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movies, container, false);
        mRouter = new MoviesRouterImpl(getActivity());
        mViewModel = new MoviesViewModel(getActivity(), getLoaderManager(), mRouter);
        binding.setModel(mViewModel);
        //loader handles activity restart and returns values to fast, so we need our binding (adapter) to be ready for it
        binding.executePendingBindings();

        View root = binding.getRoot();
        Toolbar toolbar = (Toolbar) root.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        mViewModel.init();

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        mViewModel.onResume();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_movies, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            mRouter.navigateToSettingsActivity();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
