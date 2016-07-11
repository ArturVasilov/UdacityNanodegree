package com.sam_chordas.android.stockhawk.ui;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.gcm.GcmNetworkManager;
import com.google.android.gms.gcm.PeriodicTask;
import com.google.android.gms.gcm.Task;
import com.melnykov.fab.FloatingActionButton;
import com.sam_chordas.android.stockhawk.R;
import com.sam_chordas.android.stockhawk.adapter.QuoteCursorAdapter;
import com.sam_chordas.android.stockhawk.adapter.RecyclerViewItemClickListener;
import com.sam_chordas.android.stockhawk.data.QuoteColumns;
import com.sam_chordas.android.stockhawk.data.QuoteProvider;
import com.sam_chordas.android.stockhawk.service.StockIntentService;
import com.sam_chordas.android.stockhawk.service.StockTaskService;
import com.sam_chordas.android.stockhawk.touch_helper.SimpleItemTouchHelperCallback;
import com.sam_chordas.android.stockhawk.utils.JsonParserUtils;
import com.sam_chordas.android.stockhawk.utils.Network;

public class MyStocksActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int CURSOR_LOADER_ID = 0;

    public static final String SELECTED_STOCK_ID = "selected_weather_id";

    /**
     * Used to store the last screen title. For use in {@link #setupActionBar()}.
     */
    private CharSequence mTitle;
    private QuoteCursorAdapter mCursorAdapter;

    private RecyclerView mRecyclerView;
    private View mEmptyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_stocks);
        // The intent service is for executing immediate pulls from the Yahoo API
        // GCMTaskService can only schedule tasks, they cannot execute immediately
        boolean isConnected = Network.hasNetwork(this);
        if (savedInstanceState == null) {
            // Run the initialize task service so that some stocks appear upon an empty database
            if (isConnected) {
                StockIntentService.start(this, StockIntentService.INIT, null);
            } else {
                networkToast();
            }
        }

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        getLoaderManager().initLoader(CURSOR_LOADER_ID, null, this);

        mCursorAdapter = new QuoteCursorAdapter(this, null);
        mRecyclerView.addOnItemTouchListener(new RecyclerViewItemClickListener(this,
                new RecyclerViewItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View v, int position) {
                        Cursor cursor = mCursorAdapter.getCursor();
                        if (cursor != null && !cursor.isClosed() && cursor.moveToPosition(position)) {
                            String stockId = cursor.getString(cursor.getColumnIndex(QuoteColumns._ID));
                            StockDetailsActivity.start(MyStocksActivity.this, stockId);
                        }
                    }
                }));
        mRecyclerView.setAdapter(mCursorAdapter);

        mEmptyView = findViewById(R.id.empty);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.attachToRecyclerView(mRecyclerView);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isConnected = Network.hasNetwork(MyStocksActivity.this);
                if (isConnected) {
                    AddStockDialogFragment.show(getFragmentManager());
                } else {
                    networkToast();
                }

            }
        });

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mCursorAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);

        mTitle = getTitle();
        if (isConnected) {
            long period = 3600;
            long flex = 10;
            String periodicTag = "periodic";

            // create a periodic task to pull stocks once every hour after the app has been opened. This
            // is so Widget data stays up to date.
            PeriodicTask periodicTask = new PeriodicTask.Builder()
                    .setService(StockTaskService.class)
                    .setPeriod(period)
                    .setFlex(flex)
                    .setTag(periodicTag)
                    .setRequiredNetwork(Task.NETWORK_STATE_CONNECTED)
                    .setRequiresCharging(false)
                    .build();
            // Schedule task with tag "periodic." This ensure that only the stocks present in the DB
            // are updated.
            GcmNetworkManager.getInstance(this).schedule(periodicTask);
        }

        if (getIntent() != null && getIntent().hasExtra(SELECTED_STOCK_ID)) {
            String stockId = getIntent().getStringExtra(SELECTED_STOCK_ID);
            StockDetailsActivity.start(this, stockId);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getLoaderManager().restartLoader(CURSOR_LOADER_ID, null, this);
    }

    public void networkToast() {
        Toast.makeText(this, getString(R.string.network_toast), Toast.LENGTH_SHORT).show();
    }

    public void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setTitle(mTitle);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_stocks, menu);
        setupActionBar();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_change_units) {
            // this is for changing stock changes from percent value to dollar value
            JsonParserUtils.showPercent = !JsonParserUtils.showPercent;
            getContentResolver().notifyChange(QuoteProvider.Quotes.CONTENT_URI, null);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        // This narrows the return to only the stocks that are most current.
        return new CursorLoader(this, QuoteProvider.Quotes.CONTENT_URI,
                new String[]{QuoteColumns._ID, QuoteColumns.SYMBOL, QuoteColumns.BIDPRICE,
                        QuoteColumns.PERCENT_CHANGE, QuoteColumns.CHANGE, QuoteColumns.ISUP},
                QuoteColumns.ISCURRENT + " = ?",
                new String[]{"1"},
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        updateStocks(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        updateStocks(null);
    }

    private void updateStocks(@Nullable Cursor cursor) {
        mCursorAdapter.swapCursor(cursor);
        Cursor adapterCursor = mCursorAdapter.getCursor();
        if (adapterCursor == null || adapterCursor.isClosed() || adapterCursor.getCount() == 0) {
            showEmptyView();
        } else {
            showStocksVisible();
        }
    }

    private void showStocksVisible() {
        mRecyclerView.setVisibility(View.VISIBLE);
        mEmptyView.setVisibility(View.GONE);
    }

    private void showEmptyView() {
        mRecyclerView.setVisibility(View.GONE);
        mEmptyView.setVisibility(View.VISIBLE);
    }

}
