package arturvasilov.udacity.nanodegree.popularmoviesdatabinding.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import arturvasilov.udacity.nanodegree.popularmoviesdatabinding.R;
import arturvasilov.udacity.nanodegree.popularmoviesdatabinding.fragment.MoviesFragment;

public class MoviesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.container, new MoviesFragment())
                    .commit();
        }
    }
}
