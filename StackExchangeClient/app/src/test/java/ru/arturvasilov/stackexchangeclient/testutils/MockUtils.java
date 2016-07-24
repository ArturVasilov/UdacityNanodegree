package ru.arturvasilov.stackexchangeclient.testutils;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.orhanobut.hawk.Hawk;
import com.orhanobut.hawk.HawkBuilder;
import com.orhanobut.hawk.LogLevel;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import ru.arturvasilov.stackexchangeclient.rx.rxloader.RxLcImpl;
import ru.arturvasilov.stackexchangeclient.view.ErrorView;
import ru.arturvasilov.stackexchangeclient.view.LoadingView;
import rx.Scheduler;
import rx.android.plugins.RxAndroidPlugins;
import rx.android.plugins.RxAndroidSchedulersHook;
import rx.plugins.RxJavaHooks;
import rx.schedulers.Schedulers;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Artur Vasilov
 */
public final class MockUtils {

    public static final SharedPreferences PREFERENCES = new SharedPreferencesMapImpl();

    private MockUtils() {
    }

    public static void setupTestSchedulers() {
        try {
            RxJavaHooks.onIOScheduler(Schedulers.immediate());
            RxJavaHooks.onNewThreadScheduler(Schedulers.immediate());
            RxJavaHooks.onComputationScheduler(Schedulers.immediate());

            RxAndroidPlugins.getInstance().registerSchedulersHook(new RxAndroidSchedulersHook() {
                @Override
                public Scheduler getMainThreadScheduler() {
                    return Schedulers.immediate();
                }
            });
        } catch (IllegalStateException ignored) {
        }
    }

    public static void setupHawkForTests() {
        Context context = MockUtils.mockContext();
        when(context.getSharedPreferences("HAWK", 0)).thenReturn(PREFERENCES);

        Hawk.init(context)
                .setEncryptionMethod(HawkBuilder.EncryptionMethod.NO_ENCRYPTION)
                .setStorage(HawkBuilder.newSharedPrefStorage(context))
                .setLogLevel(LogLevel.NONE)
                .build();
    }

    @NonNull
    public static Context mockContext() {
        Context context = mock(Context.class);
        Context appContext = mock(Context.class);
        when(context.getApplicationContext()).thenReturn(appContext);
        return context;
    }

    @NonNull
    public static LoadingView mockLoadingView() {
        LoadingView loadingView = mock(LoadingView.class);
        doNothing().when(loadingView).showLoadingIndicator();
        doNothing().when(loadingView).hideLoadingIndicator();
        return loadingView;
    }

    @NonNull
    public static ErrorView mockErrorView() {
        ErrorView errorView = mock(ErrorView.class);
        doNothing().when(errorView).showNetworkError();
        doNothing().when(errorView).showUnexpectedError();
        doNothing().when(errorView).showErrorMessage(anyString());
        doNothing().when(errorView).hideErrorMessage();
        return errorView;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public static LoaderManager rxLoaderManager() {
        LoaderManager lm = mock(LoaderManager.class);
        when(lm.initLoader(anyInt(), any(Bundle.class), any(LoaderManager.LoaderCallbacks.class)))
                .then(new MockedRxLoaderAnswer());
        when(lm.restartLoader(anyInt(), any(Bundle.class), any(LoaderManager.LoaderCallbacks.class)))
                .then(new MockedRxLoaderAnswer());
        return lm;
    }

    private static class MockedRxLoaderAnswer implements Answer<Loader> {

        @Override
        public Loader answer(InvocationOnMock invocation) throws Throwable {
            RxLcImpl callbacks = (RxLcImpl) invocation.getArguments()[2];
            callbacks.subscribeForTesting();
            return null;
        }
    }

}
