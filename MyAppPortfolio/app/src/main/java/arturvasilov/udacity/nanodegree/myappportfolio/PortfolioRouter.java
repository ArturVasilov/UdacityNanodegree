package arturvasilov.udacity.nanodegree.myappportfolio;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;

/**
 * @author Artur Vasilov
 */
public class PortfolioRouter {

    private final Context mContext;

    public PortfolioRouter(@NonNull Context context) {
        mContext = context;
    }

    public void navigateToUdacity() {
        String url = "https://www.udacity.com/course/android-developer-nanodegree--nd801";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        mContext.startActivity(intent);
    }

}
