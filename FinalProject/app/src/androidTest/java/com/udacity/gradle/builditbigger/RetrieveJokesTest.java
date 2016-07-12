package com.udacity.gradle.builditbigger;

import android.support.annotation.NonNull;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

/**
 * @author Artur Vasilov
 */
@RunWith(AndroidJUnit4.class)
public class RetrieveJokesTest {

    @Test
    public void testJokeRetrieved() throws Exception {
        RetrieveJokesAsyncTask.Callback callback = new RetrieveJokesAsyncTask.Callback() {
            @Override
            public void onStartLoading() {
                //Do nothing
            }

            @Override
            public void onFinished(@NonNull String joke) {
                assertNotNull(joke);
                assertTrue(joke.length() > 0);
            }
        };

        new RetrieveJokesAsyncTask(callback).execute();
    }
}
