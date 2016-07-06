package arturvasilov.udacity.nanodegree.popularmoviesdatabinding.rx;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * @author Artur Vasilov
 */
class RxResult<T> {

    private final T mResult;

    private final Throwable mError;

    private RxResult(@Nullable T result, @Nullable Throwable error) {
        mResult = result;
        mError = error;
    }

    @NonNull
    public static <T> RxResult<T> onNext(@Nullable T result) {
        return new RxResult<>(result, null);
    }

    @NonNull
    public static <T> RxResult<T> onError(@Nullable Throwable error) {
        return new RxResult<>(null, error);
    }

    @Nullable
    public T getResult() {
        return mResult;
    }

    @Nullable
    public Throwable getError() {
        return mError;
    }

}
