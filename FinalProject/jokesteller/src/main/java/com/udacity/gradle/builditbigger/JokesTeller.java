package com.udacity.gradle.builditbigger;

import java.security.SecureRandom;
import java.util.Random;

public class JokesTeller {

    private static final String[] JOKES = {
            "Can a kangaroo jump higher than a house? Of course, a house doesn’t jump at all.",
            "Doctor: \"I'm sorry but you suffer from a terminal illness and have only 10 to live.\"\n" +
                    "Patient: \"What do you mean, 10? 10 what? Months? Weeks?!\"\n" +
                    "Doctor: \"Nine.\"",
            "It is so cold outside I saw a politician with his hands in his own pockets.",
            "My dog used to chase people on a bike a lot. It got so bad, finally I had to take his bike away.",
            "I’m selling my talking parrot. Why? Because yesterday, the bastard tried to sell me."
    };

    private Random mRandom;

    private JokesTeller() {
        mRandom = new SecureRandom();
    }

    public static JokesTeller getInstance() {
        return new JokesTeller();
    }

    public String getJoke() {
        int jokeIndex = mRandom.nextInt(JOKES.length);
        return JOKES[jokeIndex];
    }

}
