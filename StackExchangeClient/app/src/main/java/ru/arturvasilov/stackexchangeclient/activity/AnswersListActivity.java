package ru.arturvasilov.stackexchangeclient.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import ru.arturvasilov.stackexchangeclient.R;
import ru.arturvasilov.stackexchangeclient.adapter.AnswersAdapter;
import ru.arturvasilov.stackexchangeclient.app.Env;
import ru.arturvasilov.stackexchangeclient.dialog.LoadingDialog;
import ru.arturvasilov.stackexchangeclient.model.content.Answer;
import ru.arturvasilov.stackexchangeclient.model.content.User;
import ru.arturvasilov.stackexchangeclient.presenter.AnswersListPresenter;
import ru.arturvasilov.stackexchangeclient.rx.RxError;
import ru.arturvasilov.stackexchangeclient.utils.Views;
import ru.arturvasilov.stackexchangeclient.view.AnswersListView;

/**
 * @author Artur Vasilov
 */
public class AnswersListActivity extends AppCompatActivity implements AnswersListView, AnswersAdapter.OnItemClickListener {

    private static final String USER_KEY = "user";

    private RecyclerView mRecyclerView;
    private TextView mEmptyView;

    private AnswersAdapter mAdapter;

    private AnswersListPresenter mPresenter;

    public static void start(@NonNull Activity activity, @NonNull User user) {
        Intent intent = new Intent(activity, AnswersListActivity.class);
        intent.putExtra(USER_KEY, user);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_my_answers);
        setSupportActionBar(Views.findById(this, R.id.toolbar));
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.answers);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        mRecyclerView = Views.findById(this, R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new AnswersAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        mEmptyView = Views.findById(this, R.id.empty);

        User user = (User) getIntent().getSerializableExtra(USER_KEY);
        mPresenter = new AnswersListPresenter(this, getLoaderManager(), this,
                LoadingDialog.view(getSupportFragmentManager()),
                RxError.view(this, getSupportFragmentManager()),
                user);
        mPresenter.init();
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
    public void showAnswers(@NonNull List<Answer> answers) {
        mAdapter.changeDataSet(answers);
    }

    @Override
    public void onItemClick(@NonNull Answer answer) {
        mPresenter.onItemClick(answer);
    }

    @Override
    public void showUrl(@NonNull String url) {
        Env.browseUrl(this, url);
    }

    @Override
    public void setEmptyText(@StringRes int textResId) {
        mEmptyView.setText(textResId);
    }

    @Override
    public void showEmptyListView() {
        mRecyclerView.setVisibility(View.GONE);
        mEmptyView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideEmptyListView() {
        mRecyclerView.setVisibility(View.VISIBLE);
        mEmptyView.setVisibility(View.GONE);
    }
}