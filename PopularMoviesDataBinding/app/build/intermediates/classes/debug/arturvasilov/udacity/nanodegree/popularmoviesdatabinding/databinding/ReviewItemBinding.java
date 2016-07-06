package arturvasilov.udacity.nanodegree.popularmoviesdatabinding.databinding;
import arturvasilov.udacity.nanodegree.popularmoviesdatabinding.R;
import arturvasilov.udacity.nanodegree.popularmoviesdatabinding.BR;
import android.view.View;
public class ReviewItemBinding extends android.databinding.ViewDataBinding  {

    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = null;
    }
    // views
    private final android.widget.LinearLayout mboundView0;
    private final android.widget.TextView mboundView1;
    private final android.widget.TextView mboundView2;
    // variables
    private arturvasilov.udacity.nanodegree.popularmoviesdatabinding.model.content.Review mReview;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ReviewItemBinding(android.databinding.DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 3, sIncludes, sViewsWithIds);
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView1 = (android.widget.TextView) bindings[1];
        this.mboundView1.setTag(null);
        this.mboundView2 = (android.widget.TextView) bindings[2];
        this.mboundView2.setTag(null);
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
            case BR.review :
                setReview((arturvasilov.udacity.nanodegree.popularmoviesdatabinding.model.content.Review) variable);
                return true;
        }
        return false;
    }

    public void setReview(arturvasilov.udacity.nanodegree.popularmoviesdatabinding.model.content.Review review) {
        this.mReview = review;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.review);
        super.requestRebind();
    }
    public arturvasilov.udacity.nanodegree.popularmoviesdatabinding.model.content.Review getReview() {
        return mReview;
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
        java.lang.String contentReview = null;
        arturvasilov.udacity.nanodegree.popularmoviesdatabinding.model.content.Review review = mReview;
        java.lang.String authorReview = null;

        if ((dirtyFlags & 0x3L) != 0) {



                if (review != null) {
                    // read review.content
                    contentReview = review.getContent();
                    // read review.author
                    authorReview = review.getAuthor();
                }
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            android.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView1, authorReview);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView2, contentReview);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    public static ReviewItemBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static ReviewItemBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot, android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<ReviewItemBinding>inflate(inflater, arturvasilov.udacity.nanodegree.popularmoviesdatabinding.R.layout.review_item, root, attachToRoot, bindingComponent);
    }
    public static ReviewItemBinding inflate(android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static ReviewItemBinding inflate(android.view.LayoutInflater inflater, android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(arturvasilov.udacity.nanodegree.popularmoviesdatabinding.R.layout.review_item, null, false), bindingComponent);
    }
    public static ReviewItemBinding bind(android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static ReviewItemBinding bind(android.view.View view, android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/review_item_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new ReviewItemBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): review
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}