package arturvasilov.udacity.nanodegree.popularmoviesdatabinding.databinding;
import arturvasilov.udacity.nanodegree.popularmoviesdatabinding.R;
import arturvasilov.udacity.nanodegree.popularmoviesdatabinding.BR;
import android.view.View;
public class FragmentMoviesBinding extends android.databinding.ViewDataBinding  {

    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.empty, 3);
    }
    // views
    public final android.widget.TextView empty;
    private final android.support.design.widget.CoordinatorLayout mboundView0;
    private final arturvasilov.udacity.nanodegree.popularmoviesdatabinding.widget.EmptyRecyclerView mboundView1;
    private final arturvasilov.udacity.nanodegree.popularmoviesdatabinding.widget.RefreshingDialogView mboundView2;
    // variables
    private arturvasilov.udacity.nanodegree.popularmoviesdatabinding.databinding.viewmodel.MoviesViewModel mModel;
    // values
    // listeners
    private OnItemClickListenerI mArturvasilovUdacity;
    // Inverse Binding Event Handlers

    public FragmentMoviesBinding(android.databinding.DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 1);
        final Object[] bindings = mapBindings(bindingComponent, root, 4, sIncludes, sViewsWithIds);
        this.empty = (android.widget.TextView) bindings[3];
        this.mboundView0 = (android.support.design.widget.CoordinatorLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView1 = (arturvasilov.udacity.nanodegree.popularmoviesdatabinding.widget.EmptyRecyclerView) bindings[1];
        this.mboundView1.setTag(null);
        this.mboundView2 = (arturvasilov.udacity.nanodegree.popularmoviesdatabinding.widget.RefreshingDialogView) bindings[2];
        this.mboundView2.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x20L;
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
            case BR.model :
                setModel((arturvasilov.udacity.nanodegree.popularmoviesdatabinding.databinding.viewmodel.MoviesViewModel) variable);
                return true;
        }
        return false;
    }

    public void setModel(arturvasilov.udacity.nanodegree.popularmoviesdatabinding.databinding.viewmodel.MoviesViewModel model) {
        updateRegistration(0, model);
        this.mModel = model;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.model);
        super.requestRebind();
    }
    public arturvasilov.udacity.nanodegree.popularmoviesdatabinding.databinding.viewmodel.MoviesViewModel getModel() {
        return mModel;
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeModel((arturvasilov.udacity.nanodegree.popularmoviesdatabinding.databinding.viewmodel.MoviesViewModel) object, fieldId);
        }
        return false;
    }
    private boolean onChangeModel(arturvasilov.udacity.nanodegree.popularmoviesdatabinding.databinding.viewmodel.MoviesViewModel model, int fieldId) {
        switch (fieldId) {
            case BR.layoutManager: {
                synchronized(this) {
                        mDirtyFlags |= 0x2L;
                }
                return true;
            }
            case BR.adapter: {
                synchronized(this) {
                        mDirtyFlags |= 0x4L;
                }
                return true;
            }
            case BR.movies: {
                synchronized(this) {
                        mDirtyFlags |= 0x8L;
                }
                return true;
            }
            case BR.refreshing: {
                synchronized(this) {
                        mDirtyFlags |= 0x10L;
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
        arturvasilov.udacity.nanodegree.popularmoviesdatabinding.widget.MoviesAdapter adapterModel = null;
        arturvasilov.udacity.nanodegree.popularmoviesdatabinding.databinding.viewmodel.MoviesViewModel model = mModel;
        boolean refreshingModel = false;
        arturvasilov.udacity.nanodegree.popularmoviesdatabinding.widget.BaseAdapter.OnItemClickListener arturvasilovUdacityN = null;
        android.support.v7.widget.RecyclerView.LayoutManager layoutManagerModel = null;
        java.util.List<arturvasilov.udacity.nanodegree.popularmoviesdatabinding.model.content.Movie> moviesModel = null;

        if ((dirtyFlags & 0x3fL) != 0) {


            if ((dirtyFlags & 0x2fL) != 0) {

                    if (model != null) {
                        // read model.adapter
                        adapterModel = model.getAdapter();
                        // read model::onItemClick
                        arturvasilovUdacityN = (((mArturvasilovUdacity == null) ? (mArturvasilovUdacity = new OnItemClickListenerI()) : mArturvasilovUdacity).setValue(model));
                        // read model.layoutManager
                        layoutManagerModel = model.getLayoutManager();
                        // read model.movies
                        moviesModel = model.getMovies();
                    }
            }
            if ((dirtyFlags & 0x31L) != 0) {

                    if (model != null) {
                        // read model.refreshing
                        refreshingModel = model.isRefreshing();
                    }
            }
        }
        // batch finished
        if ((dirtyFlags & 0x2fL) != 0) {
            // api target 1

            arturvasilov.udacity.nanodegree.popularmoviesdatabinding.databinding.adapters.RecyclerAdapters.initRecycler(this.mboundView1, layoutManagerModel, adapterModel, moviesModel, empty, (arturvasilov.udacity.nanodegree.popularmoviesdatabinding.widget.BaseAdapter.OnItemClickListener)arturvasilovUdacityN);
        }
        if ((dirtyFlags & 0x31L) != 0) {
            // api target 1

            arturvasilov.udacity.nanodegree.popularmoviesdatabinding.databinding.adapters.CustomAdapters.setRefreshing(this.mboundView2, refreshingModel);
        }
    }
    // Listener Stub Implementations
    public static class OnItemClickListenerI implements arturvasilov.udacity.nanodegree.popularmoviesdatabinding.widget.BaseAdapter.OnItemClickListener{
        private arturvasilov.udacity.nanodegree.popularmoviesdatabinding.databinding.viewmodel.MoviesViewModel value;
        public OnItemClickListenerI setValue(arturvasilov.udacity.nanodegree.popularmoviesdatabinding.databinding.viewmodel.MoviesViewModel value) {
            this.value = value;
            return value == null ? null : this;
        }
        @Override
        public void onItemClick(android.view.View arg0, java.lang.Object arg1) {
            this.value.onItemClick(arg0, arg1);
        }
    }
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    public static FragmentMoviesBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static FragmentMoviesBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot, android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<FragmentMoviesBinding>inflate(inflater, arturvasilov.udacity.nanodegree.popularmoviesdatabinding.R.layout.fragment_movies, root, attachToRoot, bindingComponent);
    }
    public static FragmentMoviesBinding inflate(android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static FragmentMoviesBinding inflate(android.view.LayoutInflater inflater, android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(arturvasilov.udacity.nanodegree.popularmoviesdatabinding.R.layout.fragment_movies, null, false), bindingComponent);
    }
    public static FragmentMoviesBinding bind(android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static FragmentMoviesBinding bind(android.view.View view, android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/fragment_movies_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new FragmentMoviesBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): model
        flag 1 (0x2L): model.layoutManager
        flag 2 (0x3L): model.adapter
        flag 3 (0x4L): model.movies
        flag 4 (0x5L): model.refreshing
        flag 5 (0x6L): null
    flag mapping end*/
    //end
}