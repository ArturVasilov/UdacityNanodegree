package ru.arturvasilov.stackexchangeclient.api;

import android.support.annotation.NonNull;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.arturvasilov.stackexchangeclient.BuildConfig;
import ru.arturvasilov.stackexchangeclient.api.service.QuestionService;
import ru.arturvasilov.stackexchangeclient.api.service.UserInfoService;

/**
 * @author Artur Vasilov
 */
public final class ApiFactory {

    private static OkHttpClient sClient;

    private static UserInfoService sUserInfoService;
    private static QuestionService sQuestionService;

    @NonNull
    public static UserInfoService getUserInfoService() {
        UserInfoService service = sUserInfoService;
        if (service == null) {
            synchronized (ApiFactory.class) {
                service = sUserInfoService;
                if (service == null) {
                    service = sUserInfoService = buildRetrofit().create(UserInfoService.class);
                }
            }
        }
        return service;
    }

    @NonNull
    public static QuestionService getQuestionService() {
        QuestionService service = sQuestionService;
        if (service == null) {
            synchronized (ApiFactory.class) {
                service = sQuestionService;
                if (service == null) {
                    service = sQuestionService = buildRetrofit().create(QuestionService.class);
                }
            }
        }
        return service;
    }

    @NonNull
    private static Retrofit buildRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.API_ENDPOINT)
                .client(getClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    @NonNull
    private static OkHttpClient getClient() {
        OkHttpClient client = sClient;
        if (client == null) {
            synchronized (ApiFactory.class) {
                client = sClient;
                if (client == null) {
                    client = sClient = buildClient();
                }
            }
        }
        return client;
    }

    @NonNull
    private static OkHttpClient buildClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(new ApiKeyInterceptor())
                .addInterceptor(new HttpLoggingInterceptor())
                .build();
    }
}
