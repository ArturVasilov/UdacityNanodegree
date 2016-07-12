package arturvasilov.udacity.nanodegree.popularmoviesdatabinding.databinding;
import arturvasilov.udacity.nanodegree.popularmoviesdatabinding.R;
import arturvasilov.udacity.nanodegree.popularmoviesdatabinding.BR;
import android.view.View;
public class ActivityMovieDetailsBinding extends android.databinding.ViewDataBinding  {

    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.app_bar, 6);
        sViewsWithIds.put(R.id.toolbar, 7);
    }
    // views
    public final android.support.design.widget.AppBarLayout appBar;
    private final android.support.design.widget.CoordinatorLayout mboundView0;
    private final android.widget.ImageView mboundView2;
    private final android.widget.TextView mboundView3;
    private final android.widget.TextView mboundView4;
    private final android.widget.TextView mboundView5;
    public final android.support.v7.widget.Toolbar toolbar;
    public final android.support.design.widget.CollapsingToolbarLayout toolbarLayout;
    // variables
    private arturvasilov.udacity.nanodegree.popularmoviesdatabinding.model.content.Movie mMovie;
    private arturvasilov.udacity.nanodegree.popularmoviesdatabinding.databinding.viewmodel.MovieDetailsViewModel mModel;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityMovieDetailsBinding(android.databinding.DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 1);
        final Object[] bindings = mapBindings(bindingComponent, root, 8, sIncludes, sViewsWithIds);
        this.appBar = (android.support.design.widget.AppBarLayout) bindings[6];
        this.mboundView0 = (android.support.design.widget.CoordinatorLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView2 = (android.widget.ImageView) bindings[2];
        this.mboundView2.setTag(null);
        this.mboundView3 = (android.widget.TextView) bindings[3];
        this.mboundView3.setTag(null);
        this.mboundView4 = (android.widget.TextView) bindings[4];
        this.mboundView4.setTag(null);
        this.mboundView5 = (android.widget.TextView) bindings[5];
        this.mboundView5.setTag(null);
        this.toolbar = (android.support.v7.widget.Toolbar) bindings[7];
        this.toolbarLayout = (android.support.design.widget.CollapsingToolbarLayout) bindings[1];
        this.toolbarLayout.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x80L;
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
        java.lang.String androidStringMovieTi = null;
        arturvasilov.udacity.nanodegree.popularmoviesdatabinding.model.content.Movie movie = mMovie;
        java.lang.String overviewMovie = null;
        java.lang.String voteAverageModel = null;
        arturvasilov.udacity.nanodegree.popularmoviesdatabinding.databinding.viewmodel.MovieDetailsViewModel model = mModel;
        java.lang.String yearModel = null;
        java.lang.String imageWidthModel = null;
        java.lang.String androidStringRatingV = null;
        java.lang.String titleMovie = null;
        java.lang.String voteMaxModel = null;

        if ((dirtyFlags & 0x9bL) != 0) {


            if ((dirtyFlags & 0x8bL) != 0) {

                    if (movie != null) {
                        // read movie.posterPath
                        posterPathMovie = movie.getPosterPath();
                    }
            }
            if ((dirtyFlags & 0x82L) != 0) {

                    if (movie != null) {
                        // read movie.overview
                        overviewMovie = movie.getOverview();
                    }
            }
            if ((dirtyFlags & 0x93L) != 0) {

                    if (movie != null) {
                        // read movie.title
                        titleMovie = movie.getTitle();
                    }
            }
        }
        if ((dirtyFlags & 0xffL) != 0) {


            if ((dirtyFlags & 0x85L) != 0) {

                    if (model != null) {
                        // read model.expandedTitleColor
                        expandedTitleColorMo = model.getExpandedTitleColor();
                    }
            }
            if ((dirtyFlags & 0xe1L) != 0) {

                    if (model != null) {
                        // read model.voteAverage
                        voteAverageModel = model.getVoteAverage();
                        // read model.voteMax
                        voteMaxModel = model.getVoteMax();
                    }


                    // read @android:string/rating
                    androidStringRatingV = getRoot().getResources().getString(R.string.rating, voteAverageModel, voteMaxModel);
            }
            if ((dirtyFlags & 0x93L) != 0) {

                    if (model != null) {
                        // read model.year
                        yearModel = model.getYear();
                    }


                    // read @android:string/movie_title
                    androidStringMovieTi = getRoot().getResources().getString(R.string.movie_title, titleMovie, yearModel);
            }
            if ((dirtyFlags & 0x8bL) != 0) {

                    if (model != null) {
                        // read model.imageWidth
                        imageWidthModel = model.getImageWidth();
                    }
            }
        }
        // batch finished
        if ((dirtyFlags & 0x8bL) != 0) {
            // api target 1

            arturvasilov.udacity.nanodegree.popularmoviesdatabinding.databinding.adapters.ImageViewAdapters.setImageUrl(this.mboundView2, posterPathMovie, imageWidthModel);
        }
        if ((dirtyFlags & 0x93L) != 0) {
            // api target 1

            android.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView3, androidStringMovieTi);
        }
        if ((dirtyFlags & 0x82L) != 0) {
            // api target 1

            android.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView4, overviewMovie);
        }
        if ((dirtyFlags & 0xe1L) != 0) {
            // api target 1

            android.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView5, androidStringRatingV);
        }
        if ((dirtyFlags & 0x85L) != 0) {
            // api target 1

            arturvasilov.udacity.nanodegree.popularmoviesdatabinding.databinding.adapters.SupportAdapters.setExpandedTitleColor(this.toolbarLayout, expandedTitleColorMo);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    public static ActivityMovieDetailsBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static ActivityMovieDetailsBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot, android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<ActivityMovieDetailsBinding>inflate(inflater, arturvasilov.udacity.nanodegree.popularmoviesdatabinding.R.layout.activity_movie_details, root, attachToRoot, bindingComponent);
    }
    public static ActivityMovieDetailsBinding inflate(android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static ActivityMovieDetailsBinding inflate(android.view.LayoutInflater inflater, android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(arturvasilov.udacity.nanodegree.popularmoviesdatabinding.R.layout.activity_movie_details, null, false), bindingComponent);
    }
    public static ActivityMovieDetailsBinding bind(android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static ActivityMovieDetailsBinding bind(android.view.View view, android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/activity_movie_details_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new ActivityMovieDetailsBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): model
        flag 1 (0x2L): movie
        flag 2 (0x3L): model.expandedTitleColor
        flag 3 (0x4L): model.imageWidth
        flag 4 (0x5L): model.year
        flag 5 (0x6L): model.voteAverage
        flag 6 (0x7L): model.voteMax
        flag 7 (0x8L): null
    flag mapping end*/
    //end
}