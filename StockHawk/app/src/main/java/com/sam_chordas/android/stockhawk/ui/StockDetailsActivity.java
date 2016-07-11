package com.sam_chordas.android.stockhawk.ui;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.db.chart.model.LineSet;
import com.db.chart.view.AxisController;
import com.db.chart.view.LineChartView;
import com.sam_chordas.android.stockhawk.R;
import com.sam_chordas.android.stockhawk.data.QuoteColumns;
import com.sam_chordas.android.stockhawk.data.QuoteProvider;

/**
 * @author Artur Vasilov
 */
public class StockDetailsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final String SYMBOL = "symbol";

    private String mSymbol;

    private LineChartView mChartView;
    private TextView mEmptyChartTextView;

    public static void start(@NonNull Activity activity, @NonNull String symbol) {
        Intent intent = new Intent(activity, StockDetailsActivity.class);
        intent.putExtra(SYMBOL, symbol);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_graph);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        mChartView = (LineChartView) findViewById(R.id.linechart);
        mChartView.setLabelsColor(ContextCompat.getColor(this, R.color.material_blue_500))
                .setBorderSpacing(16)
                .setXAxis(false)
                .setYAxis(false)
                .setXLabels(AxisController.LabelPosition.NONE);

        mEmptyChartTextView = (TextView) findViewById(R.id.empty);
        mEmptyChartTextView.setText(R.string.empty_chart);

        mSymbol = getIntent().getStringExtra(SYMBOL);
        getLoaderManager().initLoader(R.id.stock_details_loader, Bundle.EMPTY, this);
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
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this, QuoteProvider.Quotes.CONTENT_URI,
                new String[]{QuoteColumns._ID, QuoteColumns.SYMBOL, QuoteColumns.BIDPRICE,
                        QuoteColumns.PERCENT_CHANGE, QuoteColumns.CHANGE, QuoteColumns.ISUP},
                QuoteColumns.SYMBOL + " = ?",
                new String[]{mSymbol},
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        if (cursor != null && !cursor.isClosed() && cursor.moveToFirst()) {
            renderChart(cursor);
        } else {
            showEmptyView();
        }
        getLoaderManager().destroyLoader(R.id.stock_details_loader);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        // Do nothing
    }

    private void renderChart(@NonNull Cursor cursor) {
        float minBidPrice = Float.MAX_VALUE;
        float maxBidPrice = Float.MIN_VALUE;

        LineSet lineSet = new LineSet();
        do {
            String label = cursor.getString(cursor.getColumnIndex(QuoteColumns.BIDPRICE));
            float value = Float.parseFloat(label.replace(',', '.'));
            lineSet.addPoint(label, value);

            if (minBidPrice > value) {
                minBidPrice = value;
            }
            if (maxBidPrice < value) {
                maxBidPrice = value;
            }
        } while (cursor.moveToNext());

        float minMargin = 2;
        float maxMargin = 5;
        float margin = (maxBidPrice - minBidPrice) / 2;
        margin = Math.min(margin, maxMargin);
        margin = Math.max(margin, minMargin);

        lineSet.setColor(ContextCompat.getColor(this, R.color.chart_line_color))
                .setFill(ContextCompat.getColor(this, R.color.chart_fill_color))
                .setDotsColor(ContextCompat.getColor(this, R.color.chart_dots_color))
                .setThickness(5)
                .setDashed(new float[]{8f, 8f});

        mChartView
                .setAxisBorderValues(Math.round(Math.max(0f, minBidPrice - margin)), Math.round(maxBidPrice + margin))
                .addData(lineSet);

        if (lineSet.size() > 1) {
            showChart();
            mChartView.show();
        } else {
            showEmptyView();
        }
    }

    private void showChart() {
        mEmptyChartTextView.setVisibility(View.GONE);
        mChartView.setVisibility(View.VISIBLE);
    }

    private void showEmptyView() {
        mEmptyChartTextView.setVisibility(View.VISIBLE);
        mChartView.setVisibility(View.GONE);
    }
}
