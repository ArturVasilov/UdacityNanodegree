package ru.arturvasilov.stackexchangeclient.presenter;

import android.app.LoaderManager;
import android.content.Context;
import android.support.annotation.NonNull;

import ru.arturvasilov.stackexchangeclient.R;
import ru.arturvasilov.stackexchangeclient.view.WalkthroughView;

/**
 * @author Artur Vasilov
 */
public class WalkthroughPresenter {

    public static final int PAGE_COUNT = 3;

    private final WalkthroughView mView;

    private final Context mContext;
    private final LoaderManager mLoaderManager;

    private int mCurrentItem = 0;

    public WalkthroughPresenter(@NonNull WalkthroughView view, @NonNull Context context,
                                @NonNull LoaderManager loaderManager) {
        mView = view;
        mContext = context;
        mLoaderManager = loaderManager;
    }

    public void init() {
        mView.showBenefit(0);
        mView.setActionButtonText(R.string.next_uppercase);

        //TODO : start loading
    }

    public void onActionButtonClick() {
        if (isLastBenefit()) {
            //TODO : check if loaded
        } else {
            mCurrentItem++;
            showCurrentBenefit();
        }
    }

    public void onBenefitSelected(boolean fromUser, int index) {
        if (fromUser) {
            mCurrentItem = index;
            showCurrentBenefit();
        }
    }

    private void showCurrentBenefit() {
        mView.showBenefit(mCurrentItem);
        if (isLastBenefit()) {
            mView.setActionButtonText(R.string.finish_uppercase);
        } else {
            mView.setActionButtonText(R.string.next_uppercase);
        }
    }

    private boolean isLastBenefit() {
        return mCurrentItem == PAGE_COUNT - 1;
    }
}
