package arturvasilov.udacity.nanodegree.popularmoviesdatabinding.databinding.viewmodel;

import android.app.LoaderManager;
import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;

import java.util.ArrayList;
import java.util.List;

import arturvasilov.udacity.nanodegree.popularmoviesdatabinding.BR;
import arturvasilov.udacity.nanodegree.popularmoviesdatabinding.R;
import arturvasilov.udacity.nanodegree.popularmoviesdatabinding.api.ApiFactory;
import arturvasilov.udacity.nanodegree.popularmoviesdatabinding.app.Preferences;
import arturvasilov.udacity.nanodegree.popularmoviesdatabinding.model.Movie;
import arturvasilov.udacity.nanodegree.popularmoviesdatabinding.model.MoviesResponse;
import arturvasilov.udacity.nanodegree.popularmoviesdatabinding.rx.RxLoader;
import arturvasilov.udacity.nanodegree.popularmoviesdatabinding.widget.MoviesAdapter;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author Artur Vasilov
 */
public class MoviesViewModel extends BaseObservable {

    private final Context mContext;
    private final LoaderManager mLm;

    private boolean mIsRefreshing;
    private final List<Movie> mMovies;

    private boolean mIsPopular;

    public MoviesViewModel(@NonNull Context context, @NonNull LoaderManager lm) {
        mContext = context;
        mLm = lm;

        mIsRefreshing = false;
        mMovies = new ArrayList<>();
    }

    public void init() {
        mIsPopular = Preferences.isPopularMovies(mContext);
        load(false);
    }

    public void onResume() {
        boolean isPopular = Preferences.isPopularMovies(mContext);
        if (isPopular != mIsPopular) {
            mIsPopular = isPopular;
            load(true);
        }
    }

    @Bindable
    public boolean isRefreshing() {
        return mIsRefreshing;
    }

    @NonNull
    @Bindable
    public RecyclerView.LayoutManager getLayoutManager() {
        int columns = mContext.getResources().getInteger(R.integer.columns_count);
        return new GridLayoutManager(mContext, columns);
    }

    @NonNull
    @Bindable
    public MoviesAdapter getAdapter() {
        int columns = mContext.getResources().getInteger(R.integer.columns_count);
        int imageWidth = mContext.getResources().getDisplayMetrics().widthPixels / columns;

        TypedValue typedValue = new TypedValue();
        mContext.getResources().getValue(R.dimen.rows_count, typedValue, true);
        float rowsCount = typedValue.getFloat();
        int actionBarHeight = mContext.getTheme().resolveAttribute(R.attr.actionBarSize, typedValue, true)
                ? TypedValue.complexToDimensionPixelSize(typedValue.data, mContext.getResources().getDisplayMetrics())
                : 0;
        int imageHeight = (int) ((mContext.getResources().getDisplayMetrics().heightPixels - actionBarHeight) / rowsCount);

        return new MoviesAdapter(new ArrayList<>(), imageWidth, imageHeight);
    }

    @NonNull
    @Bindable
    public List<Movie> getMovies() {
        return mMovies;
    }

    private void load(boolean restart) {
        Observable<MoviesResponse> observable = mIsPopular
                ? ApiFactory.getMoviesService().popularMovies()
                : ApiFactory.getMoviesService().topRatedMovies();
        Observable<List<Movie>> movies = Observable.defer(() -> observable
                .doOnSubscribe(() -> setRefreshing(true))
                .doAfterTerminate(() -> setRefreshing(false))
                .map(MoviesResponse::getMovies)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()));

        RxLoader<List<Movie>> loader = RxLoader.create(mContext, mLm, R.id.movies_loader_id, movies);
        if (restart) {
            loader.restart(this::handleMovies, this::handleError);
        } else {
            loader.init(this::handleMovies, this::handleError);
        }
    }

    private void setRefreshing(boolean refreshing) {
        mIsRefreshing = refreshing;
        notifyPropertyChanged(BR.refreshing);
    }

    private void handleMovies(@NonNull List<Movie> movies) {
        mMovies.clear();
        mMovies.addAll(movies);
        notifyPropertyChanged(BR.movies);
    }

    private void handleError(@NonNull Throwable throwable) {
        mMovies.clear();
        notifyPropertyChanged(BR.movies);
    }

}
