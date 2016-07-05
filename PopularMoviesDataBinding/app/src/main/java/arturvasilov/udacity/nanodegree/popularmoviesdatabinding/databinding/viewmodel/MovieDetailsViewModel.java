package arturvasilov.udacity.nanodegree.popularmoviesdatabinding.databinding.viewmodel;

import android.app.LoaderManager;
import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;

import java.io.IOException;
import java.util.List;

import arturvasilov.udacity.nanodegree.popularmoviesdatabinding.R;
import arturvasilov.udacity.nanodegree.popularmoviesdatabinding.api.RepositoryProvider;
import arturvasilov.udacity.nanodegree.popularmoviesdatabinding.model.content.Movie;
import arturvasilov.udacity.nanodegree.popularmoviesdatabinding.model.content.Review;
import arturvasilov.udacity.nanodegree.popularmoviesdatabinding.model.content.Video;
import arturvasilov.udacity.nanodegree.popularmoviesdatabinding.rx.RxLoader;
import arturvasilov.udacity.nanodegree.popularmoviesdatabinding.utils.Images;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author Artur Vasilov
 */
public class MovieDetailsViewModel extends BaseObservable {

    private static final String MAXIMUM_RATING = "10";

    private final Context mContext;
    private final LoaderManager mLm;

    private final Movie mMovie;

    public MovieDetailsViewModel(@NonNull Context context, @NonNull LoaderManager lm,
                                 @NonNull Movie movie) {
        mContext = context;
        mLm = lm;
        mMovie = movie;
    }

    public void init() {
        Observable<List<Review>> reviews = RepositoryProvider.getRepository()
                .reviews(mMovie)
                .doOnNext(this::handleReviews);

        Observable<List<Video>> videos = RepositoryProvider.getRepository()
                .videos(mMovie)
                .doOnNext(this::handleVideos);

        Observable<Boolean> details = Observable.zip(reviews, videos, this::isNoError)
                .flatMap(value -> {
                    if (value != null && value) {
                        return Observable.just(true);
                    }
                    return Observable.error(new IOException());
                })
                //TODO : show progress
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        RxLoader.create(mContext, mLm, R.id.movie_details_loader_id, details)
                .init(value -> {
                }, this::handleError);
    }

    @NonNull
    @Bindable
    public String getImageWidth() {
        return Images.WIDTH_780;
    }

    @NonNull
    @Bindable
    public String getYear() {
        return mMovie.getReleasedDate().substring(0, 4);
    }

    @NonNull
    @Bindable
    public String getVoteAverage() {
        String average = String.valueOf(mMovie.getVoteAverage());
        average = average.length() > 3 ? average.substring(0, 3) : average;
        average = average.length() == 3 && average.charAt(2) == '0' ? average.substring(0, 1) : average;
        return average;
    }

    @NonNull
    @Bindable
    public String getVoteMax() {
        return MAXIMUM_RATING;
    }

    @ColorRes
    @Bindable
    public int getExpandedTitleColor() {
        return android.R.color.transparent;
    }

    @VisibleForTesting
    void handleReviews(@NonNull List<Review> reviews) {

    }

    @VisibleForTesting
    void handleVideos(@NonNull List<Video> videos) {

    }

    @VisibleForTesting
    void handleError(@NonNull Throwable throwable) {
    }

    private boolean isNoError(@Nullable List<Review> reviews, @Nullable List<Video> videos) {
        return reviews != null && videos != null;
    }
}
