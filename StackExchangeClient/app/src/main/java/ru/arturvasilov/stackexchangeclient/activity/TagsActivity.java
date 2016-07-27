package ru.arturvasilov.stackexchangeclient.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import ru.arturvasilov.stackexchangeclient.R;
import ru.arturvasilov.stackexchangeclient.adapter.TagsAdapter;
import ru.arturvasilov.stackexchangeclient.model.content.Tag;
import ru.arturvasilov.stackexchangeclient.presenter.TagsPresenter;
import ru.arturvasilov.stackexchangeclient.utils.Views;
import ru.arturvasilov.stackexchangeclient.view.TagsView;

/**
 * @author Artur Vasilov
 */
public class TagsActivity extends AppCompatActivity implements TagsView {

    private EditText mSearchEdit;
    private View mClearButton;
    private RecyclerView mRecyclerView;
    private TextView mEmptyView;

    private TagsAdapter mAdapter;

    private TagsPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_tags);
        setSupportActionBar(Views.findById(this, R.id.toolbar));
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        mPresenter = new TagsPresenter(this, getLoaderManager(), this);

        mSearchEdit = Views.findById(this, R.id.searchEdit);
        mClearButton = Views.findById(this, R.id.btnClear);
        mRecyclerView = Views.findById(this, R.id.recyclerView);
        mEmptyView = Views.findById(this, R.id.empty);

        mSearchEdit.addTextChangedListener(new SearchListener());
        mClearButton.setOnClickListener(view -> mPresenter.onClearButtonClick());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new TagsAdapter(mPresenter::onFavouriteClick);
        mRecyclerView.setAdapter(mAdapter);

        mPresenter.init();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void clearText() {
        mSearchEdit.setText("");
    }

    @Override
    public void setEmptyText(@StringRes int textId) {
        mEmptyView.setText(textId);
    }

    @Override
    public void showTags(@NonNull List<Tag> tags) {
        mAdapter.changeDataSet(tags);
    }

    @Override
    public void notifyChanged() {
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showClearButton() {
        mClearButton.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideAllElements() {
        mRecyclerView.setVisibility(View.GONE);
        mEmptyView.setVisibility(View.GONE);
        mClearButton.setVisibility(View.INVISIBLE);
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

    private class SearchListener implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            // Do nothing
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            // Do nothing
        }

        @Override
        public void afterTextChanged(Editable editable) {
            String input = editable.toString();
            mPresenter.onInput(input);
        }
    }
}
