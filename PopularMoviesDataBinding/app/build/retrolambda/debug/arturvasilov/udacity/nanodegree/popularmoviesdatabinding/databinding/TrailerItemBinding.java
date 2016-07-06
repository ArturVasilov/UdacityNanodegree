package arturvasilov.udacity.nanodegree.popularmoviesdatabinding.databinding;
import arturvasilov.udacity.nanodegree.popularmoviesdatabinding.R;
import arturvasilov.udacity.nanodegree.popularmoviesdatabinding.BR;
import android.view.View;
public class TrailerItemBinding extends android.databinding.ViewDataBinding  {

    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = null;
    }
    // views
    private final android.widget.TextView mboundView0;
    // variables
    private arturvasilov.udacity.nanodegree.popularmoviesdatabinding.model.content.Video mTrailer;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public TrailerItemBinding(android.databinding.DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 1, sIncludes, sViewsWithIds);
        this.mboundView0 = (android.widget.TextView) bindings[0];
        this.mboundView0.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x2L;
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
            case BR.trailer :
                setTrailer((arturvasilov.udacity.nanodegree.popularmoviesdatabinding.model.content.Video) variable);
                return true;
        }
        return false;
    }

    public void setTrailer(arturvasilov.udacity.nanodegree.popularmoviesdatabinding.model.content.Video trailer) {
        this.mTrailer = trailer;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.trailer);
        super.requestRebind();
    }
    public arturvasilov.udacity.nanodegree.popularmoviesdatabinding.model.content.Video getTrailer() {
        return mTrailer;
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
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
        arturvasilov.udacity.nanodegree.popularmoviesdatabinding.model.content.Video trailer = mTrailer;
        java.lang.String nameTrailer = null;

        if ((dirtyFlags & 0x3L) != 0) {



                if (trailer != null) {
                    // read trailer.name
                    nameTrailer = trailer.getName();
                }
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            android.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView0, nameTrailer);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    public static TrailerItemBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static TrailerItemBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot, android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<TrailerItemBinding>inflate(inflater, arturvasilov.udacity.nanodegree.popularmoviesdatabinding.R.layout.trailer_item, root, attachToRoot, bindingComponent);
    }
    public static TrailerItemBinding inflate(android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static TrailerItemBinding inflate(android.view.LayoutInflater inflater, android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(arturvasilov.udacity.nanodegree.popularmoviesdatabinding.R.layout.trailer_item, null, false), bindingComponent);
    }
    public static TrailerItemBinding bind(android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static TrailerItemBinding bind(android.view.View view, android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/trailer_item_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new TrailerItemBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): trailer
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}