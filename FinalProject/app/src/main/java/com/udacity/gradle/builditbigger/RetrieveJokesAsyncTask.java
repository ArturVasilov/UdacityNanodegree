package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.udacity.gradle.jokesbackend.myApi.MyApi;
import com.udacity.gradle.jokesbackend.myApi.model.JokeBean;

import java.io.IOException;

/**
 * @author Artur Vasilov
 */
public class RetrieveJokesAsyncTask extends AsyncTask<Void, Void, String> {

    private final Callback mCallback;

    public RetrieveJokesAsyncTask(@NonNull Callback callback) {
        mCallback = callback;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mCallback.onStartLoading();
    }

    @Override
    protected String doInBackground(Void... voids) {
        try {
            return new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl("http://10.0.3.2:8080/_ah/api/") //that's for genymotion
                    .setGoogleClientRequestInitializer(request -> request.setDisableGZipContent(true))
                    .build()
                    .tellJoke(new JokeBean()).execute().getJoke();
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        mCallback.onFinished(s);
    }

    public interface Callback {

        void onStartLoading();

        void onFinished(@NonNull String joke);

    }
}
