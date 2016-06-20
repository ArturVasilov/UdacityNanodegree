package arturvasilov.udacity.nanodegree.myappportfolio;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

/**
 * @author Artur Vasilov
 */
public class PortfolioPresenter {

    private final Context mContext;
    private final PortfolioView mView;

    public PortfolioPresenter(@NonNull Context context, @NonNull PortfolioView view) {
        mContext = context;
        mView = view;
    }

    public void onPopularMoviesClick() {
        showText(R.string.popular_movies);
    }

    public void onStockHawkClick() {
        showText(R.string.stock_hawk);
    }

    public void onBuildItBetterClick() {
        showText(R.string.build_it_bigger);
    }

    public void onMakeYourAppMaterialClick() {
        showText(R.string.make_your_app_material);
    }

    public void onGoUbiquitousClick() {
        showText(R.string.go_ubiquitous);
    }

    public void onCapstoneClick() {
        showText(R.string.capstone);
    }

    public void onFabClick() {
        mView.navigateToUdacity();
    }

    private void showText(@StringRes int appNameId) {
        String appName = mContext.getString(appNameId);
        mView.showNavigationText(mContext.getString(R.string.navigation_text, appName));
    }
}
