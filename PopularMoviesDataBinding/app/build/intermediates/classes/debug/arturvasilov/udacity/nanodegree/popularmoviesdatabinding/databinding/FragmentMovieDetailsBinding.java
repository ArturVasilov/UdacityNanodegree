package arturvasilov.udacity.nanodegree.popularmoviesdatabinding.databinding;
import arturvasilov.udacity.nanodegree.popularmoviesdatabinding.R;
import arturvasilov.udacity.nanodegree.popularmoviesdatabinding.BR;
import android.view.View;
public class FragmentMovieDetailsBinding extends android.databinding.ViewDataBinding  {

    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.app_bar, 13);
        sViewsWithIds.put(R.id.toolbar, 14);
    }
    // views
    public final android.support.design.widget.AppBarLayout appBar;
    private final android.support.design.widget.CoordinatorLayout mboundView0;
    private final android.widget.TextView mboundView10;
    private final arturvasilov.udacity.nanodegree.popularmoviesdatabinding.widget.EmptyRecyclerView mboundView11;
    private final android.widget.ProgressBar mboundView12;
    private final android.widget.ImageView mboundView2;
    private final android.widget.TextView mboundView3;
    private final android.widget.TextView mboundView4;
    private final android.widget.TextView mboundView5;
    private final android.widget.ImageView mboundView6;
    private final android.widget.LinearLayout mboundView7;
    private final android.widget.TextView mboundView8;
    private final arturvasilov.udacity.nanodegree.popularmoviesdatabinding.widget.EmptyRecyclerView mboundView9;
    public final android.support.v7.widget.Toolbar toolbar;
    public final android.support.design.widget.CollapsingToolbarLayout toolbarLayout;
    // variables
    private arturvasilov.udacity.nanodegree.popularmoviesdatabinding.model.content.Movie mMovie;
    private arturvasilov.udacity.nanodegree.popularmoviesdatabinding.databinding.viewmodel.MovieDetailsViewModel mModel;
    // values
    // listeners
    private OnClickListenerImpl mAndroidViewViewOnCl;
    private OnItemClickListenerI mArturvasilovUdacity;
    // Inverse Binding Event Handlers

    public FragmentMovieDetailsBinding(android.databinding.DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 1);
        final Object[] bindings = mapBindings(bindingComponent, root, 15, sIncludes, sViewsWithIds);
        this.appBar = (android.support.design.widget.AppBarLayout) bindings[13];
        this.mboundView0 = (android.support.design.widget.CoordinatorLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView10 = (android.widget.TextView) bindings[10];
        this.mboundView10.setTag(null);
        this.mboundView11 = (arturvasilov.udacity.nanodegree.popularmoviesdatabinding.widget.EmptyRecyclerView) bindings[11];
        this.mboundView11.setTag(null);
        this.mboundView12 = (android.widget.ProgressBar) bindings[12];
        this.mboundView12.setTag(null);
        this.mboundView2 = (android.widget.ImageView) bindings[2];
        this.mboundView2.setTag(null);
        this.mboundView3 = (android.widget.TextView) bindings[3];
        this.mboundView3.setTag(null);
        this.mboundView4 = (android.widget.TextView) bindings[4];
        this.mboundView4.setTag(null);
        this.mboundView5 = (android.widget.TextView) bindings[5];
        this.mboundView5.setTag(null);
        this.mboundView6 = (android.widget.ImageView) bindings[6];
        this.mboundView6.setTag(null);
        this.mboundView7 = (android.widget.LinearLayout) bindings[7];
        this.mboundView7.setTag(null);
        this.mboundView8 = (android.widget.TextView) bindings[8];
        this.mboundView8.setTag(null);
        this.mboundView9 = (arturvasilov.udacity.nanodegree.popularmoviesdatabinding.widget.EmptyRecyclerView) bindings[9];
        this.mboundView9.setTag(null);
        this.toolbar = (android.support.v7.widget.Toolbar) bindings[14];
        this.toolbarLayout = (android.support.design.widget.CollapsingToolbarLayout) bindings[1];
        this.toolbarLayout.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x20000L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    public boolean setVariable(int variableId, Object variable) {
        switch(variableId) {
            case BR.movie :
                setMovie((arturvasilov.udacity.nanodegree.popularmoviesdatabinding.model.content.Movie) variable);
                return true;
            case BR.model :
                setModel((arturvasilov.udacity.nanodegree.popularmoviesdatabinding.databinding.viewmodel.MovieDetailsViewModel) variable);
                return true;
        }
        return false;
    }

    public void setMovie(arturvasilov.udacity.nanodegree.popularmoviesdatabinding.model.content.Movie movie) {
        this.mMovie = movie;
        synchronized(this) {
            mDirtyFlags |= 0x2L;
        }
        notifyPropertyChanged(BR.movie);
        super.requestRebind();
    }
    public arturvasilov.udacity.nanodegree.popularmoviesdatabinding.model.content.Movie getMovie() {
        return mMovie;
    }
    public void setModel(arturvasilov.udacity.nanodegree.popularmoviesdatabinding.databinding.viewmodel.MovieDetailsViewModel model) {
        updateRegistration(0, model);
        this.mModel = model;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.model);
        super.requestRebind();
    }
    public arturvasilov.udacity.nanodegree.popularmoviesdatabinding.databinding.viewmodel.MovieDetailsViewModel getModel() {
        return mModel;
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeModel((arturvasilov.udacity.nanodegree.popularmoviesdatabinding.databinding.viewmodel.MovieDetailsViewModel) object, fieldId);
        }
        return false;
    }
    private boolean onChangeModel(arturvasilov.udacity.nanodegree.popularmoviesdatabinding.databinding.viewmodel.MovieDetailsViewModel model, int fieldId) {
        switch (fieldId) {
            case BR.expandedTitleColor: {
                synchronized(this) {
                        mDirtyFlags |= 0x4L;
                }
                return true;
            }
            case BR.imageWidth: {
                synchronized(this) {
                        mDirtyFlags |= 0x8L;
                }
                return true;
            }
            case BR.year: {
                synchronized(this) {
                        mDirtyFlags |= 0x10L;
                }
                return true;
            }
            case BR.voteAverage: {
                synchronized(this) {
                        mDirtyFlags |= 0x20L;
                }
                return true;
            }
            case BR.voteMax: {
                synchronized(this) {
                        mDirtyFlags |= 0x40L;
                }
                return true;
            }
            case BR.favouriteImage: {
                synchronized(this) {
                        mDirtyFlags |= 0x80L;
                }
                return true;
            }
            case BR.loading: {
                synchronized(this) {
                        mDirtyFlags |= 0x100L;
                }
                return true;
            }
            case BR.trailersEnabled: {
                synchronized(this) {
                        mDirtyFlags |= 0x200L;
                }
                return true;
            }
            case BR.trailersAdapter: {
                synchronized(this) {
                        mDirtyFlags |= 0x400L;
                }
                return true;
            }
            case BR.trailers: {
                synchronized(this) {
                        mDirtyFlags |= 0x800L;
                }
                return true;
            }
            case BR.trailersLayoutManager: {
                synchronized(this) {
                        mDirtyFlags |= 0x1000L;
                }
                return true;
            }
            case BR.reviewsEnabled: {
                synchronized(this) {
                        mDirtyFlags |= 0x2000L;
                }
                return true;
            }
            case BR.reviewsAdapter: {
                synchronized(this) {
                        mDirtyFlags |= 0x4000L;
                }
                return true;
            }
            case BR.reviews: {
                synchronized(this) {
                        mDirtyFlags |= 0x8000L;
                }
                return true;
            }
            case BR.reviewsLayoutManager: {
                synchronized(this) {
                        mDirtyFlags |= 0x10000L;
                }
                return true;
            }
            case BR._all: {
                synchronized(this) {
                        mDirtyFlags |= 0x1L;
                }
                return true;
            }
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        java.lang.String posterPathMovie = null;
        int expandedTitleColorMo = 0;
        android.view.View.OnClickListener androidViewViewOnCli = null;
        boolean loadingModel = false;
        arturvasilov.udacity.nanodegree.popularmoviesdatabinding.widget.TrailersAdapter trailersAdapterModel = null;
        java.lang.String androidStringMovieTi = null;
        int trailersEnabledModel = 0;
        arturvasilov.udacity.nanodegree.popularmoviesdatabinding.model.content.Movie movie = mMovie;
        java.lang.String overviewMovie = null;
        java.lang.String voteAverageModel = null;
        arturvasilov.udacity.nanodegree.popularmoviesdatabinding.databinding.viewmodel.MovieDetailsViewModel model = mModel;
        java.lang.String yearModel = null;
        int favouriteImageModel = 0;
        arturvasilov.udacity.nanodegree.popularmoviesdatabinding.widget.BaseAdapter.OnItemClickListener arturvasilovUdacityN = null;
        android.support.v7.widget.RecyclerView.LayoutManager reviewsLayoutManager = null;
        boolean reviewsEnabledModel = false;
        java.lang.String imageWidthModel = null;
        java.lang.String androidStringRatingV = null;
        java.lang.String titleMovie = null;
        int loadingModelGONEView = 0;
        arturvasilov.udacity.nanodegree.popularmoviesdatabinding.widget.ReviewsAdapter reviewsAdapterModel = null;
        java.util.List<arturvasilov.udacity.nanodegree.popularmoviesdatabinding.model.content.Video> trailersModel = null;
        int loadingModelVISIBLEV = 0;
        android.support.v7.widget.RecyclerView.LayoutManager trailersLayoutManage = null;
        int reviewsEnabledModelV = 0;
        java.util.List<arturvasilov.udacity.nanodegree.popularmoviesdatabinding.model.content.Review> reviewsModel = null;
        java.lang.String voteMaxModel = null;
        boolean trailersEnabledModel1 = false;

        if ((dirtyFlags & 0x2001bL) != 0) {


            if ((dirtyFlags & 0x2000bL) != 0) {

                    if (movie != null) {
                        // read movie.posterPath
                        posterPathMovie = movie.getPosterPath();
                    }
            }
            if ((dirtyFlags & 0x20002L) != 0) {

                    if (movie != null) {
                        // read movie.overview
                        overviewMovie = movie.getOverview();
                    }
            }
            if ((dirtyFlags & 0x20013L) != 0) {

                    if (movie != null) {
                        // read movie.title
                        titleMovie = movie.getTitle();
                    }
            }
        }
        if ((dirtyFlags & 0x3ffffL) != 0) {


            if ((dirtyFlags & 0x20005L) != 0) {

                    if (model != null) {
                        // read model.expandedTitleColor
                        expandedTitleColorMo = model.getExpandedTitleColor();
                    }
            }
            if ((dirtyFlags & 0x20001L) != 0) {

                    if (model != null) {
                        // read model::onFavouriteButtonClick
                        androidViewViewOnCli = (((mAndroidViewViewOnCl == null) ? (mAndroidViewViewOnCl = new OnClickListenerImpl()) : mAndroidViewViewOnCl).setValue(model));
                    }
            }
            if ((dirtyFlags & 0x20101L) != 0) {

                    if (model != null) {
                        // read model.loading
                        loadingModel = model.isLoading();
                    }
                    if((dirtyFlags & 0x20101L) != 0) {
                        if (loadingModel) {
                            dirtyFlags |= 0x200000L;
                            dirtyFlags |= 0x800000L;
                        } else {
                            dirtyFlags |= 0x100000L;
                            dirtyFlags |= 0x400000L;
                        }}


                    // read model.loading ? View.GONE : View.VISIBLE
                    loadingModelGONEView = (loadingModel) ? (android.view.View.GONE) : (android.view.View.VISIBLE);
                    // read model.loading ? View.VISIBLE : View.GONE
                    loadingModelVISIBLEV = (loadingModel) ? (android.view.View.VISIBLE) : (android.view.View.GONE);
            }
            if ((dirtyFlags & 0x21c01L) != 0) {

                    if (model != null) {
                        // read model.trailersAdapter
                        trailersAdapterModel = model.getTrailersAdapter();
                        // read model::onTrailerClick
                        arturvasilovUdacityN = (((mArturvasilovUdacity == null) ? (mArturvasilovUdacity = new OnItemClickListenerI()) : mArturvasilovUdacity).setValue(model));
                        // read model.trailers
                        trailersModel = model.getTrailers();
                        // read model.trailersLayoutManager
                        trailersLayoutManage = model.getTrailersLayoutManager();
                    }
            }
            if ((dirtyFlags & 0x20061L) != 0) {

                    if (model != null) {
                        // read model.voteAverage
                        voteAverageModel = model.getVoteAverage();
                        // read model.voteMax
                        voteMaxModel = model.getVoteMax();
                    }


                    // read @android:string/rating
                    androidStringRatingV = getRoot().getResources().getString(R.string.rating, voteAverageModel, voteMaxModel);
            }
            if ((dirtyFlags & 0x20013L) != 0) {

                    if (model != null) {
                        // read model.year
                        yearModel = model.getYear();
                    }


                    // read @android:string/movie_title
                    androidStringMovieTi = getRoot().getResources().getString(R.string.movie_title, titleMovie, yearModel);
            }
            if ((dirtyFlags & 0x20081L) != 0) {

                    if (model != null) {
                        // read model.favouriteImage
                        favouriteImageModel = model.getFavouriteImage();
                    }
            }
            if ((dirtyFlags & 0x3c001L) != 0) {

                    if (model != null) {
                        // read model.reviewsLayoutManager
                        reviewsLayoutManager = model.getReviewsLayoutManager();
                        // read model.reviewsAdapter
                        reviewsAdapterModel = model.getReviewsAdapter();
                        // read model.reviews
                        reviewsModel = model.getReviews();
                    }
            }
            if ((dirtyFlags & 0x22001L) != 0) {

                    if (model != null) {
                        // read model.reviewsEnabled
                        reviewsEnabledModel = model.isReviewsEnabled();
                    }
                    if((dirtyFlags & 0x22001L) != 0) {
                        if (reviewsEnabledModel) {
                            dirtyFlags |= 0x2000000L;
                        } else {
                            dirtyFlags |= 0x1000000L;
                        }}


                    // read model.reviewsEnabled ? View.VISIBLE : View.GONE
                    reviewsEnabledModelV = (reviewsEnabledModel) ? (android.view.View.VISIBLE) : (android.view.View.GONE);
            }
            if ((dirtyFlags & 0x2000bL) != 0) {

                    if (model != null) {
                        // read model.imageWidth
                        imageWidthModel = model.getImageWidth();
                    }
            }
            if ((dirtyFlags & 0x20201L) != 0) {

                    if (model != null) {
                        // read model.trailersEnabled
                        trailersEnabledModel1 = model.isTrailersEnabled();
                    }
                    if((dirtyFlags & 0x20201L) != 0) {
                        if (trailersEnabledModel1) {
                            dirtyFlags |= 0x80000L;
                        } else {
                            dirtyFlags |= 0x40000L;
                        }}


                    // read model.trailersEnabled ? View.VISIBLE : View.GONE
                    trailersEnabledModel = (trailersEnabledModel1) ? (android.view.View.VISIBLE) : (android.view.View.GONE);
            }
        }
        // batch finished
        if ((dirtyFlags & 0x22001L) != 0) {
            // api target 1

            this.mboundView10.setVisibility(reviewsEnabledModelV);
            this.mboundView11.setVisibility(reviewsEnabledModelV);
        }
        if ((dirtyFlags & 0x3c001L) != 0) {
            // api target 1

            arturvasilov.udacity.nanodegree.popularmoviesdatabinding.databinding.adapters.RecyclerAdapters.initRecycler(this.mboundView11, reviewsLayoutManager, reviewsAdapterModel, reviewsModel, (android.view.View)null, (arturvasilov.udacity.nanodegree.popularmoviesdatabinding.widget.BaseAdapter.OnItemClickListener)null);
        }
        if ((dirtyFlags & 0x20101L) != 0) {
            // api target 1

            this.mboundView12.setVisibility(loadingModelVISIBLEV);
            this.mboundView7.setVisibility(loadingModelGONEView);
        }
        if ((dirtyFlags & 0x2000bL) != 0) {
            // api target 1

            arturvasilov.udacity.nanodegree.popularmoviesdatabinding.databinding.adapters.ImageViewAdapters.setImageUrl(this.mboundView2, posterPathMovie, imageWidthModel);
        }
        if ((dirtyFlags & 0x20013L) != 0) {
            // api target 1

            android.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView3, androidStringMovieTi);
        }
        if ((dirtyFlags & 0x20002L) != 0) {
            // api target 1

            android.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView4, overviewMovie);
        }
        if ((dirtyFlags & 0x20061L) != 0) {
            // api target 1

            android.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView5, androidStringRatingV);
        }
        if ((dirtyFlags & 0x20001L) != 0) {
            // api target 1

            this.mboundView6.setOnClickListener(androidViewViewOnCli);
        }
        if ((dirtyFlags & 0x20081L) != 0) {
            // api target 1

            arturvasilov.udacity.nanodegree.popularmoviesdatabinding.databinding.adapters.ImageViewAdapters.setImageResource(this.mboundView6, favouriteImageModel);
        }
        if ((dirtyFlags & 0x20201L) != 0) {
            // api target 1

            this.mboundView8.setVisibility(trailersEnabledModel);
            this.mboundView9.setVisibility(trailersEnabledModel);
        }
        if ((dirtyFlags & 0x21c01L) != 0) {
            // api target 1

            arturvasilov.udacity.nanodegree.popularmoviesdatabinding.databinding.adapters.RecyclerAdapters.initRecycler(this.mboundView9, trailersLayoutManage, trailersAdapterModel, trailersModel, (android.view.View)null, (arturvasilov.udacity.nanodegree.popularmoviesdatabinding.widget.BaseAdapter.OnItemClickListener)arturvasilovUdacityN);
        }
        if ((dirtyFlags & 0x20005L) != 0) {
            // api target 1

            arturvasilov.udacity.nanodegree.popularmoviesdatabinding.databinding.adapters.SupportAdapters.setExpandedTitleColor(this.toolbarLayout, expandedTitleColorMo);
        }
    }
    // Listener Stub Implementations
    public static class OnClickListenerImpl implements android.view.View.OnClickListener{
        private arturvasilov.udacity.nanodegree.popularmoviesdatabinding.databinding.viewmodel.MovieDetailsViewModel value;
        public OnClickListenerImpl setValue(arturvasilov.udacity.nanodegree.popularmoviesdatabinding.databinding.viewmodel.MovieDetailsViewModel value) {
            this.value = value;
            return value == null ? null : this;
        }
        @Override
        public void onClick(android.view.View arg0) {
            this.value.onFavouriteButtonClick(arg0);
        }
    }
    public static class OnItemClickListenerI implements arturvasilov.udacity.nanodegree.popularmoviesdatabinding.widget.BaseAdapter.OnItemClickListener{
        private arturvasilov.udacity.nanodegree.popularmoviesdatabinding.databinding.viewmodel.MovieDetailsViewModel value;
        public OnItemClickListenerI setValue(arturvasilov.udacity.nanodegree.popularmoviesdatabinding.databinding.viewmodel.MovieDetailsViewModel value) {
            this.value = value;
            return value == null ? null : this;
        }
        @Override
        public void onItemClick(android.view.View arg0, java.lang.Object arg1) {
            this.value.onTrailerClick(arg0, arg1);
        }
    }
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    public static FragmentMovieDetailsBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static FragmentMovieDetailsBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot, android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<FragmentMovieDetailsBinding>inflate(inflater, arturvasilov.udacity.nanodegree.popularmoviesdatabinding.R.layout.fragment_movie_details, root, attachToRoot, bindingComponent);
    }
    public static FragmentMovieDetailsBinding inflate(android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static FragmentMovieDetailsBinding inflate(android.view.LayoutInflater inflater, android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(arturvasilov.udacity.nanodegree.popularmoviesdatabinding.R.layout.fragment_movie_details, null, false), bindingComponent);
    }
    public static FragmentMovieDetailsBinding bind(android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static FragmentMovieDetailsBinding bind(android.view.View view, android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/fragment_movie_details_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new FragmentMovieDetailsBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): model
        flag 1 (0x2L): movie
        flag 2 (0x3L): model.expandedTitleColor
        flag 3 (0x4L): model.imageWidth
        flag 4 (0x5L): model.year
        flag 5 (0x6L): model.voteAverage
        flag 6 (0x7L): model.voteMax
        flag 7 (0x8L): model.favouriteImage
        flag 8 (0x9L): model.loading
        flag 9 (0xaL): model.trailersEnabled
        flag 10 (0xbL): model.trailersAdapter
        flag 11 (0xcL): model.trailers
        flag 12 (0xdL): model.trailersLayoutManager
        flag 13 (0xeL): model.reviewsEnabled
        flag 14 (0xfL): model.reviewsAdapter
        flag 15 (0x10L): model.reviews
        flag 16 (0x11L): model.reviewsLayoutManager
        flag 17 (0x12L): null
        flag 18 (0x13L): model.trailersEnabled ? View.VISIBLE : View.GONE
        flag 19 (0x14L): model.trailersEnabled ? View.VISIBLE : View.GONE
        flag 20 (0x15L): model.loading ? View.GONE : View.VISIBLE
        flag 21 (0x16L): model.loading ? View.GONE : View.VISIBLE
        flag 22 (0x17L): model.loading ? View.VISIBLE : View.GONE
        flag 23 (0x18L): model.loading ? View.VISIBLE : View.GONE
        flag 24 (0x19L): model.reviewsEnabled ? View.VISIBLE : View.GONE
        flag 25 (0x1aL): model.reviewsEnabled ? View.VISIBLE : View.GONE
    flag mapping end*/
    //end
}