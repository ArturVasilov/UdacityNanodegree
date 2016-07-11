package com.sam_chordas.android.stockhawk.adapter;

import android.database.Cursor;
import android.database.DataSetObserver;
import android.provider.BaseColumns;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

/**
 * Created by sam_chordas on 10/6/15.
 * Credit to skyfishjy gist:
 * https://gist.github.com/skyfishjy/443b7448f59be978bc59
 * for the CursorRecyclerViewApater.java code and idea.
 */
public abstract class CursorRecyclerViewAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    private Cursor mCursor;
    private boolean mIsDataValid;
    private int mRowIdColumn;
    private final DataSetObserver mDataSetObserver;

    public CursorRecyclerViewAdapter(@Nullable Cursor cursor) {
        mCursor = cursor;
        mIsDataValid = cursor != null;
        mRowIdColumn = mIsDataValid ? mCursor.getColumnIndex(BaseColumns._ID) : -1;
        mDataSetObserver = new NotifyingDataSetObserver();
        if (mIsDataValid) {
            mCursor.registerDataSetObserver(mDataSetObserver);
        }
    }

    @Nullable
    public Cursor getCursor() {
        return mCursor;
    }

    @Override
    public int getItemCount() {
        if (mIsDataValid && mCursor != null) {
            return mCursor.getCount();
        }
        return 0;
    }

    @Override
    public long getItemId(int position) {
        if (mIsDataValid && mCursor != null && mCursor.moveToPosition(position)) {
            return mCursor.getLong(mRowIdColumn);
        }
        return 0;
    }

    @Override
    public void setHasStableIds(boolean hasStableIds) {
        super.setHasStableIds(true);
    }

    protected abstract void onBindViewHolder(VH viewHolder, Cursor cursor);

    @Override
    public void onBindViewHolder(VH viewHolder, int position) {
        if (!mIsDataValid) {
            throw new IllegalStateException("This should only be called when Cursor is valid");
        }
        if (!mCursor.moveToPosition(position)) {
            throw new IllegalStateException("Could not move Cursor to position: " + position);
        }

        onBindViewHolder(viewHolder, mCursor);
    }

    public void swapCursor(@Nullable Cursor newCursor) {
        if (newCursor == mCursor) {
            return;
        }
        final Cursor oldCursor = mCursor;
        if (oldCursor != null && mDataSetObserver != null) {
            oldCursor.unregisterDataSetObserver(mDataSetObserver);
        }
        mCursor = newCursor;
        if (mCursor != null) {
            if (mDataSetObserver != null) {
                mCursor.registerDataSetObserver(mDataSetObserver);
            }
            mRowIdColumn = newCursor.getColumnIndexOrThrow("_id");
            mIsDataValid = true;
            notifyDataSetChanged();
        } else {
            mRowIdColumn = -1;
            mIsDataValid = false;
            notifyDataSetChanged();
        }
    }

    private class NotifyingDataSetObserver extends DataSetObserver {
        @Override
        public void onChanged() {
            super.onChanged();
            mIsDataValid = true;
            notifyDataSetChanged();
        }

        @Override
        public void onInvalidated() {
            super.onInvalidated();
            mIsDataValid = false;
            notifyDataSetChanged();
        }
    }
}
