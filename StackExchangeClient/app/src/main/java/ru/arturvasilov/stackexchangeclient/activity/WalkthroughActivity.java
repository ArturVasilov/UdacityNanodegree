package ru.arturvasilov.stackexchangeclient.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import me.relex.circleindicator.CircleIndicator;
import ru.arturvasilov.stackexchangeclient.R;
import ru.arturvasilov.stackexchangeclient.adapter.WalkthroughAdapter;
import ru.arturvasilov.stackexchangeclient.presenter.WalkthroughPresenter;
import ru.arturvasilov.stackexchangeclient.utils.PreferencesUtils;
import ru.arturvasilov.stackexchangeclient.utils.Views;
import ru.arturvasilov.stackexchangeclient.view.WalkthroughView;
import ru.arturvasilov.stackexchangeclient.widget.CustomViewPager;

/**
 * @author Artur Vasilov
 */
public class WalkthroughActivity extends AppCompatActivity implements WalkthroughView, View.OnClickListener,
        CustomViewPager.PagerStateListener {

    private TextView mActionButton;
    private CustomViewPager mPager;

    private WalkthroughPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        setContentView(R.layout.ac_walkthrough);
        mActionButton = Views.findById(this, R.id.walkthroughActionButton);
        mActionButton.setOnClickListener(this);

        mPager = Views.findById(this, R.id.pager);
        PagerAdapter adapter = new WalkthroughAdapter(getSupportFragmentManager());
        mPager.setAdapter(adapter);
        mPager.setOnPageChangedListener(this);

        CircleIndicator indicator = Views.findById(this, R.id.pager_indicator);
        indicator.setViewPager(mPager);

        mPresenter = new WalkthroughPresenter(this, this, getLoaderManager());
        mPresenter.init();
    }

    @Override
    public void onClick(View view) {
        mPresenter.onActionButtonClick();
    }

    @Override
    public void onPageScrollStarted(int currentPage) {
        // Do nothing
    }

    @Override
    public void onPageChanged(int selectedPage, boolean fromUser) {
        mPresenter.onBenefitSelected(fromUser, selectedPage);
    }

    @Override
    public void showBenefit(int index) {
        if (index == mPager.getCurrentItem()) {
            return;
        }
        mPager.smoothScrollNext(getResources().getInteger(android.R.integer.config_shortAnimTime));

    }

    @Override
    public void setActionButtonText(@StringRes int textResId) {
        mActionButton.setText(textResId);
    }

    @Override
    public void showLoadingSplash() {
        //TODO
    }

    @Override
    public void finishWalkthrough() {
        PreferencesUtils.saveWalkthroughPassed();
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
