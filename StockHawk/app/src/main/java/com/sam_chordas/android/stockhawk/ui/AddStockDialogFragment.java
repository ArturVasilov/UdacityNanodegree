package com.sam_chordas.android.stockhawk.ui;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.InputType;
import android.view.Gravity;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.sam_chordas.android.stockhawk.R;
import com.sam_chordas.android.stockhawk.data.QuoteColumns;
import com.sam_chordas.android.stockhawk.data.QuoteProvider;
import com.sam_chordas.android.stockhawk.service.StockIntentService;

/**
 * @author Artur Vasilov
 */
public class AddStockDialogFragment extends DialogFragment {

    private static final String TAG = AddStockDialogFragment.class.getName();

    public static void show(@NonNull FragmentManager fragmentManager) {
        if (fragmentManager.findFragmentByTag(TAG) != null) {
            return;
        }
        AddStockDialogFragment dialogFragment = new AddStockDialogFragment();
        dialogFragment.show(fragmentManager, TAG);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new MaterialDialog.Builder(getActivity())
                .title(R.string.symbol_search)
                .content(R.string.content_test)
                .inputType(InputType.TYPE_CLASS_TEXT)
                .input(R.string.input_hint, R.string.input_prefill, new NewStockInputListener())
                .build();
    }

    private class NewStockInputListener implements MaterialDialog.InputCallback {

        @Override
        public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
            Cursor cursor = getActivity().getContentResolver().query(QuoteProvider.Quotes.CONTENT_URI,
                    new String[]{QuoteColumns.SYMBOL}, QuoteColumns.SYMBOL + "= ?",
                    new String[]{input.toString()}, null);
            if (cursor == null || cursor.isClosed()) {
                return;
            }

            if (cursor.getCount() != 0) {
                Toast toast =
                        Toast.makeText(getActivity(), getString(R.string.stock_exists), Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, Gravity.CENTER, 0);
                toast.show();
            } else {
                StockIntentService.start(getContext(), StockIntentService.ADD, input.toString());
            }
            cursor.close();
        }
    }
}
