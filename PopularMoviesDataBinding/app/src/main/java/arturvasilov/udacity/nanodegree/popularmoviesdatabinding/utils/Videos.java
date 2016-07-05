package arturvasilov.udacity.nanodegree.popularmoviesdatabinding.utils;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;

import arturvasilov.udacity.nanodegree.popularmoviesdatabinding.AppDelegate;
import arturvasilov.udacity.nanodegree.popularmoviesdatabinding.model.content.Video;

/**
 * @author Artur Vasilov
 */
public final class Videos {

    private static final String YOUTUBE = "https://www.youtube.com/watch?v=";

    private Videos() {
    }

    public static void browseVideo(@NonNull Video video) {
        String videoUrl = YOUTUBE + video.getKey();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.parse(videoUrl), "video/*");
        AppDelegate.getAppContext().startActivity(intent);
    }

}
