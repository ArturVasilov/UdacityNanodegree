package arturvasilov.udacity.nanodegree.popularmoviesnew.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import arturvasilov.udacity.nanodegree.popularmoviesnew.R;
import arturvasilov.udacity.nanodegree.popularmoviesnew.model.Movie;
import arturvasilov.udacity.nanodegree.popularmoviesnew.widget.MoviesAdapter;

/**
 * @author Artur Vasilov
 */
public class MoviesViewModel extends BaseObservable {

    private final Context mContext;

    private final MoviesAdapter mAdapter;

    public MoviesViewModel(@NonNull Context context) {
        mContext = context;
        mAdapter = new MoviesAdapter(new ArrayList<>());
    }

    @NonNull
    @Bindable
    public RecyclerView.LayoutManager getLayoutManager() {
        return new GridLayoutManager(mContext, mContext.getResources().getInteger(R.integer.columns_count));
    }

    @NonNull
    @Bindable
    public void getAdapter() {

    }
}
