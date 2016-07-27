package ru.arturvasilov.stackexchangeclient.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ru.arturvasilov.stackexchangeclient.R;
import ru.arturvasilov.stackexchangeclient.adapter.MainScreenAdapter;
import ru.arturvasilov.stackexchangeclient.images.CircleTransform;
import ru.arturvasilov.stackexchangeclient.model.content.User;
import ru.arturvasilov.stackexchangeclient.presenter.MainPresenter;
import ru.arturvasilov.stackexchangeclient.utils.Views;
import ru.arturvasilov.stackexchangeclient.view.MainView;
import ru.arturvasilov.stackexchangeclient.widget.CustomViewPager;

/**
 * @author Artur Vasilov
 */
public class MainActivity extends AppCompatActivity implements MainView,
        NavigationView.OnNavigationItemSelectedListener, TabLayout.OnTabSelectedListener {

    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private ActionBarDrawerToggle mDrawerToggle;
    private ImageView mHeaderImage;
    private TextView mHeaderText;

    private TabLayout mTabLayout;
    private CustomViewPager mPager;

    private MainPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_main);
        Toolbar toolbar = Views.findById(this, R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.app_name);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        mDrawerLayout = Views.findById(this, R.id.drawerLayout);
        mNavigationView = Views.findById(this, R.id.navigationView);
        mNavigationView.setNavigationItemSelectedListener(this);
        View headerView = mNavigationView.getHeaderView(0);
        mHeaderImage = Views.findById(headerView, R.id.headerImage);
        mHeaderText = Views.findById(headerView, R.id.headerText);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, 0, 0);
        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(ContextCompat.getColor(this, android.R.color.transparent));
        }

        mTabLayout = Views.findById(this, R.id.tabs);
        mPager = Views.findById(this, R.id.pager);
        mTabLayout.addOnTabSelectedListener(this);

        mPresenter = new MainPresenter(this, this);
        mPresenter.init();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return mDrawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(mNavigationView)) {
            mDrawerLayout.closeDrawer(mNavigationView);
            return;
        }

        super.onBackPressed();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.profile) {
            mPresenter.onProfileSelected();
        } else if (item.getItemId() == R.id.my_answers) {
            //TODO : show my answers
        } else if (item.getItemId() == R.id.tags) {
            startActivity(new Intent(this, TagsActivity.class));
        } else if (item.getItemId() == R.id.exit) {
            //TODO : show confirmation alert
        }

        mDrawerLayout.closeDrawer(mNavigationView);
        return false;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        mPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        // Do nothing
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
        // Do nothing
    }

    @Override
    public void showUserImage(@NonNull String imageUrl) {
        Picasso.with(this)
                .load(imageUrl)
                .error(R.drawable.ic_person)
                .transform(new CircleTransform())
                .into(mHeaderImage);
    }

    @Override
    public void showUserName(@NonNull String name) {
        mHeaderText.setText(name);
    }

    @Override
    public void addTab(@NonNull String tabTitle) {
        mTabLayout.addTab(mTabLayout.newTab().setText(tabTitle));
    }

    @Override
    public void showTags(@NonNull List<String> tags) {
        PagerAdapter adapter = new MainScreenAdapter(getSupportFragmentManager(), tags);
        mPager.setAdapter(adapter);
    }

    @Override
    public void openProfile(@NonNull User currentUser) {
        ProfileActivity.start(this, currentUser);
    }

    @Override
    public void hideTabLayout() {
        mTabLayout.setVisibility(View.GONE);
    }
}
