
package android.databinding;
import arturvasilov.udacity.nanodegree.popularmoviesdatabinding.BR;
class DataBinderMapper {
    final static int TARGET_MIN_SDK = 16;
    public DataBinderMapper() {
    }
    public android.databinding.ViewDataBinding getDataBinder(android.databinding.DataBindingComponent bindingComponent, android.view.View view, int layoutId) {
        switch(layoutId) {
                case arturvasilov.udacity.nanodegree.popularmoviesdatabinding.R.layout.review_item:
                    return arturvasilov.udacity.nanodegree.popularmoviesdatabinding.databinding.ReviewItemBinding.bind(view, bindingComponent);
                case arturvasilov.udacity.nanodegree.popularmoviesdatabinding.R.layout.fragment_movie_details:
                    return arturvasilov.udacity.nanodegree.popularmoviesdatabinding.databinding.FragmentMovieDetailsBinding.bind(view, bindingComponent);
                case arturvasilov.udacity.nanodegree.popularmoviesdatabinding.R.layout.trailer_item:
                    return arturvasilov.udacity.nanodegree.popularmoviesdatabinding.databinding.TrailerItemBinding.bind(view, bindingComponent);
                case arturvasilov.udacity.nanodegree.popularmoviesdatabinding.R.layout.fragment_movies:
                    return arturvasilov.udacity.nanodegree.popularmoviesdatabinding.databinding.FragmentMoviesBinding.bind(view, bindingComponent);
        }
        return null;
    }
    android.databinding.ViewDataBinding getDataBinder(android.databinding.DataBindingComponent bindingComponent, android.view.View[] views, int layoutId) {
        switch(layoutId) {
        }
        return null;
    }
    int getLayoutId(String tag) {
        if (tag == null) {
            return 0;
        }
        final int code = tag.hashCode();
        switch(code) {
            case 101895846: {
                if(tag.equals("layout/review_item_0")) {
                    return arturvasilov.udacity.nanodegree.popularmoviesdatabinding.R.layout.review_item;
                }
                break;
            }
            case -2047199622: {
                if(tag.equals("layout/fragment_movie_details_0")) {
                    return arturvasilov.udacity.nanodegree.popularmoviesdatabinding.R.layout.fragment_movie_details;
                }
                break;
            }
            case -821275627: {
                if(tag.equals("layout/trailer_item_0")) {
                    return arturvasilov.udacity.nanodegree.popularmoviesdatabinding.R.layout.trailer_item;
                }
                break;
            }
            case 1657380766: {
                if(tag.equals("layout/fragment_movies_0")) {
                    return arturvasilov.udacity.nanodegree.popularmoviesdatabinding.R.layout.fragment_movies;
                }
                break;
            }
        }
        return 0;
    }
    String convertBrIdToString(int id) {
        if (id < 0 || id >= InnerBrLookup.sKeys.length) {
            return null;
        }
        return InnerBrLookup.sKeys[id];
    }
    private static class InnerBrLookup {
        static String[] sKeys = new String[]{
            "_all"
            ,"adapter"
            ,"expandedTitleColor"
            ,"favourite"
            ,"favouriteImage"
            ,"imageWidth"
            ,"layoutManager"
            ,"loading"
            ,"model"
            ,"movie"
            ,"movies"
            ,"refreshing"
            ,"review"
            ,"reviews"
            ,"reviewsAdapter"
            ,"reviewsEnabled"
            ,"reviewsLayoutManager"
            ,"trailer"
            ,"trailers"
            ,"trailersAdapter"
            ,"trailersEnabled"
            ,"trailersLayoutManager"
            ,"voteAverage"
            ,"voteMax"
            ,"year"};
    }
}