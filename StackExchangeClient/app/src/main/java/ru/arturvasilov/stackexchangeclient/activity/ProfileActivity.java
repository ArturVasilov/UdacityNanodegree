package ru.arturvasilov.stackexchangeclient.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayout;
import android.text.method.LinkMovementMethod;
import android.transition.Slide;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ru.arturvasilov.stackexchangeclient.R;
import ru.arturvasilov.stackexchangeclient.dialog.LoadingDialog;
import ru.arturvasilov.stackexchangeclient.model.content.Badge;
import ru.arturvasilov.stackexchangeclient.model.content.User;
import ru.arturvasilov.stackexchangeclient.model.content.UserTag;
import ru.arturvasilov.stackexchangeclient.presenter.ProfilePresenter;
import ru.arturvasilov.stackexchangeclient.rx.RxError;
import ru.arturvasilov.stackexchangeclient.utils.HtmlCompat;
import ru.arturvasilov.stackexchangeclient.utils.Views;
import ru.arturvasilov.stackexchangeclient.view.ProfileView;
import ru.arturvasilov.stackexchangeclient.widget.BadgeTextView;
import ru.arturvasilov.stackexchangeclient.widget.UserTagView;

/**
 * @author Artur Vasilov
 */
public class ProfileActivity extends AppCompatActivity implements ProfileView {

    private static final String USER_KEY = "user";

    @Nullable
    private TextView mPersonName;
    private ImageView mPersonImage;
    private TextView mReputation;
    private TextView mProfileLink;

    private View mBadgesDivider;
    private View mBadgesTitle;
    private GridLayout mBadgesGrid;

    private View mTagsDivider;
    private View mTagsTitle;
    private ViewGroup mTagsLayout;

    public static void start(@NonNull Activity activity, @NonNull User user) {
        Intent intent = new Intent(activity, ProfileActivity.class);
        intent.putExtra(USER_KEY, user);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prepareWindow();
        setContentView(R.layout.ac_profile);
        setupActionBar();

        mPersonName = Views.findById(this, R.id.userName);
        mPersonImage = Views.findById(this, R.id.image);
        mReputation = Views.findById(this, R.id.reputationText);
        mProfileLink = Views.findById(this, R.id.linkText);
        mProfileLink.setMovementMethod(LinkMovementMethod.getInstance());

        mBadgesDivider = Views.findById(this, R.id.badgesDivider);
        mBadgesTitle = Views.findById(this, R.id.badgesTitle);
        mBadgesGrid = Views.findById(this, R.id.badgesGrid);

        mTagsDivider = Views.findById(this, R.id.tagsDivider);
        mTagsTitle = Views.findById(this, R.id.tagsTitle);
        mTagsLayout = Views.findById(this, R.id.tagsLayout);

        User user = (User) getIntent().getSerializableExtra(USER_KEY);
        ProfilePresenter presenter = new ProfilePresenter(this, getLoaderManager(), this,
                LoadingDialog.view(getSupportFragmentManager()),
                RxError.view(this, getSupportFragmentManager()),
                user);
        presenter.init();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showUserName(@NonNull String name) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(name);
        } else if (mPersonName != null) {
            mPersonName.setText(name);
        }
    }

    @Override
    public void showUserImage(@NonNull String url) {
        Picasso.with(this)
                .load(url)
                .into(mPersonImage);
    }

    @Override
    public void showProfileLink(@NonNull String profileLink) {
        mProfileLink.setText(HtmlCompat.fromHtml(profileLink));
    }

    @Override
    public void showReputation(@NonNull String reputation) {
        mReputation.setText(reputation);
    }

    @Override
    public void showBadges(@NonNull List<Badge> badges) {
        mBadgesGrid.removeAllViews();
        LayoutInflater inflater = LayoutInflater.from(this);
        for (Badge badge : badges) {
            BadgeTextView view = (BadgeTextView) inflater.inflate(R.layout.badge_item, mBadgesGrid, false);
            view.setBadge(badge);
            mBadgesGrid.addView(view);
        }

        mBadgesDivider.setVisibility(View.VISIBLE);
        mBadgesTitle.setVisibility(View.VISIBLE);
        mBadgesGrid.setVisibility(View.VISIBLE);
    }

    @Override
    public void showTopTags(@NonNull List<UserTag> tags) {
        mTagsLayout.removeAllViews();
        for (UserTag userTag : tags) {
            mTagsLayout.addView(new UserTagView(this, userTag));
        }

        mTagsDivider.setVisibility(View.VISIBLE);
        mTagsTitle.setVisibility(View.VISIBLE);
        mTagsLayout.setVisibility(View.VISIBLE);
    }

    private void prepareWindow() {
        if (getResources().getConfiguration().orientation != Configuration.ORIENTATION_PORTRAIT) {
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Slide transition = new Slide();
            transition.excludeTarget(android.R.id.statusBarBackground, true);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            getWindow().setEnterTransition(transition);
            getWindow().setReturnTransition(transition);
        }
    }

    private void setupActionBar() {
        if (getResources().getConfiguration().orientation != Configuration.ORIENTATION_PORTRAIT) {
            return;
        }
        setSupportActionBar(Views.findById(this, R.id.toolbar));
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }
}
