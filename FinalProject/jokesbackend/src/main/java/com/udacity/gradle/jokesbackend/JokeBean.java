package com.udacity.gradle.jokesbackend;

import com.udacity.gradle.builditbigger.JokesTeller;

/**
 * @author Artur Vasilov
 */
public class JokeBean {

    private JokesTeller mJokesTeller;

    public JokeBean() {
        mJokesTeller = JokesTeller.getInstance();
    }

    public String getJoke() {
        return mJokesTeller.getJoke();
    }

}
