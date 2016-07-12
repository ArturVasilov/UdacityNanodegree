package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.udacity.gradle.androidlib.JokingAndroid;

/**
 * @author Artur Vasilov
 */
public class MainActivityFragment extends Fragment implements View.OnClickListener, RetrieveJokesAsyncTask.Callback {

    private ProgressBar mProgressBar;
    private View mMainLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        mProgressBar = (ProgressBar) root.findViewById(R.id.progressBar);
        mMainLayout = root.findViewById(R.id.mainLayout);

        root.findViewById(R.id.tellJokeButton).setOnClickListener(this);

        AdsUtils.showAds(root);

        return root;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.tellJokeButton) {
            new RetrieveJokesAsyncTask(this).execute();
        }
    }

    @Override
    public void onStartLoading() {
        mProgressBar.setVisibility(View.VISIBLE);
        mMainLayout.setVisibility(View.GONE);
    }

    @Override
    public void onFinished(@NonNull String joke) {
        mProgressBar.setVisibility(View.GONE);
        mMainLayout.setVisibility(View.VISIBLE);
        JokingAndroid.tellJoke(getActivity(), joke);
    }
}
