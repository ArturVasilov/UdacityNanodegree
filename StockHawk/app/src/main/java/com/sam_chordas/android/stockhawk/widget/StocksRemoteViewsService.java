package com.sam_chordas.android.stockhawk.widget;

import android.content.Intent;
import android.database.Cursor;
import android.os.Binder;
import android.widget.AdapterView;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.sam_chordas.android.stockhawk.R;
import com.sam_chordas.android.stockhawk.data.QuoteColumns;
import com.sam_chordas.android.stockhawk.data.QuoteProvider;
import com.sam_chordas.android.stockhawk.ui.MyStocksActivity;

/**
 * @author Artur Vasilov
 */
public class StocksRemoteViewsService extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new StocksViewsFactory();
    }

    private class StocksViewsFactory implements RemoteViewsService.RemoteViewsFactory {

        private Cursor mData = null;

        @Override
        public void onCreate() {
            // Do nothing
        }

        @Override
        public void onDataSetChanged() {
            if (mData != null) {
                mData.close();
            }

            final long identityToken = Binder.clearCallingIdentity();

            mData = getContentResolver().query(QuoteProvider.Quotes.CONTENT_URI,
                    new String[]{QuoteColumns._ID, QuoteColumns.SYMBOL, QuoteColumns.BIDPRICE,
                            QuoteColumns.PERCENT_CHANGE, QuoteColumns.CHANGE, QuoteColumns.ISUP},
                    QuoteColumns.ISCURRENT + " = ?",
                    new String[]{"1"},
                    null);

            Binder.restoreCallingIdentity(identityToken);
        }

        @Override
        public void onDestroy() {
            if (mData != null) {
                mData.close();
                mData = null;
            }
        }

        @Override
        public int getCount() {
            return mData == null ? 0 : mData.getCount();
        }

        @Override
        public RemoteViews getViewAt(int position) {
            if (position == AdapterView.INVALID_POSITION ||
                    mData == null || !mData.moveToPosition(position)) {
                return null;
            }
            RemoteViews views = new RemoteViews(getPackageName(), R.layout.widget_list_item);
            String symbol = mData.getString(mData.getColumnIndex(QuoteColumns.SYMBOL));
            String price = mData.getString(mData.getColumnIndex(QuoteColumns.BIDPRICE));

            views.setTextViewText(R.id.stock_symbol, symbol);
            views.setTextViewText(R.id.bid_price, price);

            String stockId = mData.getString(mData.getColumnIndex(QuoteColumns._ID));
            final Intent fillInIntent = new Intent();
            fillInIntent.putExtra(MyStocksActivity.SELECTED_STOCK_ID, stockId);
            views.setOnClickFillInIntent(R.id.widget_list_item, fillInIntent);
            return views;
        }

        @Override
        public RemoteViews getLoadingView() {
            return new RemoteViews(getPackageName(), R.layout.widget_list_item);
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public long getItemId(int position) {
            if (mData.moveToPosition(position)) {
                return Long.parseLong(mData.getString(mData.getColumnIndex(QuoteColumns._ID)));
            }
            return position;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }
    }
}
