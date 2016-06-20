package arturvasilov.udacity.nanodegree.myappportfolio;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

public class PortfolioActivity extends AppCompatActivity implements View.OnClickListener, PortfolioView {

    private PortfolioPresenter mPresenter;
    private PortfolioRouter mRouter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portfolio);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mPresenter = new PortfolioPresenter(this, this);
        mRouter = new PortfolioRouter(this);

        findViewById(R.id.popular_movies_button).setOnClickListener(this);
        findViewById(R.id.stock_hawk_button).setOnClickListener(this);
        findViewById(R.id.build_it_bigger_button).setOnClickListener(this);
        findViewById(R.id.make_your_app_material_button).setOnClickListener(this);
        findViewById(R.id.go_ubiquitous_button).setOnClickListener(this);
        findViewById(R.id.capstone_button).setOnClickListener(this);

        findViewById(R.id.fab).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        @IdRes int id = view.getId();
        if (id == R.id.popular_movies_button) {
            mPresenter.onPopularMoviesClick();
        } else if (id == R.id.stock_hawk_button) {
            mPresenter.onStockHawkClick();
        } else if (id == R.id.build_it_bigger_button) {
            mPresenter.onBuildItBetterClick();
        } else if (id == R.id.make_your_app_material_button) {
            mPresenter.onMakeYourAppMaterialClick();
        } else if (id == R.id.go_ubiquitous_button) {
            mPresenter.onGoUbiquitousClick();
        } else if (id == R.id.capstone_button) {
            mPresenter.onCapstoneClick();
        } else if (id == R.id.fab) {
            mPresenter.onFabClick();
        }
    }

    @Override
    public void showNavigationText(@NonNull String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }

    @Override
    public void navigateToUdacity() {
        mRouter.navigateToUdacity();
    }
}
