package ru.arturvasilov.stackexchangeclient.api.service;

import android.support.annotation.NonNull;

import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.arturvasilov.stackexchangeclient.model.response.UserResponse;
import rx.Observable;

/**
 * @author Artur Vasilov
 */
public interface UserInfoService {

    @NonNull
    @GET("/me?site=stackoverflow")
    Observable<UserResponse> getCurrentUser();

}
