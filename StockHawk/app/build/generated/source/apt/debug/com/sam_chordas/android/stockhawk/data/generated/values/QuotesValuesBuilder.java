package com.sam_chordas.android.stockhawk.data.generated.values;

import android.content.ContentValues;
import com.sam_chordas.android.stockhawk.data.QuoteColumns;
import java.lang.Integer;
import java.lang.Long;
import java.lang.String;

public class QuotesValuesBuilder {
  ContentValues values = new ContentValues();

  public QuotesValuesBuilder Id(Integer value) {
    values.put(QuoteColumns._ID, value);
    return this;
  }

  public QuotesValuesBuilder Id(Long value) {
    values.put(QuoteColumns._ID, value);
    return this;
  }

  public QuotesValuesBuilder symbol(String value) {
    values.put(QuoteColumns.SYMBOL, value);
    return this;
  }

  public QuotesValuesBuilder percentChange(String value) {
    values.put(QuoteColumns.PERCENT_CHANGE, value);
    return this;
  }

  public QuotesValuesBuilder change(String value) {
    values.put(QuoteColumns.CHANGE, value);
    return this;
  }

  public QuotesValuesBuilder bidprice(String value) {
    values.put(QuoteColumns.BIDPRICE, value);
    return this;
  }

  public QuotesValuesBuilder created(String value) {
    values.put(QuoteColumns.CREATED, value);
    return this;
  }

  public QuotesValuesBuilder isup(Integer value) {
    values.put(QuoteColumns.ISUP, value);
    return this;
  }

  public QuotesValuesBuilder isup(Long value) {
    values.put(QuoteColumns.ISUP, value);
    return this;
  }

  public QuotesValuesBuilder iscurrent(Integer value) {
    values.put(QuoteColumns.ISCURRENT, value);
    return this;
  }

  public QuotesValuesBuilder iscurrent(Long value) {
    values.put(QuoteColumns.ISCURRENT, value);
    return this;
  }

  public ContentValues values() {
    return values;
  }
}
