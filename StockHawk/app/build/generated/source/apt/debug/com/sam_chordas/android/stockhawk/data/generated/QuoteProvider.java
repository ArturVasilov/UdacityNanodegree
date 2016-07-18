package com.sam_chordas.android.stockhawk.data.generated;

import android.content.ContentProvider;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.OperationApplicationException;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import java.lang.IllegalArgumentException;
import java.lang.Override;
import java.lang.String;
import java.util.ArrayList;
import net.simonvt.schematic.utils.SelectionBuilder;

public class QuoteProvider extends ContentProvider {
  public static final String AUTHORITY = "com.sam_chordas.android.stockhawk.data.QuoteProvider";

  private static final int QUOTES_CONTENT_URI = 0;

  private static final int QUOTES_QUOTE_ID = 1;

  private static final UriMatcher MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

  static {
    MATCHER.addURI(AUTHORITY, "quotes", QUOTES_CONTENT_URI);
    MATCHER.addURI(AUTHORITY, "quotes/*", QUOTES_QUOTE_ID);
  }

  private SQLiteOpenHelper database;

  @Override
  public boolean onCreate() {
    database = QuoteDatabase.getInstance(getContext());
    return true;
  }

  private SelectionBuilder getBuilder(String table) {
    SelectionBuilder builder = new SelectionBuilder();
    return builder;
  }

  private long[] insertValues(SQLiteDatabase db, String table, ContentValues[] values) {
    long[] ids = new long[values.length];
    for (int i = 0; i < values.length; i++) {
      ContentValues cv = values[i];
      db.insertOrThrow(table, null, cv);
    }
    return ids;
  }

  @Override
  public int bulkInsert(Uri uri, ContentValues[] values) {
    final SQLiteDatabase db = database.getWritableDatabase();
    db.beginTransaction();
    try {
      switch(MATCHER.match(uri)) {
        case QUOTES_CONTENT_URI: {
          long[] ids = insertValues(db, "quotes", values);
          getContext().getContentResolver().notifyChange(uri, null);
          break;
        }
        case QUOTES_QUOTE_ID: {
          long[] ids = insertValues(db, "quotes", values);
          getContext().getContentResolver().notifyChange(uri, null);
          break;
        }
      }
      db.setTransactionSuccessful();
    } finally {
      db.endTransaction();
    }
    return values.length;
  }

  @Override
  public ContentProviderResult[] applyBatch(ArrayList<ContentProviderOperation> ops) throws OperationApplicationException {
    ContentProviderResult[] results;
    final SQLiteDatabase db = database.getWritableDatabase();
    db.beginTransaction();
    try {
      results = super.applyBatch(ops);
      db.setTransactionSuccessful();
    } finally {
      db.endTransaction();
    }
    return results;
  }

  @Override
  public String getType(Uri uri) {
    switch(MATCHER.match(uri)) {
      case QUOTES_CONTENT_URI: {
        return "vnd.android.cursor.dir/quote";
      }
      case QUOTES_QUOTE_ID: {
        return "vnd.android.cursor.item/quote";
      }
      default: {
        throw new IllegalArgumentException("Unknown URI " + uri);
      }
    }
  }

  @Override
  public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
    final SQLiteDatabase db = database.getReadableDatabase();
    switch(MATCHER.match(uri)) {
      case QUOTES_CONTENT_URI: {
        SelectionBuilder builder = getBuilder("Quotes");
        String table = "quotes";
        final String groupBy = null;
        final String having = null;
        final String limit = null;
        Cursor cursor = builder.table(table)
            .where(selection, selectionArgs)
            .query(db, projection, groupBy, having, sortOrder, limit);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
      }
      case QUOTES_QUOTE_ID: {
        SelectionBuilder builder = getBuilder("Quotes");
        String table = "quotes";
        final String groupBy = null;
        final String having = null;
        final String limit = null;
        Cursor cursor = builder.table(table)
            .where("symbol=?" , uri.getPathSegments().get(1))
            .where(selection, selectionArgs)
            .query(db, projection, groupBy, having, sortOrder, limit);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
      }
      default: {
        throw new IllegalArgumentException("Unknown URI " + uri);
      }
    }
  }

  @Override
  public Uri insert(Uri uri, ContentValues values) {
    final SQLiteDatabase db = database.getWritableDatabase();
    switch(MATCHER.match(uri)) {
      case QUOTES_CONTENT_URI: {
        final long id = db.insertOrThrow("quotes", null, values);
        getContext().getContentResolver().notifyChange(uri, null);
        return ContentUris.withAppendedId(uri, id);
      }
      case QUOTES_QUOTE_ID: {
        final long id = db.insertOrThrow("quotes", null, values);
        getContext().getContentResolver().notifyChange(uri, null);
        return ContentUris.withAppendedId(uri, id);
      }
      default: {
        throw new IllegalArgumentException("Unknown URI " + uri);
      }
    }
  }

  @Override
  public int update(Uri uri, ContentValues values, String where, String[] whereArgs) {
    final SQLiteDatabase db = database.getWritableDatabase();
    switch(MATCHER.match(uri)) {
      case QUOTES_CONTENT_URI: {
        SelectionBuilder builder = getBuilder("Quotes");
        builder.where(where, whereArgs);
        final int count = builder.table("quotes")
            .update(db, values);
        if (count > 0) {
          getContext().getContentResolver().notifyChange(uri, null);
        }
        return count;
      }
      case QUOTES_QUOTE_ID: {
        SelectionBuilder builder = getBuilder("Quotes");
        builder.where("symbol=?", uri.getPathSegments().get(1));
        builder.where(where, whereArgs);
        final int count = builder.table("quotes")
            .update(db, values);
        if (count > 0) {
          getContext().getContentResolver().notifyChange(uri, null);
        }
        return count;
      }
      default: {
        throw new IllegalArgumentException("Unknown URI " + uri);
      }
    }
  }

  @Override
  public int delete(Uri uri, String where, String[] whereArgs) {
    final SQLiteDatabase db = database.getWritableDatabase();
    switch(MATCHER.match(uri)) {
      case QUOTES_CONTENT_URI: {
        SelectionBuilder builder = getBuilder("Quotes");
        builder.where(where, whereArgs);
        final int count = builder
            .table("quotes")
            .delete(db);
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
      }
      case QUOTES_QUOTE_ID: {
        SelectionBuilder builder = getBuilder("Quotes");
        builder.where("symbol=?", uri.getPathSegments().get(1));
        builder.where(where, whereArgs);
        final int count = builder
            .table("quotes")
            .delete(db);
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
      }
      default: {
        throw new IllegalArgumentException("Unknown URI " + uri);
      }
    }
  }
}
