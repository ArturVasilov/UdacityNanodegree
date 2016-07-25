package ru.arturvasilov.stackexchangeclient.api.service;

import android.support.annotation.NonNull;

import retrofit2.http.GET;
import ru.arturvasilov.stackexchangeclient.model.response.QuestionResponse;
import rx.Observable;

/**
 * @author Artur Vasilov
 */
public interface QuestionService {

    @NonNull
    @GET("/questions?order=desc&sort=activity&pagesize=50&site=stackoverflow")
    Observable<QuestionResponse> questions();

    @NonNull
    @GET("/questions?order=desc&sort=activity&site=stackoverflow")
    Observable<QuestionResponse> questions(@NonNull String tag);

}
