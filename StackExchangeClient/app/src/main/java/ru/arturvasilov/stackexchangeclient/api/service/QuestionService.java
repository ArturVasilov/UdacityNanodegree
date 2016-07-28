package ru.arturvasilov.stackexchangeclient.api.service;

import android.support.annotation.NonNull;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import ru.arturvasilov.stackexchangeclient.model.content.Question;
import ru.arturvasilov.stackexchangeclient.model.response.QuestionResponse;
import rx.Observable;

/**
 * @author Artur Vasilov
 */
public interface QuestionService {

    @NonNull
    @GET("/questions?order=desc&sort=activity&pagesize=50")
    Observable<QuestionResponse> questions();

    @NonNull
    @GET("/me/questions?order=desc&sort=activity&pagesize=50")
    Observable<QuestionResponse> myQuestions();

    @NonNull
    @GET("/questions?order=desc&sort=activity&pagesize=50")
    Observable<QuestionResponse> questions(@NonNull @Query("tagged") String tag);

    @NonNull
    @GET("/questions/{ids}?order=desc&sort=activity&filter=withbody")
    Observable<QuestionResponse> questionWithBody(@Path("ids") int questionId);
}
