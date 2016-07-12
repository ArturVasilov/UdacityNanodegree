package com.udacity.gradle.builditbigger;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertNotNull;

@RunWith(JUnit4.class)
public class JokesTellerTest {

    @Test
    public void testCreated() throws Exception {
        assertNotNull(JokesTeller.getInstance());
    }

    @Test
    public void testJokesCreated() throws Exception {
        JokesTeller jokesTeller = JokesTeller.getInstance();
        for (int i = 0; i < 3; i++) {
            String joke = jokesTeller.getJoke();
            assertNotNull(joke);
            assertTrue(joke.length() > 0);
        }
    }
}
