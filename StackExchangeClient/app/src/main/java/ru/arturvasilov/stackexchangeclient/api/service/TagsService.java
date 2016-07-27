package ru.arturvasilov.stackexchangeclient.api.service;

import android.support.annotation.NonNull;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.arturvasilov.stackexchangeclient.model.response.TagsResponse;
import rx.Observable;

/**
 * @author Artur Vasilov
 */
public interface TagsService {

    @GET("tags?order=desc&sort=popular&site=stackoverflow&pagesize=50")
    Observable<TagsResponse> searchTags(@NonNull @Query("inname") String search);

}
